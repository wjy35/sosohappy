import { View, Text, TouchableOpacity, Alert, ActivityIndicator } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import LoginStyle from "@/styles/LoginStyle"
import useInput from "@/hooks/useInput";
import PlainInput from "@/components/PlainInput";
import memberApi from "@/apis/memberApi";
import pushAlarmApi from "@/apis/pushAlarmApi";
import RNSecureStorage, {ACCESSIBLE} from "rn-secure-storage";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import messaging from '@react-native-firebase/messaging'
import useStore from "@/store/store";
import {useState} from "react";
import {useEffect} from "react";
import { helpSocket} from "@/types";

interface propsType{
  socket: helpSocket,
}

const Login = ({socket}: propsType) => {
  const navigation = useNavigation();
  const {userInfo, login} = useStore();
  const [isFail, setIsFail] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

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

  const userLogin = async () => {
    setIsLoading(true);
    try {
      const res = await memberApi.login({
        memberSignId: inputId.text,
        memberSignPassword: inputPassword.text,
      });

      if (res.status === 200 && res.data.status === 'success'){
        await RNSecureStorage.set("accessToken", res.data.result.authorization.accessToken, {accessible: ACCESSIBLE.WHEN_UNLOCKED});
        await RNSecureStorage.set("refreshToken", res.data.result.authorization.refreshToken, {accessible: ACCESSIBLE.WHEN_UNLOCKED});

        const userInfo = await memberApi.getMember();

        if (userInfo.status === 200){
          setIsLoading(false);
          login(userInfo.data.result.member);
          socket.getMemberId(userInfo.data.result.member.memberId);
          navigation.replace('Main');
        }
        const fcmToken = await getFcmToken();
        const insertFcmTokenApi = await pushAlarmApi.insertFcmToken({fcmToken});
      } else if (res.status === 200) {
        setIsFail(true);
        setIsLoading(false);
      }
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
        {
          isFail && (
                <View style={LoginStyle.loginFailWrap}>
                  <Text style={LoginStyle.loginFailText}>존재하지 않는 아이디 혹은 비밀번호 입니다.</Text>
                </View>
            )
        }

        <TouchableOpacity activeOpacity={0.7} onPress={()=>(inputId.isValid&&inputPassword.isValid)&&userLogin()}>
          <View style={[LoginStyle.loginButton, (inputId.isValid&&inputPassword.isValid)&&LoginStyle.loginButtonActive]}>
            {
              isLoading ? (
                  <ActivityIndicator size='large' color='#ffffff'/>
              ): (
                  <Text style={[LoginStyle.loginButtonText]}>로그인</Text>
              )
            }
          </View>
        </TouchableOpacity>
        <TouchableOpacity activeOpacity={0.7}>
          <Text style={LoginStyle.authText}>회원이 아니신가요?</Text>
        </TouchableOpacity>
      </View>
    </CommonLayout>
  );
};

export default Login;
