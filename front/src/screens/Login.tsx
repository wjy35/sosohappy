import { View, Text, TouchableOpacity, Alert } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import LoginStyle from "@/styles/LoginStyle"
import useInput from "@/hooks/useInput";
import PlainInput from "@/components/PlainInput";
import memberApi from "@/apis/memberApi";
import pushAlarmApi from "@/apis/pushAlarmApi";
import RNSecureStorage, {ACCESSIBLE} from "rn-secure-storage";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import messaging from '@react-native-firebase/messaging'
import {observer} from "mobx-react";
import useStore from "@/hooks/useStore";
import {useEffect} from "react";

interface propsType{
  socket: {
    connect: Function,
    send: Function,
    status: String,
    helpList: helpDetail[],
    connected: boolean,
    disConnect: Function,
  };
}

interface helpDetail {
  memberId: number;
  nickname: string;
  category: {
    categoryId: number,
    categoryName: string,
    categoryImage: string,
  };
  longitude: number;
  latitude: number;
  content: string;
  place: string;
}

const Login = observer(({socket}: propsType) => {
  const navigation = useNavigation();
  const {userStore} = useStore();

  const checkId = (newText: string) => {
    inputId.updateIsValid(newText!=="");
  };

  const checkPassword = (newText: string) => {
    // TODO: add password validation check
    inputPassword.updateIsValid(newText!=="");
  }

  const inputId = useInput({
    placeholder: "아이디를 입력해주세요",
    initialIsValid: false,
    onChange: checkId,
  });
  const inputPassword = useInput({
    placeholder: "비밀번호를 입력해주세요",
    initialIsValid: false,
    onChange: checkPassword,
  });

  const getFcmToken = async () => {
    const fcmToken = await messaging().getToken();
    return fcmToken;
  };

  const login = async () => {
    try {
      const res = await memberApi.login({
        memberSignId: inputId.text,
        memberSignPassword: inputPassword.text,
      });

      if (res.status === 200){
        await RNSecureStorage.set("accessToken", res.data.result.authorization.accessToken, {accessible: ACCESSIBLE.WHEN_UNLOCKED});
        await RNSecureStorage.set("refreshToken", res.data.result.authorization.refreshToken, {accessible: ACCESSIBLE.WHEN_UNLOCKED});
      }

      const fcmToken = await getFcmToken();
      const insertFcmTokenApi = await pushAlarmApi.insertFcmToken({fcmToken});

      const userInfo = await memberApi.getMember();

      if (userInfo.status === 200){
        userStore.setUser(userInfo.data.result.member);
        navigation.replace('Main');
        // console.log(userInfo.data.result.member);
      }

      // if(insertFcmTokenApi.status === 200){
      //   navigation.replace('Main');
      // }

    } catch (err) {
      console.error(err);
    }
  };

  useFocusEffect(()=>{
    if (!socket.connected) return;
    socket.disConnect();
  })

  return (
    <CommonLayout headerType={0} footer={true}>
      <View style={LoginStyle.loginTitleWrap}>
        <Text style={LoginStyle.loginCategory}>로그인</Text>
        <Text style={LoginStyle.loginTitle}>당신의 행운나눔이.</Text>
        <Text style={LoginStyle.loginDescription}>
          소소하고 소중한 행운을 오늘도{"\n"}
          전달합니다.
        </Text>
      </View>
      <View style={LoginStyle.loginContentWrap}>
        <PlainInput {...inputId} />
        <PlainInput {...inputPassword} secureTextEntry={true} />
        <TouchableOpacity activeOpacity={0.7} onPress={()=>(inputId.isValid&&inputPassword.isValid)&&login()}>
          <View style={[LoginStyle.loginButton, (inputId.isValid&&inputPassword.isValid)&&LoginStyle.loginButtonActive]}>
            <Text style={[LoginStyle.loginButtonText]}>로그인</Text>
          </View>
        </TouchableOpacity>
        <TouchableOpacity activeOpacity={0.7}>
          <Text style={LoginStyle.authText}>회원이 아니신가요?</Text>
        </TouchableOpacity>
      </View>
    </CommonLayout>
  );
});

export default Login;
