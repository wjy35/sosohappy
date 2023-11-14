import {useEffect, useState} from "react"
import { Text, View, Image} from "react-native";
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import NanumImg from "@/assets/img/nanum-img.png"
import MoeumImg from "@/assets/img/moeum-img.png"

import SignUpSeparateStyle from "@/styles/SignUpSeparateStyle";
import { TouchableOpacity } from "react-native-gesture-handler";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import {ChatSocket, helpSocket} from "@/types";

interface propsType{
  socket: helpSocket;
  chatSocket: ChatSocket;
}


const SignUpSeparate = ({socket, chatSocket}: propsType) => {
  const [selectedType, setSelectedType] = useState(0);
  const [isActive, setIsActive] = useState(false);
  const navigation = useNavigation();

  const activeNanum = () => {
    setSelectedType(1);
    if (!isActive) setIsActive(true);
  }

  const activeMoeum = () => {
    setSelectedType(2);
    if (!isActive) setIsActive(true);
  }

  const goNext = () => {
    if (selectedType === 1){
      navigation.navigate("SignUpAuth", {selectedType: selectedType});
    } else {
      navigation.navigate("SignUpInput", {selectedType: selectedType});
    }
  }

  useFocusEffect(()=>{
    if (!socket.connected) return;
    socket.disConnect();
  })

  return (
    <CommonLayout headerType={0} footer={true}>
      <AuthTitle level="1" title="서비스가 필요하신가요?" description="해당되는 곳을 선택해주세요."/>

      <View style={SignUpSeparateStyle.selectWrap}>
        <TouchableOpacity activeOpacity={0.7} onPress={activeNanum}>
          <View style={[SignUpSeparateStyle.selectContent, selectedType===1 && SignUpSeparateStyle.selectedContent]}>
            <Image
              source={NanumImg}
              style={SignUpSeparateStyle. selectImg}
            />
            <Text style={SignUpSeparateStyle.selectName}>나눔이</Text>
            <Text style={SignUpSeparateStyle.selectInfo}>
              도움을 요청하실 수{"\n"}
              있습니다.
            </Text>
            <Text style={SignUpSeparateStyle.authInfoText}>인증이 필요합니다</Text>
          </View>
        </TouchableOpacity>

        <TouchableOpacity activeOpacity={0.7} onPress={activeMoeum}>
          <View style={[SignUpSeparateStyle.selectContent, selectedType===2 && SignUpSeparateStyle.selectedContent]}>
            <Image
              source={MoeumImg}
              style={SignUpSeparateStyle. selectImg}
            />
            <Text style={SignUpSeparateStyle.selectName}>모음이</Text>
            <Text style={SignUpSeparateStyle.selectInfo}>
              도움을 제공하실 수{"\n"}
              있습니다.
            </Text>
          </View>
        </TouchableOpacity>
      </View>
      <AuthButton isActive={isActive} buttonText={'다음단계 진행하기'} goNext={goNext}/>
    </CommonLayout>
  );
};

export default SignUpSeparate;
