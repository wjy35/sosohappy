import {View, Text, Image, TouchableOpacity} from "react-native"
import TurtleLoading from "@/assets/img/turtle-loading.png"
import MapLoadingStyle from "@/styles/MapLoadingStyle";
import useStore from "@/store/store";
import {useEffect, useState} from "react";
import {type1, type2, type3, type4} from "@/assets/sosomon";
import {helpSocket} from "@/types";
import useInterval from "@/hooks/useInterval";
import helpMatchApi from "@/apis/helpMatchApi";

interface propsType{
    socket: helpSocket,
}

const MapLoading = ({socket}: propsType) => {
    const {userInfo} = useStore();
    const [src, setSrc] = useState();
    const [count, setCount] = useState(1);

    const cancelMatch = async () => {
        try {
            const res = await helpMatchApi.cancelMatch({
                memberId: userInfo.memberId
            })
            if (res.status === 200){
                console.log(res)
            }
        } catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        const type = Math.floor((userInfo.profileMonsterId-1)/10);
        const level = (userInfo.profileMonsterId % 10 === 0)?10:userInfo.profileMonsterId%10;
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

    useInterval(()=>{
        setCount(count+1>3?1:count+1)
    }, socket.isSearching?1000:null)

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
                    잠시만 기다려주세요{'.'.repeat(count)}
                </Text>
                <View style={MapLoadingStyle.matchCancelButton}>
                    <TouchableOpacity onPress={cancelMatch}>
                        <View>
                            <Text style={MapLoadingStyle.loadingText}>요청 취소</Text>
                        </View>
                    </TouchableOpacity>
                </View>
            </View>
        </>
    );
};

export default MapLoading;
