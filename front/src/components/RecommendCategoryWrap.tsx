import React, {useEffect, useState} from "react"
import {View, Text, ScrollView, TouchableOpacity} from "react-native"
import helpCategoryApi from "@/apis/helpCategoryApi";
import CategoryWrapStyle from "@/styles/CategoryWrapStyle";
import { SvgXml } from "react-native-svg";


interface propsType{
  selectCategory: Function;
}

const RecommendCategoryWrap = ({selectCategory}: propsType) => {
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
                <React.Fragment key={`recentCategory${index}`}>
                  <TouchableOpacity activeOpacity={0.7} onPress={()=>selectCategory(recentCategory)}>
                    <View style={CategoryWrapStyle.categoryItemWrap} key={index}>
                      <SvgXml
                        xml={recentCategory.categoryImage}
                        style={CategoryWrapStyle.categoryItemImg}
                      />
                      <Text style={CategoryWrapStyle.categoryItemText}>{recentCategory.categoryName}</Text>
                    </View>
                  </TouchableOpacity>
                </React.Fragment>
              );
            })
          }
        </ScrollView>
    );
}

export default RecommendCategoryWrap;
