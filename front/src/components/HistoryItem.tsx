import { View, Text, Image, TouchableOpacity } from "react-native"

import HistoryItemStyle from "@/styles/HistoryItemStyle"
import {lock, fortuneCookie, clover} from "@/assets/icons/icons";
import {SvgXml} from "react-native-svg";
import {useState} from "react";
import helpMatchApi from "@/apis/helpMatchApi";

interface propsType{
    thumbnail: string,
    content: string,
    createdDate: string,
    openCookie: Function,
    updateFortuneModalState: Function,
    fortuneCookieId: number,
}

const HistoryItem = ({thumbnail, content, createdDate, openCookie, updateFortuneModalState, fortuneCookieId} : propsType) => {
    const [cookie, setCookie] = useState(true);

    const clickOpenCookie = () => {
        setCookie(false);
        openCookie();
    }

    const openFortuneCookie = async () => {
        
        const deleteFortuneCookie = await helpMatchApi.openFortuneCookie({fortuneCookieId: fortuneCookieId});
        
        if(deleteFortuneCookie.status === 200){
            updateFortuneModalState(true);
            console.log("deleteFortuneCookie", deleteFortuneCookie);
            setCookie(false);
        }

    }

    return(
        <>
            <TouchableOpacity activeOpacity={0.7} onPress={() => openFortuneCookie()}>
                <View style={HistoryItemStyle.historyItemWrap}>
                    <View style={HistoryItemStyle.historyItemProfileBg}>
                        <SvgXml
                            xml={thumbnail}
                            style={HistoryItemStyle.historyItemProfileImg}
                            width={40}
                            height={40}
                        />
                    </View>
                    <View style={HistoryItemStyle.historyItemInfo}>
                        <Text style={HistoryItemStyle.historyItemContent}>{content}</Text>
                        <Text style={HistoryItemStyle.historyItemDate}>{createdDate}</Text>
                    </View>
                    <View style={HistoryItemStyle.historyItemStateWrap}>
                        {
                            cookie ? (
                                <TouchableOpacity activeOpacity={0.7} onPress={clickOpenCookie}>
                                    <SvgXml
                                        xml={fortuneCookie}
                                        width={30}
                                        height={30}
                                    />
                                </TouchableOpacity>
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
