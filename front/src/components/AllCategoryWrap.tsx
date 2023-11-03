import { useEffect, useState } from "react"
import {View, Text, ScrollView, TouchableOpacity} from "react-native"
import helpCategoryApi from "@/apis/helpCategoryApi"

import CategoryWrapStyle from "@/styles/CategoryWrapStyle";

import { SvgXml } from "react-native-svg";

interface propsType{
    category: any;
    selectCategory: Function;
}

const AllCategoryWrap = ({category, selectCategory}: propsType) => {
    const [defaultCategories, setDefaultCategories] = useState<any[] | null>(null);
    useEffect(() => {
      const getCategories = async () => {
        const res = await helpCategoryApi.default();
        setDefaultCategories(res.data.result.defaultCategoryList);
      }
      getCategories();
    }, [])
    return(
        <ScrollView horizontal={true}>
          {
            defaultCategories &&
            defaultCategories.map((defaultCategory, index) => {
              return(
                <>
                <TouchableOpacity activeOpacity={0.7} onPress={()=>selectCategory(defaultCategory)}>
                  <View style={[CategoryWrapStyle.categoryItemWrap]}>
                    <SvgXml
                      xml={defaultCategory.categoryImage}
                      style={CategoryWrapStyle.categoryItemImg}
                    />
                    <Text style={CategoryWrapStyle.categoryItemText}>{defaultCategory.categoryName}</Text>
                  </View>
                </TouchableOpacity>
                </>
              );
            })
          }
        </ScrollView>
    );
}

export default AllCategoryWrap;
