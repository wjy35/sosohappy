import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import History from "@/components/History";

import FishThumbnail from "@/assets/img/fish-thumbnail.png"
import GearIcon from "@/assets/img/gear-icon.png"
import BellIcon from "@/assets/img/bell-icon.png"
import BookIcon from "@/assets/img/book-icon.png"
import GrayMoreIcon from "@/assets/img/gray-more-icon.png"

import MyPageStyle from "@/styles/MyPageStyle";

const MyPage = () => {
  return (
    <CommonLayout>
      <Header/>
        <View style={MyPageStyle.myProfileWrap}>
          <Image
            source={FishThumbnail}
            style={MyPageStyle.myProfileImg}
          />
          <View style={MyPageStyle.myProfileInfo}>
            <Text style={[MyPageStyle.myName]}>김싸피</Text>
            <Text style={[MyPageStyle.myRank]}>모음이</Text>
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
          <Text style={MyPageStyle.myPoint}>214P / <Text style={MyPageStyle.myAllPoint}>2400P</Text></Text>
          <Text style={MyPageStyle.myPointDesc}><Text style={MyPageStyle.myPointDescPoint}>행운력을</Text> 통해 성장시키세요!</Text>
        </View>


        <View style={MyPageStyle.ThumbnailCharacterWrap}>
          <Image
            source={FishThumbnail}
            style={MyPageStyle.MySelectedCharImg}
          />
          <TouchableOpacity activeOpacity={0.7} style={MyPageStyle.bookIconWrap}>
            <Image
              source={BookIcon}
              style={MyPageStyle.bookIcon}
            />
          </TouchableOpacity>
        </View>


        <View style={MyPageStyle.expWrap}>
          <View style={MyPageStyle.expTitleWrap}>
            <View style={MyPageStyle.expTitle}>
              <Text style={MyPageStyle.expMainTitle}>Amy의 성장경험치</Text>
              <Text style={MyPageStyle.expSubTitle}>당신의 선행력을 수치로 보여드려요</Text>
            </View>
            <TouchableOpacity activeOpacity={0.7}>
              <Image
                source={GrayMoreIcon}
                style={MyPageStyle.grayMoreIcon}
              />
            </TouchableOpacity>
          </View>

          <View style={MyPageStyle.statusWrap}>
            <Text style={MyPageStyle.statusPercent}>28%</Text>
            <View style={MyPageStyle.expBarWrap}>
              <View style={MyPageStyle.expBarBg}></View>
              <View style={MyPageStyle.expBarMy}></View>
            </View>
            <Text style={MyPageStyle.expInfo}>진화까지 얼마 안남았어요! 새로운 행운력을 모아보세요!</Text>
          </View>
        </View>


        <History/>

      <Footer/>
    </CommonLayout>
  );
};

export default MyPage;