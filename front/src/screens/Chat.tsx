import { View, Text, Image, TouchableOpacity, TextInput } from "react-native";

import CommonLayout from "@/components/CommonLayout";
import MyChat from "@/components/MyChat";

import {SvgXml} from "react-native-svg";
import {exit, people, send2, hamburgerMenu, backIcon} from "@/assets/icons/icons";

import ChatStyle from "@/styles/ChatStyle";
import { ScrollView } from "react-native-gesture-handler";

const Chat = () => {
  return (
    <CommonLayout>
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
            <Text>2023. 10. 16.</Text>
            <MyChat content="네, 금방 갈게요 조금만 기다리세요." />
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
          />
          <TouchableOpacity activeOpacity={0.7}>
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
