import { View, Text, TouchableOpacity } from "react-native"
import HistoryItem from "./HistoryItem";

import FishThumbnail from "@/assets/img/fish-thumbnail.png"
import CloverIcon from "@/assets/img/clover-icon.png"
import FortuneCookieIcon from "@/assets/img/fortune-cookie-icon.png"

import HistoryStyle from "@/styles/HistoryStyle";
import {useEffect, useState} from "react";
import helpHistoryApi from "@/apis/helpHistoryApi";

interface propsType{
    updateFortuneModalState: Function,
}

const History = ({updateFortuneModalState}: propsType) => {
    const [historyList, setHistoryList] = useState([]);

    const openCookie = () => {
        
    }


    const getHistory = async () => {
        try {
            const res = await helpHistoryApi.getHistoryList();
            if (res.status === 200){
                setHistoryList(res.data.result.historyList);
            }
        } catch (err) {
            console.log(err);
        }
    }

    useEffect(()=>{
        getHistory();
    }, [])

    return(
        <>
            <View>
                {
                    historyList.map((el, idx)=>{
                        return (
                            <View style={HistoryStyle.historyWrap} key={`helpHistory${idx}`}>
                                <HistoryItem thumbnail={el.categoryImage} content={el.content} createdDate={el.createdAt} openCookie={openCookie} updateFortuneModalState={updateFortuneModalState}/>
                            </View>
                        )
                    })
                }
            </View>
        </>
    );
}

export default History;
