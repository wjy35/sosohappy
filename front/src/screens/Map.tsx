import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

import MapStyle from "@/styles/MapStyle";

const Map = () => {
  return (
    <CommonLayout>
      <Header/>
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
      <Footer/>
    </CommonLayout>
  );
};

export default Map;
