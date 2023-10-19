import {View, Text, TouchableOpacity} from "react-native"

import AuthButtonStyle from "@/styles/AuthButtonStyle";

interface propsType{
    movePage: string,
}

const AuthButton = ({movePage}: propsType) => {
    return(
        <>
            <View style={AuthButtonStyle.authInfoWrap}>
                <Text style={AuthButtonStyle.authInfoTitle}>이런 분들께 추천드려요</Text>
                <Text style={AuthButtonStyle.authInfoDescription}>
                    도움이 필요하지만, 주변에게 직접 도움을 요청하기 어려울 때{"\n"}
                    소소한 선행을 주변에게 베품으로써 선행 인증을 채우고 싶을 때
                </Text>
            </View>
            <View style={AuthButtonStyle.authButtonWrap}>
                <TouchableOpacity activeOpacity={0.7}>
                    <View style={AuthButtonStyle.nextButton}>
                        <Text style={AuthButtonStyle.nextButtonText}>다음단계 진행하기</Text>
                    </View>
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7}>
                    <View style={AuthButtonStyle.moveMainButton}>
                        <Text style={AuthButtonStyle.moveMainButtonText}>메인으로 돌아가기</Text>
                    </View>
                </TouchableOpacity>
            </View>
        </>
    );
}

export default AuthButton;