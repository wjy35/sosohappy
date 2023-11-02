import { useEffect, useState } from "react"
import {View, Text, ScrollView, Image} from "react-native"
import helpCategoryApi from "@/apis/helpCategoryApi"

import CategoryWrapStyle from "@/styles/CategoryWrapStyle";

import StairIcon from '@/assets/img/stair-icon.png'
import BusStopIcon from "@/assets/img/bus-stop-icon.png"
import HelpIcon from "@/assets/img/help-icon.png"
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
                  <View style={CategoryWrapStyle.categoryItemWrap}>
                    <SvgXml
                      xml={defaultCategory.categoryImage}
                      style={CategoryWrapStyle.categoryItemImg}
                    />
                    <Text style={CategoryWrapStyle.categoryItemText}>계단보행</Text>
                  </View>
                </>
              );
            })
          }
          
          <View style={CategoryWrapStyle.categoryItemWrap}>
            <Image
              source={BusStopIcon}
              style={CategoryWrapStyle.categoryItemImg}
            />
            <Text style={CategoryWrapStyle.categoryItemText}>버스 승·하차</Text>
          </View>
          <View style={CategoryWrapStyle.categoryItemWrap}>
            <Image
              source={HelpIcon}
              style={CategoryWrapStyle.categoryItemImg}
            />
            <Text style={CategoryWrapStyle.categoryItemText}>기타</Text>
          </View>
        </ScrollView>
    );
}

export default AllCategoryWrap;