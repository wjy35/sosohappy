import { View, Text, TouchableOpacity } from "react-native"
import HistoryItem from "./HistoryItem";

import HistoryStyle from "@/styles/HistoryStyle";
import {useEffect, useState} from "react";
import helpMatchApi from "@/apis/helpMatchApi";

interface propsType{
    updateFortuneModalState: Function,
}

const History = ({updateFortuneModalState}: propsType) => {
    const [fortuneCookieList, setFortuneCookieList] = useState([]);
    const [maxPage, setMaxPage] = useState<number>(3);

    const getHistory = async () => {
        try {
            const res = await helpMatchApi.getFortuneList();
            if (res.status === 200){
                setFortuneCookieList(res.data.result.fortuneCookieList);
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
                    fortuneCookieList.map((el, idx)=>{
                        const date = new Date(el.timestamp);
                        let formatingDate = date.getFullYear() + "." + date.getMonth() + "." + date.getDate();
                        if(idx < maxPage){
                            return (
                                <View style={HistoryStyle.historyWrap} key={`helpHistory${idx}`}>
                                    <HistoryItem categoryId={el.categoryId} content={el.content} createdDate={String(formatingDate)} updateFortuneModalState={updateFortuneModalState} fortuneCookieId={el.fortuneCookieId}/>
                                </View>
                            )
                        }
                    })
                }
                {
                    maxPage < fortuneCookieList.length &&
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
