import {View, Text, Image, TouchableOpacity, ScrollView} from "react-native";
import CommonLayout from "@/components/CommonLayout";
import MainImg from "@/assets/img/main-img.png"
import HandShakeIcon from "@/assets/img/handshake-icon.png"
import MegaphoneIcon from "@/assets/img/megaphone-icon.png"
import CloverIcon from "@/assets/img/clover-icon.png"
import RightArrowIcon from "@/assets/img/right-arrow-icon.png"

import MainStyle from "@/styles/MainStyle";

import {observer} from 'mobx-react';
import useStore from "@/hooks/useStore";
import {useNavigation} from "@react-navigation/native";

const Main = observer(() => {
  const {userStore} = useStore();
  const navigation =  useNavigation();

  const goto = (next: string) => {
    userStore.user?(navigation.navigate(next)):(navigation.navigate('Login'));
  }

  return (
    <CommonLayout footer={true} headerType={0} nowPage={'Main'}>
      <View style={MainStyle.mainWrap}>
        <Text style={MainStyle.mainTitle}>
          소소한 선행을{"\n"}
          베풀고,{"\n"}
          <Text style={MainStyle.pointBlue}>여러분의 행운력{"\n"}
          을</Text> 올리세요
        </Text>
        <Text style={MainStyle.mainDescription}>주변의 소소한 도움이 필요하신가요?</Text>
        <Image
          source={MainImg}
          style={MainStyle.mainImg}
        />
        <TouchableOpacity activeOpacity={0.7}>
          <View style={MainStyle.helpButton}>
            <Text style={MainStyle.helpButtonText}>도움 찾아가기</Text>
          </View>
        </TouchableOpacity>
        {
          !userStore.user && (
                <TouchableOpacity activeOpacity={0.7}>
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
          <TouchableOpacity activeOpacity={0.7}>
            <View style={MainStyle.boxContentWrap}>
              <Text style={MainStyle.boxContentTitle}>
                도움이{"\n"}
                필요하신가요?
              </Text>
              <Text style={MainStyle.boxContentSubTitle}>글 작성하러 가기</Text>
            </View>
            <Image
              source={HandShakeIcon}
              style={MainStyle.boxContentImg}
            />
          </TouchableOpacity>

          <TouchableOpacity activeOpacity={0.7}>
            <View style={MainStyle.boxContentWrap}>
              <Text style={MainStyle.boxContentTitle}>
                소소한 행복을{"\n"}
                찾으시나요?
              </Text>
              <Text style={MainStyle.boxContentSubTitle}>주변의 행운 찾으러가기</Text>
            </View>
            <Image
              source={MegaphoneIcon}
              style={MainStyle.boxContentImg}
            />
          </TouchableOpacity>
        </View>
      </View>

      <View style={MainStyle.happyWrap}>
        <View>
          <Text style={MainStyle.happyMainTitle}>김싸피님, 행운을 나누세요.</Text>
          <Text style={MainStyle.happySubTitle}>
                오늘도 김싸피님의 소소한 행운이 더 많이{"\n"}
                전해질 수 있도록
          </Text>
        </View>

        <TouchableOpacity activeOpacity={0.7}>
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
});

export default Main;
