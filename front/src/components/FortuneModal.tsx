import { View, Text, TouchableOpacity } from "react-native"
import { SvgXml } from "react-native-svg";

import FortuneModalStyle from "@/styles/FortuneModalStyle";

import { fortuneCookie, close } from "@/assets/icons/icons";

interface propsType{
    updateFortuneModalState: Function,
}

const FortuneModal = ({updateFortuneModalState}: propsType) => {
    return(
        <>
            <View style={FortuneModalStyle.modalBg}></View>
            <View style={FortuneModalStyle.modalMain}>
                <View style={FortuneModalStyle.modalTitleWrap}>
                    <Text style={FortuneModalStyle.modalTitleText}>오늘의 포춘쿠키</Text>
                    <TouchableOpacity activeOpacity={0.7} onPress={() => updateFortuneModalState(false)}>
                        <SvgXml
                            xml={close}
                            style={FortuneModalStyle.closeIcon}
                        />
                    </TouchableOpacity>
                </View>
                <View style={FortuneModalStyle.fortuneWrap}>
                    <SvgXml
                        xml={fortuneCookie}
                        style={FortuneModalStyle.fortuneImg}
                    />
                    <View style={FortuneModalStyle.fortuneTextWrap}>
                        <Text style={FortuneModalStyle.fortuneText}>기억하세요. 당신은 눈부시고 아름답습니다. 기억하세요. 당신은 눈부시고 아름답습니다. 기억하세요. 당신은 눈부시고 아름답습니다.</Text>
                        <Text style={FortuneModalStyle.fortuneFooter}>오늘의 포춘쿠키</Text>
                    </View>
                </View>
            </View>
        </>
    );
}

export default FortuneModal;