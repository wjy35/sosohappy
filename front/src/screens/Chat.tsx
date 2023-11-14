import { useState, useEffect, useCallback } from "react"
import { View, Text, TouchableOpacity, TextInput, ScrollView, Alert } from "react-native";

import CommonLayout from "@/components/CommonLayout";
import MyChat from "@/components/MyChat";
import ChatDate from "@/components/ChatDate";
import chatApi from "@/apis/chatApi";

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import {SvgXml} from "react-native-svg";
import {exit, people, send2, hamburgerMenu, backIcon} from "@/assets/icons/icons";

import ChatStyle from "@/styles/ChatStyle";
import YourChat from "@/components/YourCaht";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import {helpSocket, ChatSocket} from "@/types";

interface propsType{
  helpSocket: helpSocket;
  chatSocket: ChatSocket
}



const Chat = ({helpSocket, chatSocket}: propsType) => {
  const socket = io('http://10.0.2.2:4002');
  const [msg, setMsg] = useState<string>("");
  const [msgList, setMsgList] = useState<Object[]>([]);
  const [roomNo, setroomNo] = useState<number|null>(null);
  const [myMemberId, setMyMemberId] = useState<string>("1");
  const [yourMemberId, setYourMemberId] = useState<string>("2");
  const navigation = useNavigation();

  const sendMsg = async () => {
    if(roomNo){
      const sendChatRes = await chatApi.sendChat({roomNo: roomNo, sendMemberId: myMemberId, receiveMemberId:yourMemberId, content:msg});
      if(sendChatRes.status === 200){
        setMsg("");
      }else{
        Alert.alert("시스템 에러, 관리자에게 문의하세요.");
      }
    }
  }

  const connectChatRoom = async () => {
    const roomNoRes = await chatApi.makeChatRoom({senderMemberId:myMemberId, receiveMemberId:yourMemberId});
    if(roomNoRes.data.status === "success"){
      setroomNo(roomNoRes.data.result.chatRoomId);
    }
  }

  const getChatListApi = async () => {
    if(roomNo){
      const chatListRes = await chatApi.getChatList({roomNo: roomNo});
      if(chatListRes.status === 200){
        setMsgList(chatListRes.data.result.chatResponseList);
        console.log("chatList", chatListRes.data.result.chatResponseList);
      }
    }
  }

  useFocusEffect(()=>{
    if (!helpSocket.connected) return;
    helpSocket.disConnect();
  })

  useEffect(() => {
    connectChatRoom();
  },[])

  useEffect(() => {
    getChatListApi();
  },[roomNo,msg]);

  useFocusEffect(
    useCallback(() => {
      const connect = () => {
        if (helpSocket.connected) return;
        helpSocket.connect();
      }
      connect();
      return () => {};
    }, [helpSocket.connected])
  )

  return (
    <CommonLayout footer={false} headerType={1}>
      <View style={ChatStyle.heightWrap}>

        {/* <View style={ChatStyle.chatTitleWrap}>
          <TouchableOpacity activeOpacity={0.7}>
            <SvgXml
              xml={backIcon}
              width={32}
              height={32}
            />
          </TouchableOpacity>
          <View style={ChatStyle.chatTitle}>
            <Text style={ChatStyle.chatTitleName}>김싸피</Text>
            <Text style={ChatStyle.chatTitleRank}>나눔이</Text>
          </View>
          <TouchableOpacity activeOpacity={0.7}>
            <SvgXml
              xml={hamburgerMenu}
              width={30}
              height={30}
            />
          </TouchableOpacity>
        </View> */}

        <View style={ChatStyle.chatContentWrap}>
          <ScrollView>
            <ChatDate date="2023. 10. 15."/>
            {
              msgList.map((msg, index) => {
                return(
                  <>
                    {
                      String(msg.memberId) === yourMemberId ?
                      <YourChat thumbnail={FishThumbnail} content={msg.content} key={index}/>
                      :
                      <MyChat content={msg.content} key={index}/>
                    }
                  </>
                );
              })
            }

          </ScrollView>
        </View>

        <View style={ChatStyle.agreeWrap}>
          <SvgXml
            xml={people}
            width={45}
            height={45}
          />
          <Text style={ChatStyle.agreeInfoText}>매칭하기 버튼을 통해 상대방의 요청을 승인해주세요.</Text>
          <TouchableOpacity activeOpacity={0.7}>
            <View style={ChatStyle.agreeButton}>
              <Text style={ChatStyle.agreeButtonText}>승인하기</Text>
            </View>
          </TouchableOpacity>
        </View>

        <View style={ChatStyle.controlWrap}>
          <TouchableOpacity activeOpacity={0.7}>
            <View style={ChatStyle.exitFlexWrap}>
              <SvgXml
                xml={exit}
                width={20}
                height={20}
              />
              <Text style={ChatStyle.exitText}>채팅방 나가기</Text>
            </View>
          </TouchableOpacity>
          <TextInput
            placeholder="메시지 내용을 입력하세요."
            style={ChatStyle.chanMsgInput}
            onChangeText={(text) => setMsg(text)}
            value={msg}
          />
          <TouchableOpacity activeOpacity={0.7} onPress={sendMsg}>
            <View style={ChatStyle.sendButton}>
              <SvgXml
                xml={send2}
                width={20}
                height={20}
              />
            </View>
          </TouchableOpacity>
        </View>
      </View>
    </CommonLayout>
  );
};

export default Chat;
