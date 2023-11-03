import {useEffect, useState} from "react"
import {View, Text, ScrollView, TouchableOpacity} from "react-native"
import helpCategoryApi from "@/apis/helpCategoryApi";

import CategoryWrapStyle from "@/styles/CategoryWrapStyle";

import { SvgXml } from "react-native-svg";

const RecommendCategoryWrap = () => {
  const [recentCategories, setRecentCategories] = useState<any|null>(null);
    useEffect(() => {
      const getRecentCategoriesApi = async () => {
        const recentCategories = await helpCategoryApi.recent();
        setRecentCategories(recentCategories.data.result.recentCategoryList);
      }

      getRecentCategoriesApi();
    }, [])
    return(
        <ScrollView horizontal={true}>
          {
            recentCategories &&
            recentCategories.map((recentCategory: any, index: number) => {
              return(
                <>
                <TouchableOpacity activeOpacity={0.7}>
                  <View style={CategoryWrapStyle.categoryItemWrap} key={index}>
                    <SvgXml
                      xml={recentCategory.categoryImage}
                      style={CategoryWrapStyle.categoryItemImg}
                    />
                    <Text style={CategoryWrapStyle.categoryItemText}>{recentCategory.categoryName}</Text>
                  </View> 
                </TouchableOpacity>
                </>
              );
            })
          }
        </ScrollView>
    );
}

export default RecommendCategoryWrap;