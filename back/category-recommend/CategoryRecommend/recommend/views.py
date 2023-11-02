from django.shortcuts import render
from .models import Category

def category_test_view(request):
    categoryList = Category.objects.all() 
    return render(request, 'recommend/index.html',{"categoryList": categoryList})
