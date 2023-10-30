import { View, Text, TextInput } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import SignUpInputStyle from "@/styles/SignUpInputStyle";
import {useEffect, useState} from "react";
import PlainInput from "@/components/PlainInput";
import useInput from "@/hooks/useInput";
import {useRoute} from "@react-navigation/native";
import memberApi from "@/apis/memberApi";
import axios from "axios";

const SignUpInput = () => {
  const route = useRoute();
  const [isActive, setIsActive] = useState(false);
  const [selectedGender, setSelectedGender] = useState(1);

  const checkMemberId = async (newText: string) => {
    try {
      const res = await memberApi.checkIdDuplicate({
        memberSignId: newText,
      })
      if (res.status === 200){
        memberId.updateIsValid(res.data.result.availability);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const checkMemberPassword = (newText: string) => {
    memberPassword.updateIsValid(newText !== "");
  };

  const checkMemberCheckPassword = (newText: string) => {
    memberCheckPassword.updateIsValid(newText === memberPassword.text);
  };

  const checkMemberName = (newText: string) => {
    // TODO: 글자수제한 필요 5글자 넘어가면 에러남
    memberName.updateIsValid(newText !== "");
  };

  const checkMemberNickname = async (newText: string) => {
    try {
      const res = await memberApi.checkNicknameDuplicate({
        memberNickname: newText,
      })
      if (res.status === 200){
        memberNickname.updateIsValid(res.data.result.availability);
      }
    } catch (err) {
      console.log(err);
    }
  };

  const memberId = useInput({
    placeholder: '아이디를 입력해 주세요',
    title: '아이디를 입력해 주세요',
    initialIsValid: false,
    onChange: checkMemberId,
    errorMessage: '중복된 아이디입니다',
  });

  const memberPassword = useInput({
    placeholder: '비밀번호를 입력해 주세요',
    title: '비밀번호를 입력해 주세요',
    initialIsValid: false,
    onChange: checkMemberPassword,
  });

  const memberCheckPassword = useInput({
    placeholder: '비밀번호를 다시 입력해 주세요',
    title: '비밀번호를 다시 입력해 주세요',
    initialIsValid: false,
    onChange: checkMemberCheckPassword,
    errorMessage: '비밀번호가 일치하지 않습니다',
  });

  const memberName = useInput({
    placeholder: '이름을 입력해 주세요',
    title: '이름을 입력해 주세요',
    initialIsValid: false,
    onChange: checkMemberName,
  });

  const memberNickname = useInput({
    placeholder: '닉네임을 입력해 주세요',
    title: '닉네임을 입력해 주세요',
    initialIsValid: false,
    onChange: checkMemberNickname,
    errorMessage: '중복된 닉네임입니다',
  });

  const memberType = useInput({
    placeholder: '등급을 입력해 주세요',
    title: '등급을 입력해 주세요',
    initialState: route.params.selectedType===1?'나눔이':'모음이',
  });

  const signUp = async () => {
    try {
      const res = await memberApi.signUp({
        memberSignId: memberId.text,
        memberSignPassword: memberPassword.text,
        memberName: memberName.text,
        memberNickname: memberNickname.text,
        disabled: route.params.selectedType===1?true:false,
        gender: selectedGender,
      })
      if (res.status === 200){
        console.log(res);
      }
    } catch (err) {
      console.log(err.response.data);
      console.log(err.response.status);
      console.log(err.response.headers);
    }
  }

  return (
    <CommonLayout headerType={0} footer={true}>
      <AuthTitle level="3" title="회원가입을 진행합니다." description={`간편한 회원가입으로 소소한${"\n"}행운을 쌓습니다`}/>

      <View style={SignUpInputStyle.signUpInputWrap}>
        <PlainInput {...memberId}/>
        <PlainInput {...memberPassword} secureTextEntry={true}/>
        <PlainInput {...memberCheckPassword} secureTextEntry={true}/>
        <PlainInput {...memberName}/>
        <PlainInput {...memberNickname}/>
        <PlainInput {...memberType} editable={false}/>
        <Text style={SignUpInputStyle.signUpInputText}>성별을 입력해주세요.</Text>
      </View>

      <AuthButton
          movePage="Main"
          isActive={memberId.isValid&&memberPassword.isValid&&memberCheckPassword.isValid&&memberName.isValid&&memberNickname.isValid}
          buttonText={'회원가입 완료하기'}
          goNext={signUp}
      />
    </CommonLayout>
  );
};

export default SignUpInput;
