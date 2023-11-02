import { useEffect, useState } from "react";
import { View, Text, TouchableOpacity, ScrollView, ImageBackground, Image } from "react-native"
import CommonLayout from "@/components/CommonLayout";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
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

const Character = () => {
    const [categoryType, setCategoryType] = useState<CategoryType>(CategoryType.army);
    const [myMonsters, setMyMonsters] = useState<any[] | null>(null);

    useEffect(() => {
        const getMyDict = async () => {
            const collectedMonsterApi = await monsterApi.getMyDict();

            if(collectedMonsterApi.status === 200){
                setMyMonsters(collectedMonsterApi.data.result?.monsterList);
                console.log(myMonsters);
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
                <TouchableOpacity activeOpacity={0.7}>
                    <View style={CharacterStyle.feedButton}>
                        {
                            categoryType === CategoryType.army && myMonsters &&
                            <Text style={CharacterStyle.feedButtonText}>육식동물에게 먹이주기</Text>
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
                        myMonsters && 
                        <ImageBackground
                            source={type1[myMonsters[0].level]}
                            style={CharacterStyle.feedAnimalImg}
                            resizeMode="cover"
                        />
                    }
                    {
                        myMonsters &&
                        <ImageBackground
                            source={type2[myMonsters[1].level]}
                            style={CharacterStyle.feedAnimalImg}
                            resizeMode="cover"
                        />
                    }
                    {
                        myMonsters &&
                        <ImageBackground
                            source={type3[myMonsters[2].level]}
                            style={CharacterStyle.feedAnimalImg}
                            resizeMode="cover"
                        />
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
                        categoryType === CategoryType.army &&
                        <Text style={CharacterStyle.selectedCharacterInfoLevel}>육식동물 Lv.3</Text>
                    }
                    {
                        categoryType === CategoryType.navy &&
                        <Text style={CharacterStyle.selectedCharacterInfoLevel}>해양동물 Lv.3</Text>
                    }
                    {
                        categoryType === CategoryType.airForce &&
                        <Text style={CharacterStyle.selectedCharacterInfoLevel}>비행동물 Lv.3</Text>
                    }
                </View>
            </View>
            <View style={CharacterStyle.expWrap}>
                <Text style={CharacterStyle.expTitle}>Exp.</Text>
                <View style={CharacterStyle.expStatusWrap}>
                    <View style={CharacterStyle.expStatusBg}></View>
                    {
                        categoryType === CategoryType.army &&
                        <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[0].currentPoint) * 100}` + "%"}]}></View>
                    }
                    {
                        categoryType === CategoryType.navy &&
                        <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[1].currentPoint) * 100}` + "%"}]}></View>
                    }
                    {
                        categoryType === CategoryType.airForce &&
                        <View style={[CharacterStyle.expStatusMy, {width:`${Number(myMonsters[2].currentPoint) * 100}` + "%"}]}></View>
                    }
                    
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
        </CommonLayout>
    );
}

export default Character;