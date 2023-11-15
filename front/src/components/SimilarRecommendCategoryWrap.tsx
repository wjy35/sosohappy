import {useEffect, useState} from "react"
import {View, Text, Image, ScrollView} from "react-native"
import recommendApi from "@/apis/recommendApi";
import memberApi from "@/apis/memberApi";

import BusStopIcon from "@/assets/img/bus-stop-icon.png"

import SimilarRecommendCategoryWrapStyle from "@/styles/SimilarRecommendCategoryWrapStyle";

const LikeRecommendWrap = () => {
    const [myMemberId, setMyMemberId] = useState<number|null>(null);

    const getSimilarCategoryApi = async () => {
      try{
        const userRes = await memberApi.getMember();
        if(userRes.status === 200){
          setMyMemberId(userRes.data.result.member.memberId);
        }
      }catch(err){
        console.error(err);
      }

      if(myMemberId){
        const res = await recommendApi.recommend({memberId: myMemberId});
        console.log("res", res);
        
      }
    }

    useEffect(() => {
      getSimilarCategoryApi();
    },[]);
    return(
        <>
          <ScrollView horizontal={true}>
            <View style={SimilarRecommendCategoryWrapStyle.likeRecommendContentWrap}>
              <View>
                <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentMainTitle}>버스 함께 타기</Text>
                <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentSubTitle}>최근에 내가 가장 많이 올린 카테고리는?</Text>
              </View>
              <Image
                source={BusStopIcon}
                style={SimilarRecommendCategoryWrapStyle.likeRecommendIcon}
              />
            </View>

            <View style={SimilarRecommendCategoryWrapStyle.likeRecommendContentWrap}>
              <View>
                <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentMainTitle}>버스 함께 타기</Text>
                <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentSubTitle}>최근에 내가 가장 많이 올린 카테고리는?</Text>
              </View>
              <Image
                source={BusStopIcon}
                style={SimilarRecommendCategoryWrapStyle.likeRecommendIcon}
              />
            </View>

            <View style={SimilarRecommendCategoryWrapStyle.likeRecommendContentWrap}>
              <View>
                <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentMainTitle}>버스 함께 타기</Text>
                <Text style={SimilarRecommendCategoryWrapStyle.likeRecommendContentSubTitle}>최근에 내가 가장 많이 올린 카테고리는?</Text>
              </View>
              <Image
                source={BusStopIcon}
                style={SimilarRecommendCategoryWrapStyle.likeRecommendIcon}
              />
            </View>
          </ScrollView>
        </>
    );
}

export default LikeRecommendWrap;