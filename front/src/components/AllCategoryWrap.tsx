import { useEffect, useState } from "react"
import {View, Text, ScrollView, TouchableOpacity} from "react-native"
import helpCategoryApi from "@/apis/helpCategoryApi"

import CategoryWrapStyle from "@/styles/CategoryWrapStyle";

import { SvgXml } from "react-native-svg";

const AllCategoryWrap = () => {
    const [defaultCategories, setDefaultCategories] = useState<any[] | null>(null);
    useEffect(() => {
      const getCategories = async () => {
        const defaultCategories = await helpCategoryApi.default();
        setDefaultCategories(defaultCategories.data.result.defaultCategoryList);
      }

      getCategories();
    }, [])
    return(
        <ScrollView horizontal={true}>
          {
            defaultCategories &&
            defaultCategories.map((defaultCategory, index) => {
              console.log(defaultCategory);
              return(
                <>
                <TouchableOpacity activeOpacity={0.7}>
                  <View style={CategoryWrapStyle.categoryItemWrap}>
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