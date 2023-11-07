import { View, Text, Image } from "react-native"
import TurtleLoading from "@/assets/img/turtle-loading.png"
import MapLoadingStyle from "@/styles/MapLoadingStyle";
import {observer} from "mobx-react";
import useStore from "@/hooks/useStore";
import {useEffect, useState} from "react";
import {type1, type2, type3, type4} from "@/assets/sosomon";

const MapLoading = observer(() => {
    const {userStore} = useStore();
    const [src, setSrc] = useState();

    useEffect(() => {
        const type = Math.floor((userStore.user.profileMonsterId-1)/10);
        const level = (userStore.user.profileMonsterId % 10 === 0)?10:userStore.user.profileMonsterId%10;
        // console.log(type, level)
        if (type === 0){
            setSrc(type1[level-1])
        } else if (type === 1){
            setSrc(type2[level-1])
        } else if (type === 2){
            setSrc(type3[level-1])
        } else {
            setSrc(type4[0])
        }
    }, []);

    return(
        <>
            <View style={MapLoadingStyle.modalBack}></View>
            <View style={MapLoadingStyle.modalMain}>
                <Image
                    source={src?src:TurtleLoading}
                    style={MapLoadingStyle.turtleLoading}
                />
                <Text style={MapLoadingStyle.loadingText}>
                    <Text style={MapLoadingStyle.loadingTextBold}>50m</Text> 반경의 모음이를 찾는 중입니다.{"\n"}
                    잠시만 기다려주세요
                </Text>
            </View>
        </>
    );
});

export default MapLoading;
