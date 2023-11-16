import {View, Text, Image, TouchableOpacity, Alert, ToastAndroid} from "react-native";
import CommonLayout from "@/components/CommonLayout";
import MainImg from "@/assets/img/main-img.png"
import HandShakeIcon from "@/assets/img/handshake-icon.png"
import MegaphoneIcon from "@/assets/img/megaphone-icon.png"
import CloverIcon from "@/assets/img/clover-icon.png"
import RightArrowIcon from "@/assets/img/right-arrow-icon.png"

import MainStyle from "@/styles/MainStyle";

import useStore from "@/store/store"
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import React, {useCallback, useEffect, useState} from "react";
import {ChatSocket, helpSocket} from "@/types";
import helpMatchApi from "@/apis/helpMatchApi";
import memberApi from "@/apis/memberApi";

interface propsType{
  socket: helpSocket,
  chatSocket: ChatSocket
}

const Main = ({socket, chatSocket}: propsType) => {
  const {userInfo, login, logout} = useStore();
  const navigation =  useNavigation();
  const [helpStatus, setHelpStatus] = useState('');

  const onToast = (text: string) => {
    ToastAndroid.showWithGravity(
        text,
        ToastAndroid.SHORT,
        ToastAndroid.TOP,
    )
  }

  const checkUserGoto = (next: string) => {
    userInfo?(navigation.navigate(next)):(navigation.navigate('Login'));
  }

  const checkDisabledGoto = (next: string) => {
    if (userInfo) {
      if (userInfo.disabled){
        if (helpStatus === 'WAIT_COMPLETE'){
          Alert.alert("도움요청 알림", "진행중인 도움요청이 있습니다. 완료하시겠습니까?",[
            {text: '완료하기', onPress: () => completeHelp()},
            {text: '지도로 가기', onPress: () => navigation.navigate('Map')},
          ])
        } else {
          navigation.navigate(next)
        }
      } else {
        onToast('나눔이만 가능한 기능입니다');
      }
    } else {
      navigation.navigate('Login')
    }
  }

  const getHelpStatus = async () => {
    try {
      const res = helpMatchApi.getHelpStatus();
      if (res.status === 200){
        setHelpStatus(res.data.result.helpMatchStatus)
      }
    } catch (err) {
      console.log(err)
    }
  }

  const getFortuneList = async () => {
    const fortuneList = await helpMatchApi.getFortuneList();
    if(fortuneList.status === 200 && userInfo){
      if(fortuneList.data.result.fortuneCookieList.length > 0){
        Alert.alert("포츈쿠키 알림이 도착했어요.", "열어보지 않은 포츈쿠키가 있어요. 마이페이지로 이동하시겠어요?", [
          {text: '취소', onPress: () => {}},
          {text: '이동하기', onPress: () => navigation.navigate("MyPage")}
        ]);
      }
    }
  }

  const completeHelp = async () => {
    try {
      const res = await helpMatchApi.helpComplete({memberId: userInfo.memberId});
      if (res.status === 200){
        console.log(res)
      }
    } catch (err) {
      console.log(err)
    }
  }

  useFocusEffect(
      React.useCallback(() => {
        if (userInfo){
          getHelpStatus();
        }
        socket.connected&&socket.disConnect();
        return () => {};
      }, [socket.connected])
  )

  useFocusEffect(
      useCallback(() => {
        const disConnect = () => {
          if (!chatSocket.connected) return;
          chatSocket.disConnect();
        }
        disConnect();
        return () => {};
      }, [chatSocket.connected])
  )

  useEffect(() => {
    if (!userInfo) return
    getFortuneList();
    getHelpStatus();
  }, [userInfo])

  useEffect(() => {
    if(helpStatus === "WAIT_COMPLETE"){
      Alert.alert("도움요청 알림", "진행중인 도움요청이 있습니다. 완료하시겠습니까?",[
        {text: '완료하기', onPress: () => completeHelp()},
        {text: '지도로 가기', onPress: () => navigation.navigate('Map')},
      ])
    }else if(helpStatus === "ON_MOVE"){
      Alert.alert("도움요청 알림", "도움요청 진행중입니다. 지도화면으로 이동합니다.", [
        {text: '이동하기', onPress: () => navigation.navigate('Map')}
      ]);
    }
  }, [helpStatus]);

  return (
    <CommonLayout footer={true} headerType={0} nowPage={'Main'}>
      <View style={MainStyle.mainWrap}>
        <Text style={MainStyle.mainTitle}>
          <Text style={MainStyle.pointBlue}>소소한 선행</Text>을 베풀고,{"\n"}
          <Text style={MainStyle.pointBlue}>클로버</Text>를 모아,{"\n"}
          여러분의 <Text style={MainStyle.pointBlue}>행운력</Text>을{"\n"}올리세요
        </Text>
        <Text style={MainStyle.mainDescription}>주변의 소소한 도움이 필요하신가요?</Text>
        <Image
          source={MainImg}
          style={MainStyle.mainImg}
        />
        <TouchableOpacity activeOpacity={0.7} onPress={() => checkUserGoto('Map')}>
          <View style={MainStyle.helpButton}>
            <Text style={MainStyle.helpButtonText}>도움 찾아가기</Text>
          </View>
        </TouchableOpacity>
        {
          !userInfo && (
                <TouchableOpacity activeOpacity={0.7} onPress={() => navigation.navigate('SignUpSeparate')}>
                  <Text style={MainStyle.signUpText}>회원이 아니신가요?</Text>
                </TouchableOpacity>
            )
        }
      </View>

      <View>
        <View style={MainStyle.boxWrap}>
          <Text style={MainStyle.boxMainTitle}>
            사소함에 고민되셨나요?{"\n"}
            소소한 도움을 얘기해주세요
          </Text>
          <Text style={MainStyle.boxSubTitle}>소소한 행복이 행운을 가져다줘요</Text>
        </View>
        <View style={MainStyle.boxFlexWrap}>
          <TouchableOpacity activeOpacity={0.7} onPress={() => checkDisabledGoto('CreateHelp')}>
            <View style={MainStyle.boxContentWrap}>
              <Text style={MainStyle.boxContentTitle}>
                도움이{"\n"}
                필요하신가요?
              </Text>
              <Text style={MainStyle.boxContentSubTitle}>글 작성하러 가기</Text>
              <Image
                source={HandShakeIcon}
                style={MainStyle.boxContentImg}
              />
            </View>
          </TouchableOpacity>

          <TouchableOpacity activeOpacity={0.7} onPress={() => checkUserGoto('Map')}>
            <View style={MainStyle.boxContentWrap}>
              <Text style={MainStyle.boxContentTitle}>
                소소한 행복을{"\n"}
                찾으시나요?
              </Text>
              <Text style={MainStyle.boxContentSubTitle}>주변의 행운 찾으러가기</Text>
              <Image
                source={MegaphoneIcon}
                style={MainStyle.boxContentImg}
              />
            </View>
          </TouchableOpacity>
        </View>
      </View>

      <View style={MainStyle.happyWrap}>

          {
            userInfo ?
            <View>
              <Text style={MainStyle.happyMainTitle}>{userInfo.name}님, 행운을 나누세요.</Text>
              <Text style={MainStyle.happySubTitle}>
                    오늘도 {userInfo.name}님의 소소한 행운이 더 많이{"\n"}
                    전해질 수 있도록
              </Text>
            </View>
            :
            <View>
              <Text style={MainStyle.happyMainTitle}>로그인을 하시면 소소몬을 만날 수 있어요.</Text>
              <Text style={MainStyle.happySubTitle}>
                    로그인을 하시면, 일상의 선행을 통해{"\n"}
                    캐릭터를 키우실 수 있어요.
              </Text>
            </View>
          }


        <TouchableOpacity activeOpacity={0.7} onPress={() => checkUserGoto('MyPage')}>
          <View style={MainStyle.moveMypageButton}>
            <Image
              source={CloverIcon}
              style={MainStyle.cloverImg}
            />
            <View style={MainStyle.moveMypageTextWrap}>
              <Text style={MainStyle.moveMypageTextTitle}>마이페이지</Text>
              <Text style={MainStyle.moveMypageTextDesc}>나의 행운점수를 확인하세요</Text>
            </View>
            <Image
              source={RightArrowIcon}
              style={MainStyle.rightArrowIcon}
            />
          </View>
        </TouchableOpacity>
      </View>
    </CommonLayout>
  );
};

export default Main;
