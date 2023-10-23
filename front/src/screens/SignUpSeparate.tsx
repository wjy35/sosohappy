import { useState } from "react"
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

const SignUpSeparate = () => {
  const [nanumBorderState, setNanumBorderState] = useState<Boolean>(false);
  const [moeumBorderState, setMoeumBorderState] = useState<Boolean>(false);

  const activeNanum = () => {
    if(nanumBorderState === true){
      setNanumBorderState(false);
      return;
    }
    setNanumBorderState(true);
    setMoeumBorderState(false);
  }

  const activeMoeum = () => {
    if(moeumBorderState === true){
      setMoeumBorderState(false);
      return;
    }
    setMoeumBorderState(true);
    setNanumBorderState(false);
  }
  return (
    <CommonLayout>
      <Header/>
      <AuthTitle level="1" title="서비스가 필요하신가요?" description="해당되는 곳을 선택해주세요."/>
      
      <View style={SignUpSeparateStyle.selectWrap}>
        <TouchableOpacity activeOpacity={0.7} onPress={activeNanum}>
          {
            nanumBorderState ?
            <View style={[SignUpSeparateStyle.selectContent, SignUpSeparateStyle.selectedContent]}>
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
            :
            <View style={SignUpSeparateStyle.selectContent}>
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

          }
        </TouchableOpacity>

        <TouchableOpacity activeOpacity={0.7} onPress={activeMoeum}>
          {
            moeumBorderState ?
            <View style={[SignUpSeparateStyle.selectContent, SignUpSeparateStyle.selectedContent]}>
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
            :
            <View style={SignUpSeparateStyle.selectContent}>
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
          }
        </TouchableOpacity>
      </View>
      <AuthButton movePage="SignUpAuth"/>
      <Footer/>
    </CommonLayout>
  );
};

export default SignUpSeparate;
