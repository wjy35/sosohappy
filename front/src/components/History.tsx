import { View, Text, TouchableOpacity } from "react-native"
import HistoryItem from "./HistoryItem";

import FishThumbnail from "@/assets/img/fish-thumbnail.png"
import CloverIcon from "@/assets/img/clover-icon.png"
import FortuneCookieIcon from "@/assets/img/fortune-cookie-icon.png"

import HistoryStyle from "@/styles/HistoryStyle";

const History = () => {
    return(
        <>
            <View style={HistoryStyle.historyWrap}>
                
                <View>
                    <HistoryItem thumbnail={FishThumbnail} content="버스승차를 도와주세요!" createdDate="2023. 10. 16." stateImg={FortuneCookieIcon} />
                </View>
                
            </View>
        </>
    );
}

export default History;