import React,{useEffect, useState} from "react"
import {View, Text, ScrollView} from "react-native"
import recommendApi from "@/apis/recommendApi";
import memberApi from "@/apis/memberApi";

import SimilarRecommendCategoryWrapStyle from "@/styles/SimilarRecommendCategoryWrapStyle";
import { SvgXml } from "react-native-svg";
import useStore from "@/store/store";

const LikeRecommendWrap = () => {
    const [recommendList, setRecommendList] = useState<any>([]);
    const {userInfo} = useStore();


    const getSimilarCategoryApi = async () => {
      try{
          const res = await recommendApi.recommend({memberId: userInfo.memberId});
          if(res.status === 200){
              setRecommendList(res.data.result.recommendCategoryList);
          }
      }catch(err){
        console.error(err);
      }

    }

    useEffect(() => {
      getSimilarCategoryApi();
    },[]);
    return(
        <>
          <ScrollView horizontal={true}>
            {
              recommendList &&
              recommendList.map((recommend, index) => {
                return(
                  <React.Fragment key={`recommend${index}`}>
                    <View style={SimilarRecommendCategoryWrapStyle.likeRecommendContentWrap}>
                      <View>
                        <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentMainTitle}>{recommend.category_name}</Text>
                        {/*<Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentSubTitle}>최근에 내가 가장 많이 올린 카테고리는?</Text>*/}
                      </View>
                      <SvgXml
                        xml={recommend.category_image}
                        style={SimilarRecommendCategoryWrapStyle.likeRecommendIcon}
                      />
                    </View>
                  </React.Fragment>
                );
              })
            }
          </ScrollView>
        </>
    );
}

export default LikeRecommendWrap;
