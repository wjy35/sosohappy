import { View, Text, Image, TouchableOpacity } from "react-native"

import HistoryItemStyle from "@/styles/HistoryItemStyle"

interface propsType{
    thumbnail: HTMLImageElement,
    content: string,
    createdDate: string,
    stateImg: HTMLImageElement,
}

const HistoryItem = ({thumbnail, content, createdDate, stateImg} : propsType) => {
    return(
        <>
            <TouchableOpacity activeOpacity={0.7}>
                <View style={HistoryItemStyle.historyItemWrap}>
                    <View style={HistoryItemStyle.historyItemProfileBg}>
                        <Image
                            source={thumbnail}
                            style={HistoryItemStyle.historyItemProfileImg}
                        />
                    </View>
                    <View style={HistoryItemStyle.historyItemInfo}>
                        <Text style={HistoryItemStyle.historyItemContent}>{content}</Text>
                        <Text style={HistoryItemStyle.historyItemDate}>{createdDate}</Text>
                    </View>
                    <View style={HistoryItemStyle.historyItemStateWrap}>
                        <Image
                            source={stateImg}
                            style={HistoryItemStyle.historyItemStateImg}
                        />
                    </View>
                </View>
            </TouchableOpacity>
        </>
    );
}

export default HistoryItem;