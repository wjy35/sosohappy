import { View, Text, Image, TouchableOpacity } from "react-native"
import { useNavigation } from "@react-navigation/native";

import ChatListItemStyle from "@/styles/ChatListItemStyle"
import memberApi from "@/apis/memberApi";
import {useEffect, useState} from "react";
import {type1, type2, type3, type4} from "@/assets/sosomon";
import {SingleChatInfo} from "@/types";
import useStore from "@/store/store";

enum Rank{
    '나눔이',
    '모음이'
}

interface propsType {
    chatInfo: SingleChatInfo
}

const ChatListItem = ({chatInfo} :propsType) => {
    const navigation = useNavigation();
    return(
        <>
            <TouchableOpacity activeOpacity={0.7} onPress={() => navigation.navigate('Chat', {otherMemberId: otherMemberInfo.memberId})}>
                <View style={ChatListItemStyle.chatListItemWrap}>
                    <Image
                        source={src?src:type4[0]}
                        style={ChatListItemStyle.chatListItemThumbnailImg}
                    />
                    <View style={ChatListItemStyle.chatContentWrap}>
                        <View style={ChatListItemStyle.chatInfoWrap}>
                            <Text style={ChatListItemStyle.chatInfoName}>{otherMemberInfo?.nickname}</Text>
                            <View style={ChatListItemStyle.charInfoRank}>
                                <Text style={ChatListItemStyle.chatInfoRankText}>{otherMemberInfo?.disabled?'나눔이':'모음이'}</Text>
                            </View>
                            <View style={ChatListItemStyle.msgAlarm}>
                                <Text style={ChatListItemStyle.msgAlarmText}>1</Text>
                            </View>
                        </View>
                        <Text style={ChatListItemStyle.recentMessageText}>{chatInfo.currentChat.content}</Text>
                    </View>
                </View>
            </TouchableOpacity>
        </>
    );
}

export default ChatListItem;
