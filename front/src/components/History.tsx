import { View, Text, TouchableOpacity } from "react-native"
import HistoryItem from "./HistoryItem";

import HistoryStyle from "@/styles/HistoryStyle";
import {useEffect, useState} from "react";
import helpHistoryApi from "@/apis/helpHistoryApi";

interface propsType{
    updateFortuneModalState: Function,
}

const History = ({updateFortuneModalState}: propsType) => {
    const [historyList, setHistoryList] = useState([]);
    const [maxPage, setMaxPage] = useState<number>(3);

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

    const showFortuneMore = () => {
        setMaxPage((prevMaxPage:number) => prevMaxPage + 3);
    }

    useEffect(()=>{
        getHistory();
    }, [])

    return(
        <>
            <View>
                {
                    historyList.map((el, idx)=>{
                        if(idx < maxPage){
                            return (
                                <View style={HistoryStyle.historyWrap} key={`helpHistory${idx}`}>
                                    <HistoryItem thumbnail={el.categoryImage} content={el.content} createdDate={el.createdAt.split("T")[0]} openCookie={openCookie} updateFortuneModalState={updateFortuneModalState} historyId={el.historyId}/>
                                </View>
                            )
                        }
                    })
                }
                {
                    maxPage < historyList.length &&
                    <TouchableOpacity activeOpacity={0.7} onPress={() => showFortuneMore()}>
                        <View style={HistoryStyle.moreButton}>
                            <Text style={HistoryStyle.moreButtonText}>더보기</Text>
                        </View>
                    </TouchableOpacity>
                }
            </View>
        </>
    );
}

export default History;
