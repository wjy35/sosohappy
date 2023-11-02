import { useEffect, useState } from "react";
import { View, Text, TouchableOpacity, ScrollView, ImageBackground, Image, Alert } from "react-native"
import CommonLayout from "@/components/CommonLayout";
import monsterApi from "@/apis/monsterApi";

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

const Character = () => {
    const [categoryType, setCategoryType] = useState<CategoryType>(CategoryType.army);
    const [myMonsters, setMyMonsters] = useState<any[] | null>(null);

    const feedSosomonCommon = async ({feedType}: feedTypes) => {
        if(myMonsters){
            const levelUpApi = await monsterApi.levelUp({
                memberMonsterId: myMonsters[feedType].memberMonsterId,
                clover: 1,
            });
            console.log("levelUpApi", levelUpApi);
            if(levelUpApi.status === 200){
                if(levelUpApi.data.message === "보유중인 클로버가 부족합니다."){
                    Alert.alert("보유중인 클로버가 부족하여 먹이를 줄 수 없습니다.");
                }else if(levelUpApi.data.message === "경험치 등록 완료"){
                    Alert.alert("소소몬에게 성공적으로 먹이를 주었습니다.");
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

    return(
        <CommonLayout headerType={0} footer={false}>

            <View style={CharacterStyle.characterTitleWrap}>
                <Text style={CharacterStyle.characterTitle}>
                    <Text style={CharacterStyle.characterTitleMyName}>김싸피</Text> 님 어떤 캐릭터를{"\n"}
                    성장시킬까요?
                </Text>
                <TouchableOpacity activeOpacity={0.7} onPress={() => feedSosomon()}>
                    <View style={CharacterStyle.feedButton}>
                        {
                            categoryType === CategoryType.army && myMonsters &&
                            <Text style={CharacterStyle.feedButtonText}>육지동물에게 먹이주기</Text>
                        }
                        {
                            categoryType === CategoryType.navy && myMonsters &&
                            <Text style={CharacterStyle.feedButtonText}>해양동물에게 먹이주기</Text>
                        }
                        {
                            categoryType === CategoryType.airForce && myMonsters &&
                            <Text style={CharacterStyle.feedButtonText}>비행동물에게 먹이주기</Text>
                        }
                    </View>
                </TouchableOpacity>
            </View>

            <View style={CharacterStyle.feedAnimalWrap}>
                <ScrollView
                    horizontal={true}
                    onScrollEndDrag={(x: any) => {
                        console.log(x.nativeEvent.contentOffset.x );
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
                <Text style={CharacterStyle.expTitle}>Exp.</Text>
                <View style={CharacterStyle.expStatusWrap}>
                    <View style={CharacterStyle.expStatusBg}></View>
                    {
                        categoryType === CategoryType.army && myMonsters &&
                        <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[0].currentPoint) * 100}` + "%"}]}></View>
                    }
                    {
                        categoryType === CategoryType.navy && myMonsters &&
                        <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[1].currentPoint) * 100}` + "%"}]}></View>
                    }
                    {
                        categoryType === CategoryType.airForce && myMonsters &&
                        <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[2].currentPoint) * 100}` + "%"}]}></View>
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