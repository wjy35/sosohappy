import { View, Text, TextInput, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

import LoginStyle from "@/styles/LoginStyle"

const Login = () => {
  return (
    <CommonLayout>
      <Header/>
      <View style={LoginStyle.loginTitleWrap}>
        <Text style={LoginStyle.loginCategory}>로그인</Text>
        <Text style={LoginStyle.loginTitle}>당신의 행운나눔이.</Text>
        <Text style={LoginStyle.loginDescription}>
          소소하고 소중한 행운을 오늘도{"\n"}
          전달합니다.
        </Text>
      </View>

      <View style={LoginStyle.loginContentWrap}>
        <TextInput
          placeholder="아이디를 입력해주세요."
          style={LoginStyle.loginInput}
        />
        <TextInput
          placeholder="비밀번호를 입력해주세요."
          style={LoginStyle.passwordInput}
          secureTextEntry={true}
        />
        <TouchableOpacity activeOpacity={0.7}>
          <View style={LoginStyle.loginButton}>
            <Text style={LoginStyle.loginButtonText}>로그인</Text>
          </View>
        </TouchableOpacity>
        <TouchableOpacity activeOpacity={0.7}>
          <Text style={LoginStyle.authText}>회원이 아니신가요?</Text>
        </TouchableOpacity>
      </View>
      <Footer/>
    </CommonLayout>
  );
};

export default Login;
