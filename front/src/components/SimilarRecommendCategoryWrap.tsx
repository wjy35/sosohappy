import {View, Text, Image, ScrollView} from "react-native"

import BusStopIcon from "@/assets/img/bus-stop-icon.png"

import SimilarRecommendCategoryWrapStyle from "@/styles/SimilarRecommendCategoryWrapStyle";

const LikeRecommendWrap = () => {
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