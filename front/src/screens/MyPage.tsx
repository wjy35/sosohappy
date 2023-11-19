import React, {useEffect, useState, useRef, useCallback} from "react"
import {View, Text, Image, TouchableOpacity, Animated, Alert} from "react-native";
import CommonLayout from "@/components/CommonLayout";
import History from "@/components/History";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import FortuneModal from "@/components/FortuneModal";
import DialogInput from "react-native-dialog-input"

import GearIcon from "@/assets/img/gear-icon.png"
import BellIcon from "@/assets/img/bell-icon.png"
import BookIcon from "@/assets/img/book-icon.png"
import GrayMoreIcon from "@/assets/img/gray-more-icon.png"
import { siren } from "@/assets/icons/icons";

import MyPageStyle from "@/styles/MyPageStyle";

import { WebView } from "react-native-webview";
import SosomonDictionary from "@/components/SosomonDictionary";
import useStore from "@/store/store";
import monsterApi from "@/apis/monsterApi";
import memberApi from "@/apis/memberApi";
import { type1, type2, type3, type4 } from "@/assets/sosomon";
import { SvgXml } from "react-native-svg";
import { createUser } from "@/collections/users";
import {ChatSocket, helpSocket} from "@/types";
import memberReportApi from "@/apis/memberReportApi";

interface propsType{
  socket: helpSocket;
  chatSocket: ChatSocket
}

