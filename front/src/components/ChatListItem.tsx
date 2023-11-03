import { View, Text, Image, TouchableOpacity } from "react-native"
import { useNavigation } from "@react-navigation/native";

import ChatListItemStyle from "@/styles/ChatListItemStyle"

enum Rank{
    '나눔이',
    '모음이'
}

interface propsType {
    thumbnail: HTMLImageElement,
    name: string,
    rank: Rank,
    recentMessage: string,
}

const ChatListItem = ({thumbnail, name, rank, recentMessage} :propsType) => {
    const navigation = useNavigation();
    return(
        <>
            <TouchableOpacity activeOpacity={0.7} onPress={() => navigation.navigate('Chat')}>
                <View style={ChatListItemStyle.chatListItemWrap}>
                    <Image
                        source={thumbnail}
                        style={ChatListItemStyle.chatListItemThumbnailImg}
                    />
                    <View style={ChatListItemStyle.chatContentWrap}>
                        <View style={ChatListItemStyle.chatInfoWrap}>
                            <Text style={ChatListItemStyle.chatInfoName}>{name}</Text>
                            <View style={ChatListItemStyle.charInfoRank}>
                                <Text style={ChatListItemStyle.chatInfoRankText}>{rank}</Text>
                            </View>
                            <View style={ChatListItemStyle.msgAlarm}>
                                <Text style={ChatListItemStyle.msgAlarmText}>1</Text>
                            </View>
                        </View>
                        <Text style={ChatListItemStyle.recentMessageText}>{recentMessage}</Text>
                    </View>
                </View>
            </TouchableOpacity>
        </>
    );
}

export default ChatListItem;