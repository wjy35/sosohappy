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

interface propsType{
  socket: helpSocket;
  chatSocket: ChatSocket
}

const ChatList = ({socket, chatSocket}: propsType) => {
  const [noneCheckedState, setNoneCheckedState] = useState<Boolean>(true);
  const [allMsgState, setAllMsgState] = useState<Boolean>(false);
  const [chatList, setChatList] = useState<Object[] | null>(null);
  const [userNameList, setUserNameList] = useState<any[]>([]);
  const [userRankList, setUserRankList] = useState<any[]>([]);
  const navigation = useNavigation();

  const updateNoneCheckedState = () => {
    setNoneCheckedState(true);
    setAllMsgState(false);
  }
  const updateAllMsgState = () => {
    setAllMsgState(true);
    setNoneCheckedState(false);
  }

  const getChatRoomList = async () => {
    const chatListApi =  await chatApi.getChatRoomList("1");
    setChatList(chatListApi.data.result.chatRoomList);

    if(chatList){
      for(let i=0; i<chatList.length; i++){
        const userInfo = await memberApi.publicMemberShow(chatList[i].memberList[1]);
        if(userInfo.status === 200){
          const userName = userInfo.data.result.member.nickname;
          const userRank = userInfo.data.result.member.disabled;
          setUserNameList(prev => ([
            ...prev,
            userName,
          ]));
          if(userRank === false){
            setUserRankList(prev => ([
              ...prev,
              "모음이",
            ]))
          }else if(userRank === true){
            setUserRankList(prev => ([
              ...prev,
              "나눔이",
            ]))
          }
        }
      }
    }
  }

  const findRoomUserName = async (userNo: number) => {
    const memberRes = await memberApi.publicMemberShow(userNo);
    if(memberRes.status === 200){
      return String(memberRes.data.result.member.nickname);
    }
  }

  useFocusEffect(()=>{
    if (!socket.connected) return;
    socket.disConnect();
  })

  useEffect(() => {
    getChatRoomList();
  },[userNameList, userRankList]);

  return (
    <CommonLayout>
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
            chatList &&
            chatList.map((chatListItem: any, index: number) => {
              return(
                <>
                  <ChatListItem thumbnail={FishThumbnail} name={userNameList[index]} rank={userRankList[index]} recentMessage={chatListItem.currentChat.content} key={index}/>
                </>
              );
            })
          }
        </View>
      </View>
    </CommonLayout>
  );
};

export default ChatList;
