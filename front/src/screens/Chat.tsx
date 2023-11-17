import React, { useState, useEffect, useCallback } from "react"
import {View, Text, TouchableOpacity, TextInput, ScrollView, Alert, FlatList, KeyboardAvoidingView} from "react-native";

import CommonLayout from "@/components/CommonLayout";
import MyChat from "@/components/MyChat";
import ChatDate from "@/components/ChatDate";
import chatApi from "@/apis/chatApi";

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import {SvgXml} from "react-native-svg";
import {exit, people, send2, hamburgerMenu, backIcon} from "@/assets/icons/icons";

import ChatStyle from "@/styles/ChatStyle";
import YourChat from "@/components/YourCaht";
import {useFocusEffect, useNavigation, useRoute} from "@react-navigation/native";
import {helpSocket, ChatSocket} from "@/types";
import useStore from "@/store/store";
import {SafeAreaProvider, SafeAreaView} from "react-native-safe-area-context";
import Header from "@/components/Header";
import SideMenu from "@/components/SideMenu";
import { KeyboardAwareScrollView } from 'react-native-keyboard-aware-scroll-view';

interface propsType{
  helpSocket: helpSocket;
  chatSocket: ChatSocket
}



const Chat = ({helpSocket, chatSocket}: propsType) => {
  const [msg, setMsg] = useState<string>("");
  const [roomNo, setroomNo] = useState<number|null>(null);
  const route = useRoute();
  const [otherMemberId, setOtherMemberId] = useState<string>(route.params?.otherMemberId);
  const {userInfo} = useStore();
  const navigation = useNavigation();
  const [isVisible, setIsVisible] = useState(false);

  const openSide = () => {
    setIsVisible(true);
  }

  const closeSide = () => {
    setIsVisible(false);
  };


  const sendMsg = async () => {
    if(roomNo){
      const sendChatRes = await chatApi.sendChat({roomNo: roomNo, sendMemberId: userInfo.memberId, receiveMemberId:otherMemberId, content:msg});
      if(sendChatRes.status === 200){
        setMsg("");
      }else{
        Alert.alert("시스템 에러, 관리자에게 문의하세요.");
      }
    }
  }

  const connectChatRoom = async () => {
    const roomNoRes = await chatApi.makeChatRoom({senderMemberId:userInfo.memberId, receiveMemberId:otherMemberId});
    if(roomNoRes.data.status === "success"){
      setroomNo(roomNoRes.data.result.chatRoomId);
    }
  }

  useEffect(() => {
    connectChatRoom();
  },[])

  useEffect(() => {
    if (!roomNo) return;
    chatSocket.getDetail(roomNo);
  },[roomNo]);

  useFocusEffect(
    useCallback(() => {
      const disConnect = () => {
        if (!helpSocket.connected) return;
        helpSocket.disConnect();
      }
      disConnect();
      return () => {};
    }, [helpSocket.connected])
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

  return (
    <>
    {/*<KeyboardAwareScrollView>*/}
      <SafeAreaProvider>
        <SafeAreaView
            edges={["top", "right", "bottom", "left"]}
            style={{flex: 1}}
        >
          <Header headerType={1} openSide={openSide}/>
          <SideMenu closeSide={closeSide} nowPage={'Chat'} isVisible={isVisible}/>
          <KeyboardAvoidingView style={{flex: 1}} behavior={'height'}>
            <View style={{flex: 1}}>
                <View style={ChatStyle.heightWrap}>
                  <View style={ChatStyle.chatContentWrap}>
                    <FlatList
                        data={[...chatSocket.msgList].reverse()}
                        showsVerticalScrollIndicator={false}
                        windowSize={5}
                        inverted={true}
                        initialNumToRender={10}
                        renderItem={(item) => {
                          if (item.item.memberId === userInfo.memberId){
                            return <MyChat content={item.item.content}/>
                          } else if (item.item.memberId === route.params?.otherMemberId) {
                            return <YourChat thumbnail={FishThumbnail} content={item.item.content}/>
                          } else {
                            return (
                                <View style={{justifyContent: 'center', alignItems: 'center', marginVertical: 5}}>
                                  <Text>{item.item.content}</Text>
                                </View>
                            )
                          }
                        }}
                        keyExtractor={(item) => String(item.timestamp)+String(item.memberId)+String(item.content)}
                        onEndReachedThreshold={0.1}
                        contentContainerStyle={{paddingHorizontal: 10}}
                    />
                  </View>
                  {/*<View style={ChatStyle.agreeWrap}>*/}
                  {/*  <SvgXml*/}
                  {/*      xml={people}*/}
                  {/*      width={45}*/}
                  {/*      height={45}*/}
                  {/*  />*/}
                  {/*  <Text style={ChatStyle.agreeInfoText}>매칭하기 버튼을 통해 상대방의 요청을 승인해주세요.</Text>*/}
                  {/*  <TouchableOpacity activeOpacity={0.7}>*/}
                  {/*    <View style={ChatStyle.agreeButton}>*/}
                  {/*      <Text style={ChatStyle.agreeButtonText}>승인하기</Text>*/}
                  {/*    </View>*/}
                  {/*  </TouchableOpacity>*/}
                  {/*</View>*/}

                  <View style={ChatStyle.controlWrap}>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.goBack()}>
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
            </View>
          </KeyboardAvoidingView>
        </SafeAreaView>
      </SafeAreaProvider>
    {/*</KeyboardAwareScrollView>*/}
    </>
  );
};

export default Chat;
