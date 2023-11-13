import {View, Text, TextInput, TouchableOpacity} from "react-native";
import CommonLayout from "@/components/CommonLayout";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import SignUpInputStyle from "@/styles/SignUpInputStyle";
import {useEffect, useState} from "react";
import PlainInput from "@/components/PlainInput";
import useInput from "@/hooks/useInput";
import {useFocusEffect, useNavigation, useRoute} from "@react-navigation/native";
import memberApi from "@/apis/memberApi";
import DropDownPicker from "react-native-dropdown-picker";
import InputStyle from "@/styles/InputStyle";
import {SvgXml} from "react-native-svg";
import {backIcon, check} from "@/assets/icons/icons";

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

const SignUpInput = ({socket}: propsType) => {
  const route = useRoute();
  const [isActive, setIsActive] = useState(false);
  const [selectedGender, setSelectedGender] = useState(2);
  const navigation = useNavigation();
  const [open, setOpen] = useState(false);


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
    initialIsValid: route.params?.name?true:false,
    onChange: checkMemberName,
    initialState: route.params?.name?route.params.name:'',
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
    // console.log(selectedGender);
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
        navigation.replace('Login');
      }
    } catch (err) {
      console.log(err);
    }
  };


  const selectGender = (gender: number) => {
    setOpen(false);
    setSelectedGender(gender);
  }

  useFocusEffect(()=>{
    if (!socket.connected) return;
    socket.disConnect();
  })

  return (
    <CommonLayout headerType={0} footer={true}>
      <AuthTitle level="3" title="회원가입을 진행합니다." description={`간편한 회원가입으로 소소한${"\n"}행운을 쌓습니다`}/>

      <View style={SignUpInputStyle.signUpInputWrap}>
        <PlainInput {...memberId}/>
        <PlainInput {...memberPassword} secureTextEntry={true}/>
        <PlainInput {...memberCheckPassword} secureTextEntry={true}/>
        <PlainInput {...memberName} editable={route.params?.name?false:true}/>
        <PlainInput {...memberNickname}/>
        <PlainInput {...memberType} editable={false}/>
        <Text style={SignUpInputStyle.signUpInputText}>성별을 입력해주세요.</Text>
        <TouchableOpacity activeOpacity={0.7} onPress={()=>setOpen(!open)}>
          <View style={[InputStyle.Input, {flexDirection: 'row', justifyContent: 'space-between'}]}>
            <View style={{justifyContent: 'center'}}>
              <Text style={[InputStyle.text]}>{selectedGender===1&&'남'}{selectedGender===0&&'여'}{selectedGender===2&&'성별을 선택해주세요'}</Text>
            </View>
            <View>
                <SvgXml
                    xml={backIcon}
                    width={32}
                    height={32}
                    rotation={open?90:270}
                />
            </View>
          </View>
        </TouchableOpacity>
        {
          open && (
                <View style={{top: -10, backgroundColor: 'white'}}>
                  <TouchableOpacity onPress={()=>{selectGender(1)}} activeOpacity={0.7}>
                    <View style={{height: 50, borderWidth: 1, padding:10, flexDirection: 'row', justifyContent: 'space-between'}}>
                      <View style={{justifyContent: 'center'}}>
                        <Text style={InputStyle.text}>남</Text>
                      </View>
                      {
                        selectedGender===1 && (
                              <View>
                                <SvgXml
                                    xml={check}
                                    width={26}
                                    height={26}
                                />
                              </View>
                          )
                      }
                    </View>
                  </TouchableOpacity>
                  <TouchableOpacity onPress={()=>{selectGender(0)}} activeOpacity={0.7}>
                    <View style={{height: 50, borderWidth: 1, borderTopWidth: 0, padding:10, borderBottomLeftRadius: 16, borderBottomRightRadius: 16, flexDirection: 'row', justifyContent: 'space-between'}}>
                      <View style={{justifyContent: 'center'}}>
                        <Text style={InputStyle.text}>여</Text>
                      </View>
                      {
                          selectedGender===0 && (
                              <View>
                                <SvgXml
                                    xml={check}
                                    width={26}
                                    height={26}
                                />
                              </View>
                          )
                      }
                    </View>
                  </TouchableOpacity>
                </View>
            )
        }
      </View>

      <AuthButton
          movePage="Main"
          isActive={memberId.isValid&&memberPassword.isValid&&memberCheckPassword.isValid&&memberName.isValid&&memberNickname.isValid&&(selectedGender<2)}
          buttonText={'회원가입 완료하기'}
          goNext={signUp}
      />
    </CommonLayout>
  );
};

export default SignUpInput;
