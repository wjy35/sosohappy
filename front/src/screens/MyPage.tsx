import {useEffect, useState, useRef} from "react"
import {View, Text, Image, TouchableOpacity, Animated} from "react-native";
import CommonLayout from "@/components/CommonLayout";
import History from "@/components/History";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import FortuneModal from "@/components/FortuneModal";

import FishThumbnail from "@/assets/img/fish-thumbnail.png"
import GearIcon from "@/assets/img/gear-icon.png"
import BellIcon from "@/assets/img/bell-icon.png"
import BookIcon from "@/assets/img/book-icon.png"
import GrayMoreIcon from "@/assets/img/gray-more-icon.png"

import MyPageStyle from "@/styles/MyPageStyle";

import { WebView } from "react-native-webview";
import SosomonDictionary from "@/components/SosomonDictionary";
import {observer} from "mobx-react";
import useStore from "@/hooks/useStore";
import monsterApi from "@/apis/monsterApi";
import memberApi from "@/apis/memberApi";
import { type1, type2, type3, type4 } from "@/assets/sosomon";

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

const MyPage = observer(({socket}: propsType) => {
  const navigation = useNavigation();
  const [modalState, setModalState] = useState<Boolean>(false);
  const {userStore} = useStore();
  const [myProfile, setMyProfile] = useState<any>(null);
  const [defaultSosomon, setDefaultSosomon] = useState<any>(null);
  const [fortuneModalState, setFortuneModalState] = useState<Boolean>(false);
  const [myClover, setMyClover] = useState<any>(null);
  const loaderValue = useRef(new Animated.Value(0)).current;
  const [profileMonsterType, setProfileMonsterType] = useState<number>(0);
  const [profileMonsterLevel, setProfileMonsterLevel] = useState<number>(0);

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

  const getProfileMonster = async () => {
    try {
      const res = await monsterApi.getMyDetail();
      if (res.status === 200){
        setDefaultSosomon(res.data.result.monster);
        setMyProfile({
          type: res.data.result.monster.typeId,
          level: res.data.result.monster.level,
        })
      }
    } catch (err) {
      console.log(err);
    }
  }

  const changeProfileMonster = async (profileType: number, profileLevel: number) => {
    const profileMonsterId = (profileType-1)*10 + profileLevel;
    console.log(profileMonsterId);
    try {
      const res = await memberApi.updateMember({
        profileMonsterId: profileMonsterId,
      })
      if (res.status === 200){
        setMyProfile({
          type: profileType,
          level: profileLevel,
        })
      }
    } catch (err){
      console.log(err);
    }
  }

  const updateFortuneModalState = (status: Boolean) => {
    setFortuneModalState(status);
  }

  const getMyCloverApi = async () => {
    const res = await monsterApi.getMyClover();
    setMyClover(res.data.result.clover);
  }

  const whatIsMyThumbnail = () => {
    if(userStore.user.profileMonsterId){
      setProfileMonsterType(Math.floor((userStore.user.profileMonsterId-1)/10));
      setProfileMonsterLevel((userStore.user.profileMonsterId % 10 === 0)?9:(userStore.user.profileMonsterId%10)-1);
    }
  }

  useEffect(() => {
    if(defaultSosomon?.currentPoint){
      load(defaultSosomon.currentPoint);
    }
  }, [defaultSosomon]);

  useEffect(()=>{
    getProfileMonster();
    getMyCloverApi();
  }, [])

  useEffect(() => {
    whatIsMyThumbnail();
  }, [])

  useFocusEffect(()=>{
    if (!socket.connected) return;
    socket.disConnect();
  })

  return (
    <>
    <CommonLayout headerType={0} footer={true}>
      <View style={MyPageStyle.myProfileWrap}>
        {
          profileMonsterType === 0 &&
          <Image
            source={type1[profileMonsterLevel]}
            style={MyPageStyle.myProfileImg}
          />
        }
        {
          profileMonsterType === 1 &&
          <Image
            source={type2[profileMonsterLevel]}
            style={MyPageStyle.myProfileImg}
          />
        }
        {
          profileMonsterType === 2 &&
          <Image
            source={type3[profileMonsterLevel]}
            style={MyPageStyle.myProfileImg}
          />
        }
        {
          profileMonsterType === 3 &&
          <Image
            source={type4[profileMonsterLevel]}
            style={MyPageStyle.myProfileImg}
          />
        }

        <View style={MyPageStyle.myProfileInfo}>
          {
            userStore.user &&
            <>
              <Text style={[MyPageStyle.myName]}>{userStore.user.name}</Text>
                {
                  userStore.user.disabled ?
                  <Text style={[MyPageStyle.myRank]}>나눔이</Text>
                  :
                  <Text style={[MyPageStyle.myRank]}>모음이</Text>
                }
            </>
          }
        </View>
        <View style={MyPageStyle.myProfileIconWrap}>
          <TouchableOpacity activeOpacity={0.7}>
            <View>
              <Image
                source={GearIcon}
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
        </View>
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
          myProfile && (
                <WebView
                    source={{uri: `http://sosohappy.co.kr:8888/sosomon/${myProfile.type}/${myProfile.level}`}}
                    style={MyPageStyle.MySelectedCharImg}
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
          modalState && <SosomonDictionary updateModalState={updateModalState} changeProfileMonster={changeProfileMonster}/>
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
          <TouchableOpacity activeOpacity={0.7} onPress={() => navigation.navigate('Character')}>
            <Image
              source={GrayMoreIcon}
              style={MyPageStyle.grayMoreIcon}
            />
          </TouchableOpacity>
        </View>

        <View style={MyPageStyle.statusWrap}>
          {
            defaultSosomon &&
            <Text style={MyPageStyle.statusPercent}>{defaultSosomon.currentPoint*100 + "%"}</Text>
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
        <History updateFortuneModalState={updateFortuneModalState}/>
        {/*<TouchableOpacity activeOpacity={0.7}>*/}
        {/*    <View style={MyPageStyle.moreButton}>*/}
        {/*        <Text style={MyPageStyle.moreButtonText}>더보기</Text>*/}
        {/*    </View>*/}
        {/*</TouchableOpacity>*/}
      </View>
    </CommonLayout>
    {
      fortuneModalState &&
      <FortuneModal updateFortuneModalState={updateFortuneModalState}/>
    }
    </>
  );
});

export default MyPage;
