import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

const Map = () => {
  return (
    <CommonLayout>
      <Header/>
      <TouchableOpacity activeOpacity={0.7}>
        <View>
          <Image
            source={ColorMegaphoneIcon}
          />
          <View>
            <Text>도움이 필요하신가요?</Text>
            <Text>주변에 요청해보세요!</Text>
          </View>
          <Text>도움요청</Text>
        </View>
      </TouchableOpacity>
      <Footer/>
    </CommonLayout>
  );
};

export default Map;