const MyPage = ({socket, chatSocket}: propsType) => {
  const navigation = useNavigation();
  const [modalState, setModalState] = useState<Boolean>(false);
  const {userInfo, updateSosomon} = useStore();
  const [myProfile, setMyProfile] = useState<any>(null);
  const [defaultSosomon, setDefaultSosomon] = useState<any>(null);
  const [fortuneModalState, setFortuneModalState] = useState<Boolean>(false);
  const [myClover, setMyClover] = useState<any>(null);
  const loaderValue = useRef(new Animated.Value(0)).current;
  const [profileMonsterType, setProfileMonsterType] = useState<number>(Math.floor((userInfo.profileMonsterId-1)/10) + 1);
  const [profileMonsterLevel, setProfileMonsterLevel] = useState<number>((userInfo.profileMonsterId % 10 === 0)?10:(userInfo.profileMonsterId%10));
  const [isDialogState, setIsDialogState] = useState<Boolean>(false);
  const [src, setSrc] = useState();

  const load = (initialWidth: number) => {
    Animated.timing(loaderValue, {
      toValue: (initialWidth) * 100,
      duration: 2000,
      useNativeDriver: false,
    }).start();
  }

  const width = loaderValue.interpolate({
    inputRange: [0, 100],
    outputRange: ["0%", "100%"],
    extrapolate: "clamp",
  })

  const updateModalState = (status: Boolean) => {
    setModalState(status);
  }

  const changeProfileMonster = async (profileType: number, profileLevel: number) => {
    const profileMonsterId = (profileType-1)*10 + profileLevel;
    try {
      const res = await memberApi.updateMember({
        profileMonsterId: profileMonsterId,
      })
      if (res.status === 200){
        setProfileMonsterType(profileType)
        setProfileMonsterLevel(profileLevel)
        updateSosomon(profileMonsterId)
      }
    } catch (err){
      console.log(err);
    }
  }

  const sendPoliceReport = async (inputText:string) => {
    if(inputText == ""){
      Alert.alert("사용자의 이름을 입력해주세요.");
      return;
    }

    const myInfoRes = await memberApi.getMember();
    if(myInfoRes.status === 200){
      const myMemberId = myInfoRes.data.result.member.memberId;

      const sirenRes = await memberReportApi.siren({reportingMemberId: myMemberId, reportedMemberId:28});
      if(sirenRes.status === 200){
        Alert.alert("신고 접수가 성공적으로 처리되었습니다.");
      }
    }

    setIsDialogState(false);
  }

  const updateFortuneModalState = (status: Boolean) => {
    setFortuneModalState(status);
  }

  const getMyCloverApi = async () => {
    const res = await monsterApi.getMyClover();
    setMyClover(res.data.result.clover);
  }

  const addClover = async () => {
    const res = await monsterApi.getMyClover();
    console.log("res", res);
    setMyClover(res.data.result.clover);
  }

  const setSosomon = (type:number, level:number) => {
    if (type === 1){
      setSrc(type1[level-1].src)
    } else if (type === 2){
      setSrc(type2[level-1].src)
    } else if (type === 3){
      setSrc(type3[level-1].src)
    } else {
      setSrc(type4[0].src)
    }
  }

  useEffect(()=>{
    setSosomon(profileMonsterType, profileMonsterLevel);
  }, [profileMonsterType, profileMonsterLevel])

  const police = () => {
    setIsDialogState(true);
  }

  useEffect(() => {
    if(defaultSosomon?.levelInfo?.currentClover){
      load((defaultSosomon?.levelInfo?.currentClover/defaultSosomon?.levelInfo.requiredClover));
    }
  }, [defaultSosomon]);

  useEffect(()=>{
    getMyCloverApi();
  }, [])

  useFocusEffect(
      React.useCallback(() => {
        const disConnect = () => {
          if (!socket.connected) return;
          socket.disConnect();
        }
        disConnect();
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

  return (
    <>
    <CommonLayout headerType={0} footer={true}>
      <View style={MyPageStyle.myProfileWrap}>
        <Image
            source={src?src:type4[0].src}
            style={MyPageStyle.myProfileImg}
        />
        <View style={MyPageStyle.myProfileInfo}>
          {
            userInfo &&
            <>
              <Text style={[MyPageStyle.myName]}>{userInfo.nickname}</Text>
                {
                  userInfo.disabled ?
                  <Text style={[MyPageStyle.myRank]}>나눔이</Text>
                  :
                  <Text style={[MyPageStyle.myRank]}>모음이</Text>
                }
            </>
          }
        </View>
        {/* <View style={MyPageStyle.myProfileIconWrap}>
          <TouchableOpacity activeOpacity={0.7} onPress={() => police()}>
            <View>
              <SvgXml
                xml={siren}
                style={MyPageStyle.myProfileGearIcon}
              />
            </View>
          </TouchableOpacity>
          <TouchableOpacity activeOpacity={0.7}>
            <View>
              <Image
                source={BellIcon}
                style={MyPageStyle.myProfileBellIcon}
              />
            </View>
          </TouchableOpacity>
        </View> */}
      </View>

      <View style={MyPageStyle.myPointInfo}>
          {
            myClover &&
            <Text style={MyPageStyle.myPoint}>{myClover.memberClover} Clover /{" "}
              <Text style={MyPageStyle.myAllPoint}>
                {myClover.memberAccruedClover} Clover
              </Text>
            </Text>
          }
        <Text style={MyPageStyle.myPointDesc}><Text style={MyPageStyle.myPointDescPoint}>행운력을</Text> 통해 성장시키세요!</Text>
      </View>

      <View style={MyPageStyle.ThumbnailCharacterWrap}>
        {
            profileMonsterType && (
                <WebView
                    source={{uri: `http://sosohappy.co.kr:8888/sosomon/${profileMonsterType}/${profileMonsterLevel}`}}
                    style={MyPageStyle.MySelectedCharImg}
                    nestedScrollEnabled
                />
            )
        }
        <TouchableOpacity activeOpacity={0.7} style={MyPageStyle.bookIconWrap} onPress={() => updateModalState(true)}>
          <Image
            source={BookIcon}
            style={MyPageStyle.bookIcon}
          />
        </TouchableOpacity>
      </View>

      {
          modalState && <SosomonDictionary updateModalState={updateModalState} changeProfileMonster={changeProfileMonster} modalState={modalState}/>
      }

      <View style={MyPageStyle.expWrap}>
        <View style={MyPageStyle.expTitleWrap}>
          <View style={MyPageStyle.expTitle}>
              {
                defaultSosomon &&
                <Text style={MyPageStyle.expMainTitle}>{defaultSosomon?.typeName}동물의 성장경험치</Text>
              }
            <Text style={MyPageStyle.expSubTitle}>당신의 선행력을 수치로 보여드려요</Text>
          </View>
          <TouchableOpacity activeOpacity={0.7} onPress={() => navigation.navigate('Character')} style={MyPageStyle.feedFlexBox}>
              <Image
                source={GrayMoreIcon}
                style={MyPageStyle.grayMoreIcon}
              />
              <Text style={MyPageStyle.moveFeedText}>먹이주러가기</Text>
          </TouchableOpacity>
        </View>

        <View style={MyPageStyle.statusWrap}>
          {
            defaultSosomon &&
            <Text style={MyPageStyle.statusPercent}>{defaultSosomon.levelInfo.currentClover + " Clover / " + defaultSosomon.levelInfo.requiredClover + " Clover"}</Text>
          }
          <View style={MyPageStyle.expBarWrap}>
            <View style={MyPageStyle.expBarBg}></View>
            {
              defaultSosomon &&
              <>
                {/* <View style={[MyPageStyle.expBarMy, {width:`${defaultSosomon.currentPoint*100 + "%"}`}]}></View> */}
                <Animated.View
                  style={{
                    width,
                    height:16,
                    backgroundColor:"#306E69",
                    borderRadius:50,
                    position:"absolute",
                  }}
                >

                </Animated.View>
              </>
            }
          </View>
          <Text style={MyPageStyle.expInfo}>진화까지 얼마 안남았어요! 새로운 행운력을 모아보세요!</Text>
        </View>
      </View>



      <View style={MyPageStyle.historyTitleWrap}>
        <Text style={MyPageStyle.historyTitle}>나의 최근 행운</Text>
        <History updateFortuneModalState={updateFortuneModalState} addClover={addClover}/>
      </View>
    </CommonLayout>
    {
      fortuneModalState &&
      <FortuneModal updateFortuneModalState={updateFortuneModalState}/>
    }
    {
      isDialogState &&
      <DialogInput isDialogVisible={isDialogState}
        title={"신고하기"}
        message={"어떤 사용자를 신고하시겠습니까?"}
        hintInput ={"이름을 입력해주세요."}
        submitInput={ (inputText: string) => sendPoliceReport(inputText) }
        closeDialog={ () => setIsDialogState(false) }>
      </DialogInput>
    }
    </>
  );
};

export default MyPage;
