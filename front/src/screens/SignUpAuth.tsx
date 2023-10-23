import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import {SvgXml} from "react-native-svg";
import {addPlus} from "@/assets/icons/icons";


import SignUpAuthStyle from "@/styles/SignUpAuthStyle";
import {useState} from "react";

const SignUpAuth = () => {
    const [isActive, setIsActive] = useState(false);

  return (
    <CommonLayout headerType={0} footer={true}>
        <AuthTitle level="2" title="증명서를 인증해주세요. " description="사진 한 장으로 나를 증명할 수 있어요"/>
        <View>
          <TouchableOpacity activeOpacity={0.7}>
            <View style={SignUpAuthStyle.uploadWrap}>
              <SvgXml
                xml={addPlus}
                width={40}
                height={40}
              />
              <Text style={SignUpAuthStyle.uploadText}>사진 등록하기</Text>
            </View>
          </TouchableOpacity>
        </View>
        <AuthButton movePage="SignUpInput" isActive={isActive} buttonText={'다음단계 진행하기'}/>
    </CommonLayout>
  );
};

export default SignUpAuth;
