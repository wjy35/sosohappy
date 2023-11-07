import { View, Text, Image } from "react-native"

import TurtleLoading from "@/assets/img/turtle-loading.png"

import MapLoadingStyle from "@/styles/MapLoadingStyle";

const MapLoading = () => {
    return(
        <>
            <View style={MapLoadingStyle.modalBack}></View>
            <View style={MapLoadingStyle.modalMain}>
                <Image
                    source={TurtleLoading}
                    style={MapLoadingStyle.turtleLoading}
                />
                <Text style={MapLoadingStyle.loadingText}>
                    <Text style={MapLoadingStyle.loadingTextBold}>50m</Text> 반경의 모음이를 찾는 중입니다.{"\n"}
                    잠시만 기다려주세요
                </Text>
            </View>
        </>
    );
}

export default MapLoading;