import { useState, useEffect } from "react"
import { View, Text, TouchableOpacity, TextInput, ScrollView } from "react-native";

import CommonLayout from "@/components/CommonLayout";
import MyChat from "@/components/MyChat";
import ChatDate from "@/components/ChatDate";
import io from "socket.io-client"

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import {SvgXml} from "react-native-svg";
import {exit, people, send2, hamburgerMenu, backIcon} from "@/assets/icons/icons";

import ChatStyle from "@/styles/ChatStyle";
import YourChat from "@/components/YourCaht";

const Chat = () => {
  const socket = io('http://10.0.2.2:4002');
  const [msg, setMsg] = useState<string>();
  const [msgList, setMsgList] = useState<Object[]>([]);
  const [roomNo, setroomNo] = useState<number>(1);

  const sendMsg = () => {
    socket.emit('send message', {roomNo, msg});
    setMsg('');
  }

  socket.on('upload chat', (data:Object) => {
    setMsgList([...msgList, data]);
  });

  useEffect(() => {
    socket.emit('chat join', roomNo);
  }, [])

  return (
    <CommonLayout footer={false} headerType={0}>
      <View style={ChatStyle.heightWrap}>

        <View style={ChatStyle.chatTitleWrap}>
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
        </View>

        <View style={ChatStyle.chatContentWrap}>
          <ScrollView>
            <ChatDate date="2023. 10. 15."/>

            <MyChat content="네, 금방 갈게요 조금만 기다리세요."/>
            <ChatDate date="2023. 10. 16."/>
            <YourChat thumbnail={FishThumbnail} content="안녕하세요! 저는 7층에 있습니다.
            건물에 들어오시면, 엘리베이터를 찾기가
            어렵네요, 안쪽으로 들어오셔서 오른쪽에
            있습니다."/>
            <MyChat content="네, 금방 갈게요 조금만 기다리세요."/>

            {
              msgList.map((msg, index) => {
                return(
                  <>
                    {
                      msg.senderType === 'you' ?
                      <YourChat thumbnail={FishThumbnail} content={msg.message} key={index}/>
                      :
                      <MyChat content={msg.message} key={index}/>
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
