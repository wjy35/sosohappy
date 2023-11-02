import { View, Text, TouchableOpacity, ImageBackground } from "react-native"
import { lock } from "@/assets/icons/icons";
import { SvgXml } from "react-native-svg";

import SosomonCardStyle from "@/styles/SosomonCardStyle"

interface propsType{
    src: HTMLImageElement,
    isLocked: Boolean,
    level: number,
    type: string,
    changeProfileMonster: Function,
}

const SosomonCard = ({src, isLocked, level, type, changeProfileMonster}: propsType) => {
    return(
        <>
            <View style={SosomonCardStyle.cardWrap}>
                <View style={SosomonCardStyle.characterRoomWrap}>
                    <ImageBackground
                        source={src}
                        resizeMode="cover"
                        imageStyle={isLocked&&SosomonCardStyle.lock}
                        style={[SosomonCardStyle.characterRoom]}
                    />
                    {
                        isLocked &&
                        <SvgXml
                            xml={lock}
                            style={SosomonCardStyle.lockIcon}
                        />
                    }
                </View>
                <View style={SosomonCardStyle.cardInfoFlexWrap}>
                    <View style={SosomonCardStyle.cardInfoWrap}>
                        <Text style={SosomonCardStyle.characterTypeLevel}>Lv.{level}</Text>
                        <Text style={SosomonCardStyle.characterName}>달에 사는 고래</Text>
                    </View>
                    <View>
                        <TouchableOpacity activeOpacity={0.7}>
                            {
                                isLocked ? (
                                        <View style={SosomonCardStyle.inActiveButton}>
                                            <Text style={SosomonCardStyle.inActiveButtonText}>커밍순</Text>
                                        </View>
                                    ):(
                                        <TouchableOpacity activeOpacity={0.7} onPress={()=>changeProfileMonster()}>
                                            <View style={SosomonCardStyle.activeButton}>
                                                <Text style={SosomonCardStyle.activeButtonText}>프로필 변경</Text>
                                            </View>
                                        </TouchableOpacity>
                                    )
                            }
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </>
    );
}

export default SosomonCard;
