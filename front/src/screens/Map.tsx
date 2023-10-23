import { useState } from "react"
import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import BottomSheet from "@/components/BottomSheet";

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

import MapStyle from "@/styles/MapStyle";

const Map = () => {
  const [bottomSheetStatus, setBottomSheetStatus] = useState<Boolean>(true);
  const updateBottomSheetStatus = (updateStatus: Boolean) => {
    setBottomSheetStatus(updateStatus);
  }
  return (
    <CommonLayout footer={true} headerType={0}>
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
