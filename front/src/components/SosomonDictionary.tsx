import { useState } from "react"
import { View, Text, TouchableOpacity, Image, ScrollView } from "react-native"
import {type1, type2, type3, type4} from '@/assets/sosomon/index'
import SosomonCard from "@/components/SosomaonCard"
import {fish, fishActive, horse, horseActive, bird, birdActive} from "@/assets/icons/icons"
import { SvgXml } from "react-native-svg"
import { close } from "@/assets/icons/icons"

import SosomonDictionaryStyle from "@/styles/SosomonDictionaryStyle"

interface propsType{
    updateModalState: Function,
}

enum CategoryEnum{
    "army",
    "navy",
    "airForce",
}

const SosomonDictionary = ({updateModalState}: propsType) => {
    const [categoryType, setCategoryType] = useState<CategoryEnum>(CategoryEnum.navy);
    const updateCategory = (categoryStatus: CategoryEnum) => {
        setCategoryType(categoryStatus);
    }
    return (
        <>
            <View style={SosomonDictionaryStyle.modalBack}></View>
            <View style={SosomonDictionaryStyle.modalMain}>
                <TouchableOpacity activeOpacity={0.7} onPress={() => updateModalState(false)}>
                    <View style={SosomonDictionaryStyle.closeIconWrap}>
                        <SvgXml
                            xml={close}
                            style={SosomonDictionaryStyle.closeIcon}
                        />
                    </View>
                </TouchableOpacity>
                <View style={SosomonDictionaryStyle.collectionTitleWrap}>
                    <Text style={SosomonDictionaryStyle.collectionTitle}>Collection</Text>
                    <Text style={SosomonDictionaryStyle.collectionTitleDescription}>도감을 열어 새로운 소소몬을 만나세요.</Text>
                </View>
                
                <View>
                    <ScrollView
                        horizontal={true}
                        style={SosomonDictionaryStyle.characterScrollView}
                    >
                    {
                        categoryType === CategoryEnum.army &&
                        type1.map((object, index) => {
                            return(
                                <>
                                    <SosomonCard src={object} isLocked={true}/>
                                </>
                            );
                        })
                    }
                    {
                        categoryType === CategoryEnum.airForce &&
                        type3.map((object, index) => {
                            return(
                                <>
                                    <SosomonCard src={object} isLocked={false}/>
                                </>
                            );
                        })
                    }
                    {
                        categoryType === CategoryEnum.navy &&
                        type2.map((object, index) => {
                            return(
                                <>
                                    <SosomonCard src={object} isLocked={false}/>
                                </>
                            );
                        })
                    }
                    </ScrollView>
                </View>

                <View style={SosomonDictionaryStyle.collectionCategory}>
                    <Text style={SosomonDictionaryStyle.collectionCategoryTitle}>더 많은 소소몬을 모아보세요.</Text>
                    <View style={SosomonDictionaryStyle.categoryFlexWrap}>
                        <TouchableOpacity activeOpacity={0.7} onPress={() => updateCategory(CategoryEnum.navy)}>
                            {
                                categoryType === CategoryEnum.navy ?
                                <View style={[SosomonDictionaryStyle.categoryWrap, SosomonDictionaryStyle.categoryActiveWrap]}>
                                    <SvgXml
                                        xml={fishActive}
                                        style={SosomonDictionaryStyle.categoryImg}
                                    />
                                    <Text style={[SosomonDictionaryStyle.categoryText, SosomonDictionaryStyle.categoryActiveText]}>해양생물</Text>
                                </View>
                                :
                                <View style={SosomonDictionaryStyle.categoryWrap}>
                                    <SvgXml
                                        xml={fish}
                                        style={SosomonDictionaryStyle.categoryImg}
                                    />
                                    <Text style={SosomonDictionaryStyle.categoryText}>해양생물</Text>
                                </View>
                            }
                        </TouchableOpacity>
                        <TouchableOpacity activeOpacity={0.7} onPress={() => updateCategory(CategoryEnum.airForce)}>
                            {
                                categoryType === CategoryEnum.airForce ?
                                <View style={[SosomonDictionaryStyle.categoryWrap, SosomonDictionaryStyle.categoryActiveWrap]}>
                                    <SvgXml
                                        xml={birdActive}
                                        style={SosomonDictionaryStyle.categoryImg}
                                    />
                                    <Text style={[SosomonDictionaryStyle.categoryText, SosomonDictionaryStyle.categoryActiveText]}>공중생물</Text>
                                </View>
                                :
                                <View style={SosomonDictionaryStyle.categoryWrap}>
                                    <SvgXml
                                        xml={bird}
                                        style={SosomonDictionaryStyle.categoryImg}
                                    />
                                    <Text style={SosomonDictionaryStyle.categoryText}>공중생물</Text>
                                </View>
                            }
                            
                        </TouchableOpacity>
                        <TouchableOpacity activeOpacity={0.7} onPress={() => updateCategory(CategoryEnum.army)}>
                            {
                                categoryType === CategoryEnum.army ?
                                <View style={[SosomonDictionaryStyle.categoryWrap, SosomonDictionaryStyle.categoryActiveWrap]}>
                                    <SvgXml
                                        xml={horseActive}
                                        style={SosomonDictionaryStyle.categoryImg}
                                    />
                                    <Text style={[SosomonDictionaryStyle.categoryText, SosomonDictionaryStyle.categoryActiveText]}>육식생물</Text>
                                </View>
                                :
                                <View style={SosomonDictionaryStyle.categoryWrap}>
                                    <SvgXml
                                        xml={horse}
                                        style={SosomonDictionaryStyle.categoryImg}
                                    />
                                    <Text style={SosomonDictionaryStyle.categoryText}>육식생물</Text>
                                </View>
                            }
                            
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </>
    )
}

export default SosomonDictionary;
