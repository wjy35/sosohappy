import { useState } from "react"
import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import BottomSheet from "@/components/BottomSheet";
import MapView, {PROVIDER_GOOGLE} from "react-native-maps"

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

import MapStyle from "@/styles/MapStyle";

import helpCategoryApi from "@/apis/helpCategoryApi";

const Map = () => {
  const [bottomSheetStatus, setBottomSheetStatus] = useState<Boolean>(false);
  const updateBottomSheetStatus = (updateStatus: Boolean) => {
    setBottomSheetStatus(updateStatus);
  }

  
  return (
    <CommonLayout footer={true} headerType={0}>
      <View style={{flex:1}}>
        <MapView
          style={{flex:1, width:400, height:400}}
          provider={PROVIDER_GOOGLE}
        />
      </View>
      <TouchableOpacity activeOpacity={0.7}>
        <View style={MapStyle.createHelpWrap}>
          <Image
            source={ColorMegaphoneIcon}
            style={MapStyle.megaphoneIcon}
          />
          <View style={MapStyle.createHelpInfo}>
            <Text style={MapStyle.helpSubTitle}>도움이 필요하신가요?</Text>
            <Text style={MapStyle.helpMainTitle}>주변에 요청해보세요!</Text>
          </View>
          <Text style={MapStyle.helpButton}>도움요청</Text>
        </View>
      </TouchableOpacity>
      {
        bottomSheetStatus ?
        <BottomSheet updateBottomSheetStatus={(updateStatus:Boolean) => updateBottomSheetStatus(updateStatus)}/>
        :
        <></>
      }
    </CommonLayout>
  );
};

export default Map;
