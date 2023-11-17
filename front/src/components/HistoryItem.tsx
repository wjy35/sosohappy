import {useEffect, useState} from "react";
import { View, Text, TouchableOpacity } from "react-native"

import HistoryItemStyle from "@/styles/HistoryItemStyle"
import {lock, fortuneCookie, clover} from "@/assets/icons/icons";
import {SvgXml} from "react-native-svg";
import helpMatchApi from "@/apis/helpMatchApi";
import helpCategoryApi from "@/apis/helpCategoryApi";

interface propsType{
    categoryId: number,
    content: string,
    createdDate: string,
    updateFortuneModalState: Function,
    fortuneCookieId: number,
    addClover: Function,
}

const HistoryItem = ({categoryId, content, createdDate, updateFortuneModalState, fortuneCookieId, addClover} : propsType) => {
    const [cookie, setCookie] = useState(true);
    const [myThumbnailInfo, setMyThumbnailInfo] = useState([]);

    const openFortuneCookie = async () => {
        const deleteFortuneCookie = await helpMatchApi.openFortuneCookie({fortuneCookieId: fortuneCookieId});

        if(deleteFortuneCookie.status === 200){
            updateFortuneModalState(true);
            setCookie(false);
            addClover();
        }
    }

    const findMyThumbnail = async () => {
        try{
            const res = await helpCategoryApi.findCategoryById({categoryId: categoryId});
            if(res.status === 200){
                setMyThumbnailInfo(res.data.result.category);
            }
        }catch(err){
            console.error(err);
        }
    }

    useEffect(() => {
        findMyThumbnail();
    }, []);

    return(
        <>
            <TouchableOpacity activeOpacity={0.7} onPress={() => openFortuneCookie()}>
                <View style={HistoryItemStyle.historyItemWrap}>
                    <View style={HistoryItemStyle.historyItemProfileBg}>
                        {
                            myThumbnailInfo?.categoryImage && (
                                <SvgXml
                                    xml={myThumbnailInfo.categoryImage}
                                    style={HistoryItemStyle.historyItemProfileImg}
                                    width={40}
                                    height={40}
                                />
                            )
                        }
                    </View>
                    <View style={HistoryItemStyle.historyItemInfo}>
                        <Text style={HistoryItemStyle.historyItemContent}>{content}</Text>
                        <Text style={HistoryItemStyle.historyItemDate}>{createdDate}</Text>
                    </View>
                    <View style={HistoryItemStyle.historyItemStateWrap}>
                        {
                            cookie ? (
                                <SvgXml
                                    xml={fortuneCookie}
                                    width={30}
                                    height={30}
                                />
                            ):(
                                <SvgXml
                                    xml={clover}
                                    width={30}
                                    height={30}
                                />
                            )
                        }
                    </View>
                </View>
            </TouchableOpacity>
        </>
    );
}

export default HistoryItem;
