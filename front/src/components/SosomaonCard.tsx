import { View, Text, Image, TouchableOpacity, ImageBackground } from "react-native"

import SosomonCardStyle from "@/styles/SosomonCardStyle"

interface propsType{
    src: HTMLImageElement,
}

const SosomonCard = ({src}: propsType) => {
    return(
        <>
            <View style={SosomonCardStyle.cardWrap}>
                <View style={SosomonCardStyle.characterRoomWrap}>
                    <ImageBackground
                        source={src}
                        resizeMode="cover"
                        style={SosomonCardStyle.characterRoom}
                    />
                </View>
                <View style={SosomonCardStyle.cardInfoFlexWrap}>
                    <View style={SosomonCardStyle.cardInfoWrap}>
                        <Text style={SosomonCardStyle.characterName}>달에 사는 고래</Text>
                        <Text style={SosomonCardStyle.characterTypeLevel}>해양동물 Lv.10</Text>
                    </View>
                    <View>
                        <TouchableOpacity activeOpacity={0.7}>
                            <View style={SosomonCardStyle.activeButton}>
                                <Text style={SosomonCardStyle.activeButtonText}>커밍순</Text>
                            </View>
                        </TouchableOpacity>
                    </View>
                </View>
            </View>
        </>
    );
}

export default SosomonCard;