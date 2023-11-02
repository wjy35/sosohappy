import { View, Text, Image, TouchableOpacity } from "react-native"

import HistoryItemStyle from "@/styles/HistoryItemStyle"
import {lock, fortuneCookie, clover} from "@/assets/icons/icons";
import {SvgXml} from "react-native-svg";
import {useState} from "react";

interface propsType{
    thumbnail: string,
    content: string,
    createdDate: string,
    openCookie: Function,
}

const HistoryItem = ({thumbnail, content, createdDate, openCookie} : propsType) => {
    const [cookie, setCookie] = useState(true);

    const clickOpenCookie = () => {
        setCookie(false);
        openCookie();
    }

    return(
        <>
            <TouchableOpacity activeOpacity={0.7}>
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
