import { View, Text, TouchableOpacity, ScrollView, Image } from "react-native"
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

import FeedAnimalImg1 from "@/assets/img/feed-animal1.png";
import FeedAnimalImg2 from "@/assets/img/feed-animal2.png";
import FeedAnimalImg3 from "@/assets/img/feed-animal3.png";
import CloverIcon from "@/assets/img/clover-icon.png"
import BearAnimationIcon from "@/assets/img/bear-animation-icon.png"

import CharacterStyle from "@/styles/CharacterStyle";

const Character = () => {
    return(
        <CommonLayout>
            <Header/>

            <View style={CharacterStyle.characterTitleWrap}>
                <Text style={CharacterStyle.characterTitle}>
                    <Text style={CharacterStyle.characterTitleMyName}>김싸피</Text> 님 어떤 캐릭터를{"\n"}
                    성장시킬까요?
                </Text>
                <TouchableOpacity activeOpacity={0.7}>
                    <View style={CharacterStyle.feedButton}>
                        <Text style={CharacterStyle.feedButtonText}>Amy 에게 먹이주기</Text>
                    </View>
                </TouchableOpacity>
            </View>

            <View style={CharacterStyle.feedAnimalWrap}>
                <ScrollView horizontal={true}>
                    <Image
                        source={FeedAnimalImg1}
                        style={CharacterStyle.feedAnimalImg}
                    />
                    <Image
                        source={FeedAnimalImg2}
                        style={CharacterStyle.feedAnimalImg}
                    />
                    <Image
                        source={FeedAnimalImg3}
                        style={CharacterStyle.feedAnimalImg}
                    />
                </ScrollView>
            </View>

            <View style={CharacterStyle.selectedCharaterWrap}>
                <Image
                    source={FeedAnimalImg1}
                    style={CharacterStyle.selectedCharacterImg}
                />
                <View style={CharacterStyle.selectedCharacterInfo}>
                    <Text style={CharacterStyle.selectedCharacterInfoTitle}>현재 성장 단계</Text>
                    <Text style={CharacterStyle.selectedCharacterInfoLevel}>초식동물 Lv.3</Text>
                </View>
            </View>
            <View style={CharacterStyle.expWrap}>
                <Text style={CharacterStyle.expTitle}>Exp.</Text>
                <View style={CharacterStyle.expStatusWrap}>
                    <View style={CharacterStyle.expStatusBg}></View>
                    <View style={CharacterStyle.expStatusMy}></View>
                </View>
            </View>


            <View style={CharacterStyle.animationButtonWrap}>
                <TouchableOpacity activeOpacity={0.7}>
                    <View style={CharacterStyle.animationButton}>
                        <Image
                            source={CloverIcon}
                            style={CharacterStyle.animationButtonThumbnail}
                        />
                        <Text style={CharacterStyle.animationButtonText}>행운 주기</Text>
                    </View>
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7}>
                    <View style={CharacterStyle.animationButton}>
                        <Image
                            source={BearAnimationIcon}
                            style={CharacterStyle.animationButtonThumbnail}
                        />
                        <Text style={CharacterStyle.animationButtonText}>애니메이션 보기</Text>
                    </View>
                </TouchableOpacity>
            </View>
            <Footer/>
        </CommonLayout>
    );
}

export default Character;