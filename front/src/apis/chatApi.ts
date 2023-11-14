import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'chat';

interface makeChatProps{
  senderMemberId: string,
  receiveMemberId: string,
}

interface sendMsgProps{
  roomNo: number,
  sendMemberId: string,
  receiveMemberId: string,
  content: string,
}

interface chatListProps{
  roomNo: number,
}

const chatApi = {
  getChatRoomList: async (myMemberId : string) => {
    const res = PublicInstance.get(
        `${domain}/chatroom`,{
          headers:{
            memberId: myMemberId,
          }
        }
    )
    return res;
  },
  makeChatRoom: async ({senderMemberId, receiveMemberId}: makeChatProps) => {
    const res = PublicInstance.post(
        `${domain}/chatroom`,
        {
            senderMemberId: senderMemberId,
            receiverMemberId: receiveMemberId,
        }
    )
    return res;
  },
  sendChat: async ({roomNo, sendMemberId, receiveMemberId, content}: sendMsgProps) => {
    const res= PublicInstance.post(
      `${domain}/chat/send`,
      {
        chatRoomId:roomNo,
        sendMemberId:sendMemberId,
        receiveMemberId:receiveMemberId,
        type:"1", // 0번 system, 1번 text
        content:content,
      }
    )
    return res;
  },
  getChatList: async ({roomNo} : chatListProps) => {
    const res = PublicInstance.get(
      `${domain}/chat/${roomNo}`
    )
    return res;
  }
};

export default chatApi;
