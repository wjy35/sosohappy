from django.shortcuts import render
from .models import Category, CategoryPick
import numpy as np
import pandas as pd
from django.http import JsonResponse
from surprise import Reader, Dataset, SVD
from surprise.model_selection import cross_validate


def custom_json_encoder(obj):
    if isinstance(obj, np.int64):
        return int(obj)
    raise TypeError(f"Object of type {type(obj)} is not JSON serializable")


def category_recommend(request):
    category_list = Category.objects.all()
    pick_list = CategoryPick.objects.values('member_id','pick_count','category_id')
    pick_data = pd.DataFrame(pick_list)

    min_pick = pick_data['pick_count'].min()
    max_pick = pick_data['pick_count'].max()
    reader = Reader(rating_scale=(min_pick,max_pick))

    data = Dataset.load_from_df(pick_data[['member_id', 'category_id', 'pick_count']], reader=reader)
    svd = SVD(random_state=0)

    # cross_validate(svd, data, measures=['RMSE','MAE'], cv=5, verbose=True)

    trainset = data.build_full_trainset()
    svd.fit(trainset)

    predictions = []

    for i in range(1, category_list.__len__()+1):
        res = svd.predict(int(request.headers.get("memberId")), i)
        predictions.append({
            'category_id': i,
            'predicted_rating': res.est
        })

    sorted_predictions = sorted(predictions, key=lambda x: x['predicted_rating'], reverse=True)
    top_5 = sorted_predictions[:5]

    result_list = []

    # category_list에서 해당 ID의 객체 찾기
    for item in top_5:
        category_id = item['category_id']
        category = category_list.get(category_id=category_id)  
        if category:
            result_list.append({
                'category_id': category.category_id,
                'predicted_rating': item['predicted_rating'],
                'category_name': category.category_name,
                'category_image': category.category_image
            })

    response_data = {
        'status': "success",
        'message' : "추천 카테고리 조회 완료",
        'result': {
            'recommendCategoryList': result_list,
        }
    }
    return JsonResponse(response_data, json_dumps_params={'default': custom_json_encoder})

