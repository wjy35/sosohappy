import { View, Text, Image } from "react-native"

import FortuneCookieImg from "@/assets/img/fortune-cookie-img.png"

import FortuneModalStyle from "@/styles/FortuneModalStyle";

const FortuneModal = () => {
    return(
        <>
            <View style={FortuneModalStyle.modalBg}></View>
            <View>
                <View>
                    <Text>오늘의 포춘쿠키</Text>
                </View>
                <View>
                    <Image
                        source={FortuneCookieImg}
                    />
                    <View>
                        <Text>기억하세요. 당신은 눈부시고 아름답습니다.</Text>
                        <Text>오늘의 포춘쿠키</Text>
                    </View>
                </View>
            </View>
        </>
    );
}

export default FortuneModal;