import {useCallback, useEffect, useState} from "react"
import { View, Text, Image, TextInput, TouchableOpacity } from "react-native";

import CommonLayout from "@/components/CommonLayout";
import Footer from "@/components/Footer";

import PreArrowIcon from "@/assets/img/pre-arrow.png"
import HamburgerMenuIcon from "@/assets/img/hamburger-menu.png"
import SearchIcon from "@/assets/img/search-icon.png"
import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import ChatListStyle from "@/styles/ChatListStyle"
import ChatListItem from "@/components/ChatListItem";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import chatApi from "@/apis/chatApi";
import memberApi from "@/apis/memberApi";
import {ChatSocket, helpSocket} from "@/types";
import useStore from "@/store/store";

interface propsType{
  socket: helpSocket;
  chatSocket: ChatSocket
}

const ChatList = ({socket, chatSocket}: propsType) => {
  const [noneCheckedState, setNoneCheckedState] = useState<Boolean>(true);
  const [allMsgState, setAllMsgState] = useState<Boolean>(false);
  const navigation = useNavigation();
  const {userInfo} = useStore();



  const getChatRoomList = async () => {
    const chatListApi = await chatApi.getChatRoomList(userInfo.memberId);
    chatSocket.getHelpChatList(chatListApi.data.result.chatRoomList);
    chatSocket.getList();
  }

  useFocusEffect(
      useCallback(() => {
        const disConnect = () => {
          if (!socket.connected) return;
          socket.disConnect();
        }
        disConnect();
        return () => {};
      }, [socket.connected])
  )

  useFocusEffect(
      useCallback(() => {
        const connect = () => {
          if (chatSocket.connected) return;
          chatSocket.connect();
        }
        connect();
        return () => {};
      }, [chatSocket.connected])
  )

  useEffect(() => {
    getChatRoomList();
  },[]);

  return (
    <CommonLayout footer={true} headerType={0}>
      <View style={ChatListStyle.chatListBg}>
        <View style={ChatListStyle.chatListHeaderWrap}>
          <View>
            <Text style={ChatListStyle.headerTitle}>Messages</Text>
          </View>
        </View>

        {/*<View style={ChatListStyle.chatListNav}>*/}
        {/*  <TouchableOpacity activeOpacity={0.7} onPress={updateNoneCheckedState}>*/}
        {/*    {*/}
        {/*      noneCheckedState ?*/}
        {/*      <Text style={[ChatListStyle.chatListNavItem, ChatListStyle.chatListNavActiveItem]}>아직 안 본 메시지</Text>*/}
        {/*      :*/}
        {/*      <Text style={[ChatListStyle.chatListNavItem]}>아직 안 본 메시지</Text>*/}
        {/*    }*/}
        {/*  </TouchableOpacity>*/}
        {/*  <TouchableOpacity activeOpacity={0.7} onPress={updateAllMsgState}>*/}
        {/*    {*/}
        {/*      allMsgState ?*/}
        {/*      <Text style={[ChatListStyle.chatListNavItem, ChatListStyle.chatListNavActiveItem]}>전체 메시지</Text>*/}
        {/*      :*/}
        {/*      <Text style={[ChatListStyle.chatListNavItem]}>전체 메시지</Text>*/}
        {/*    }*/}
        {/*  </TouchableOpacity>*/}
        {/*</View>*/}

        <View style={ChatListStyle.chatListItemWrap}>
          {
            chatSocket.helpChatList.map((chatListItem: any, index: number) => {
              return(
                  <ChatListItem chatInfo={chatListItem} key={`chatlist${index}`}/>
              );
            })
          }
        </View>
      </View>
    </CommonLayout>
  );
};

export default ChatList;
