import { View, Text, TouchableOpacity, Image, ScrollView } from "react-native"
import {type1, type2, type3, type4} from '@/assets/sosomon/index'
import SosomonCard from "@/components/SosomaonCard"
import {fish, horse, bird} from "@/assets/icons/icons"
import { SvgXml } from "react-native-svg"

import SosomonDictionaryStyle from "@/styles/SosomonDictionaryStyle"

const SosomonDictionary = ({}) => {
    return (
        <>
            <View style={SosomonDictionaryStyle.modalBack}></View>
            <View style={SosomonDictionaryStyle.modalMain}>
                <View style={SosomonDictionaryStyle.collectionTitleWrap}>
                    <Text style={SosomonDictionaryStyle.collectionTitle}>Collection</Text>
                    <Text style={SosomonDictionaryStyle.collectionTitleDescription}>도감을 열어 새로운 소소몬을 만나세요.</Text>
                </View>
                
                <View style={SosomonDictionaryStyle.characterCard}>
                    <ScrollView
                        horizontal={true}
                        style={SosomonDictionaryStyle.characterScrollView}
                    >
                    {
                        type1.map((object, index) => {
                            return(
                                <>
                                    <SosomonCard src={object}/>
                                </>
                            );
                        })
                    }
                    </ScrollView>
                </View>

                <View style={SosomonDictionaryStyle.collectionCategory}>
                    <Text style={SosomonDictionaryStyle.collectionCategoryTitle}>더 많은 소소몬을 모아보세요.</Text>
                    <View style={SosomonDictionaryStyle.categoryFlexWrap}>
                        <TouchableOpacity activeOpacity={0.7}>
                            <View style={SosomonDictionaryStyle.categoryWrap}>
                                <SvgXml
                                    xml={fish}
                                    style={SosomonDictionaryStyle.categoryImg}
                                />
                                <Text style={SosomonDictionaryStyle.categoryText}>해양생물</Text>
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity activeOpacity={0.7}>
                            <View style={SosomonDictionaryStyle.categoryWrap}>
                                <SvgXml
                                    xml={bird}
                                    style={SosomonDictionaryStyle.categoryImg}
                                />
                                <Text style={SosomonDictionaryStyle.categoryText}>공중생물</Text>
                            </View>
                        </TouchableOpacity>
                        <TouchableOpacity activeOpacity={0.7}>
                            <View style={SosomonDictionaryStyle.categoryWrap}>
                                <SvgXml
                                    xml={horse}
                                    style={SosomonDictionaryStyle.categoryImg}
                                />
                                <Text style={SosomonDictionaryStyle.categoryText}>육식생물</Text>
                            </View>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </>
    )
}

export default SosomonDictionary;
