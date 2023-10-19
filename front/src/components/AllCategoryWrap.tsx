import {View, Text, ScrollView, Image} from "react-native"

import CategoryWrapStyle from "@/styles/CategoryWrapStyle";

import StairIcon from '@/assets/img/stair-icon.png'
import BusStopIcon from "@/assets/img/bus-stop-icon.png"
import HelpIcon from "@/assets/img/help-icon.png"

const AllCategoryWrap = () => {
    return(
        <ScrollView horizontal={true}>
          <View style={CategoryWrapStyle.categoryItemWrap}>
            <Image
              source={StairIcon}
              style={CategoryWrapStyle.categoryItemImg}
            />
            <Text style={CategoryWrapStyle.categoryItemText}>계단보행</Text>
          </View>
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