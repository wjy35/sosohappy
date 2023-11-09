import { useEffect, useState, useRef } from "react";
import { View, Text, TouchableOpacity, ScrollView, ImageBackground, Image, Alert, Animated } from "react-native"
import CommonLayout from "@/components/CommonLayout";
import monsterApi from "@/apis/monsterApi";
import useStore from "@/store/store";
import {useFocusEffect, useNavigation} from "@react-navigation/native";

import CloverIcon from "@/assets/img/clover-icon.png"
import BearAnimationIcon from "@/assets/img/bear-animation-icon.png"

import { type1, type2, type3, type4 } from "@/assets/sosomon";

import CharacterStyle from "@/styles/CharacterStyle";

enum CategoryType{
    "army",
    "navy",
    "airForce"
}

interface feedTypes{
    feedType: number,
}

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

const Character = ({socket}: propsType) => {
    const [categoryType, setCategoryType] = useState<CategoryType>(CategoryType.army);
    const [myMonsters, setMyMonsters] = useState<any[] | null>(null);
    const loaderValue = useRef(new Animated.Value(0)).current;
    const {userInfo} = useStore();
    const navigation = useNavigation();

    const feedSosomonCommon = async ({feedType}: feedTypes) => {
        if(myMonsters){
            const levelUpApi = await monsterApi.levelUp({
                memberMonsterId: myMonsters[feedType].memberMonsterId,
                clover: 1,
            });
            console.log("levelUpApi", levelUpApi.data.message);
            if(levelUpApi.status === 200){
                if(levelUpApi.data.message === "보유중인 클로버가 부족합니다."){
                    Alert.alert("보유중인 클로버가 부족하여 먹이를 줄 수 없습니다.");
                }else if(levelUpApi.data.message === "클로버를 성공적으로 반영하였습니다."){
                    Alert.alert("소소몬에게 성공적으로 먹이를 주었습니다.");
                    navigation.navigate("MyPage");
                    navigation.navigate("Character");
                }
            }else{
                Alert.alert("시스템 오류, 관리자에게 문의하세요.");
            }
        }
    }

    const feedSosomon = async () => {
        switch(categoryType){
            case CategoryType.army:
                feedSosomonCommon({
                    feedType:0,
                });
                break;
            case CategoryType.navy:
                feedSosomonCommon({
                    feedType:1,
                });
                break;
            case CategoryType.airForce:
                feedSosomonCommon({
                    feedType:2,
                });
                break;
        }
    }

    const load = (initialWidth: number) => {
        Animated.timing(loaderValue, {
            toValue: (initialWidth) * 100,
            duration: 1500,
            useNativeDriver: false,
        }).start();
    }

    const width = loaderValue.interpolate({
        inputRange: [0, 100],
        outputRange: ["0%", "100%"],
        extrapolate: "clamp",
    })

    useEffect(() => {
        if(myMonsters){
            switch(categoryType){
                case CategoryType.army:
                    load(myMonsters[0].currentPoint);
                    break;
                case CategoryType.navy:
                    load(myMonsters[1].currentPoint);
                    break;
                case CategoryType.airForce:
                    load(myMonsters[2].currentPoint);
                    break;
            }
        }
      }, [myMonsters, categoryType]);

    useEffect(() => {
        const getMyDict = async () => {
            const collectedMonsterApi = await monsterApi.getMyDict();

            if(collectedMonsterApi.status === 200){
                setMyMonsters(collectedMonsterApi.data.result?.monsterList);
                console.log("myMonsters", myMonsters);
            }
        }
        getMyDict();
    }, [])

    useFocusEffect(()=>{
        if (!socket.connected) return;
        socket.disConnect();
    })

    return(
        <CommonLayout headerType={0} footer={true}>

            <View style={CharacterStyle.characterTitleWrap}>
                {
                    userInfo.name &&
                    <Text style={CharacterStyle.characterTitle}>
                        <Text style={CharacterStyle.characterTitleMyName}>{userInfo.name}</Text> 님 어떤 캐릭터를{"\n"}
                        성장시킬까요?
                    </Text>
                }
            </View>

            <View style={CharacterStyle.feedAnimalWrap}>
                <ScrollView
                    horizontal={true}
                    onScroll={(x: any) => {
                        // console.log(x.nativeEvent.contentOffset.x );
                        if(x.nativeEvent.contentOffset.x > 405){
                            setCategoryType(CategoryType.airForce);
                        }else if(x.nativeEvent.contentOffset.x > 185){
                            setCategoryType(CategoryType.navy);
                        }else{
                            setCategoryType(CategoryType.army);
                        }
                    }}
                >

                    {
                        myMonsters && (
                            <View style={categoryType === CategoryType.army && CharacterStyle.feedAnimalActive}>
                                <ImageBackground
                                    source={type1[myMonsters[0].level]}
                                    style={CharacterStyle.feedAnimalImg}
                                    resizeMode="cover"
                                />
                            </View>
                        )
                    }
                    {
                        myMonsters && (
                            <View style={categoryType === CategoryType.navy && CharacterStyle.feedAnimalActive}>
                                <ImageBackground
                                    source={type2[myMonsters[1].level]}
                                    style={CharacterStyle.feedAnimalImg}
                                    resizeMode="cover"
                                />
                            </View>
                            )
                    }
                    {
                        myMonsters && (
                            <View style={categoryType === CategoryType.airForce && CharacterStyle.feedAnimalActive}>
                                <ImageBackground
                                    source={type3[myMonsters[2].level]}
                                    style={CharacterStyle.feedAnimalImg}
                                    resizeMode="cover"
                                />
                            </View>
                            )
                    }
                </ScrollView>
            </View>

            <View style={CharacterStyle.selectedCharaterWrap}>
                {
                    categoryType === CategoryType.army && myMonsters &&
                    <Image
                        source={type1[myMonsters[0].level]}
                        style={CharacterStyle.selectedCharacterImg}
                    />
                }
                {
                    categoryType === CategoryType.navy && myMonsters &&
                    <Image
                        source={type2[myMonsters[1].level]}
                        style={CharacterStyle.selectedCharacterImg}
                    />
                }
                {
                    categoryType === CategoryType.airForce && myMonsters &&
                    <Image
                        source={type3[myMonsters[2].level]}
                        style={CharacterStyle.selectedCharacterImg}
                    />
                }
                <View style={CharacterStyle.selectedCharacterInfo}>
                    <Text style={CharacterStyle.selectedCharacterInfoTitle}>현재 성장 단계</Text>
                    {
                        categoryType === CategoryType.army && myMonsters &&
                        <Text style={CharacterStyle.selectedCharacterInfoLevel}>육지동물 Lv.{myMonsters[0].level}</Text>
                    }
                    {
                        categoryType === CategoryType.navy && myMonsters &&
                        <Text style={CharacterStyle.selectedCharacterInfoLevel}>해양동물 Lv.{myMonsters[1].level}</Text>
                    }
                    {
                        categoryType === CategoryType.airForce && myMonsters &&
                        <Text style={CharacterStyle.selectedCharacterInfoLevel}>비행동물 Lv.{myMonsters[2].level}</Text>
                    }
                </View>
            </View>
            <View style={CharacterStyle.expWrap}>
                <Text style={CharacterStyle.expTitle}>
                    Exp.
                    {
                        categoryType === CategoryType.army && myMonsters &&
                        Number(myMonsters[0].currentPoint) * 100 + "%"
                    }
                    {
                        categoryType === CategoryType.navy && myMonsters &&
                        Number(myMonsters[1].currentPoint) * 100 + "%"
                    }
                    {
                        categoryType === CategoryType.airForce && myMonsters &&
                        Number(myMonsters[2].currentPoint) * 100 + "%"
                    }
                </Text>
                <View style={CharacterStyle.expStatusWrap}>
                    <View style={CharacterStyle.expStatusBg}></View>
                    {
                        categoryType === CategoryType.army && myMonsters &&
                        <Animated.View
                        style={{
                            width,
                            height:28,
                            borderRadius:10,
                            backgroundColor:"#8B8BEF",
                            position:"absolute",
                        }}></Animated.View>
                        // <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[0].currentPoint) * 100}` + "%"}]}></View>
                    }
                    {
                        categoryType === CategoryType.navy && myMonsters &&
                        <Animated.View
                        style={{
                            width,
                            height:28,
                            borderRadius:10,
                            backgroundColor:"#8B8BEF",
                            position:"absolute",
                        }}></Animated.View>
                        // <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[1].currentPoint) * 100}` + "%"}]}></View>
                    }
                    {
                        categoryType === CategoryType.airForce && myMonsters &&
                        <Animated.View
                        style={{
                            width,
                            height:28,
                            borderRadius:10,
                            backgroundColor:"#8B8BEF",
                            position:"absolute",
                        }}></Animated.View>
                        // <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[2].currentPoint) * 100}` + "%"}]}></View>
                    }

                </View>
            </View>


            <View style={CharacterStyle.animationButtonWrap}>
                <TouchableOpacity activeOpacity={0.7} onPress={() => feedSosomon()}>
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
        </CommonLayout>
    );
}

export default Character;
