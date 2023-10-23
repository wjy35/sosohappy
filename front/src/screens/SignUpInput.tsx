import { View, Text, TextInput } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import SignUpInputStyle from "@/styles/SignUpInputStyle";

const SignUpInput = () => {
  return (
    <CommonLayout headerType={0} footer={true}>
      <AuthTitle level="3" title="회원가입을 진행합니다." description={`간편한 회원가입으로 소소한${"\n"}행운을 쌓습니다`}/>

      <View style={SignUpInputStyle.signUpInputWrap}>
        <Text style={SignUpInputStyle.signUpInputText}>아이디를 입력해주세요.</Text>
        <TextInput
          placeholder="아이디를 입력해주세요."
          style={SignUpInputStyle.signUpInput}
        />
        <Text style={SignUpInputStyle.signUpInputText}>비밀번호를 입력해주세요.</Text>
        <TextInput
          placeholder="비밀번호를 입력해주세요."
          style={SignUpInputStyle.signUpInput}
          secureTextEntry={true}
        />
        <Text style={SignUpInputStyle.signUpInputText}>비밀번호를 다시 입력해주세요.</Text>
        <TextInput
          placeholder="비밀번호를 다시 입력해주세요."
          style={SignUpInputStyle.signUpInput}
          secureTextEntry={true}
        />
        <Text style={SignUpInputStyle.signUpInputText}>이름을 입력해주세요.</Text>
        <TextInput
          placeholder="이름을 입력해주세요."
          style={SignUpInputStyle.signUpInput}
        />
        <Text style={SignUpInputStyle.signUpInputText}>등급을 입력해주세요.</Text>
        <TextInput
          value="나눔이"
          style={SignUpInputStyle.signUpInput}
          editable={false}
        />
        <Text style={SignUpInputStyle.signUpInputText}>닉네임을 입력해주세요.</Text>
        <TextInput
          placeholder="닉네임을 입력해주세요."
          style={SignUpInputStyle.signUpInput}
        />
        <Text style={SignUpInputStyle.signUpInputText}>성별을 입력해주세요.</Text>

      </View>

      <AuthButton movePage="Main"/>
    </CommonLayout>
  );
};

export default SignUpInput;
