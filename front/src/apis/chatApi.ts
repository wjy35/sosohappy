import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = '/chat';

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

const chatApi = {
  getChatList: async (chatRoom : number) => {
    const res = PublicInstance.get(
        `${domain}/${chatRoom}`,
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
      `${domain}/send`,
      {
        chatRoomId:roomNo,
        sendMemberId:sendMemberId,
        receiveMemberId:receiveMemberId,
        type:"1", // 0번 system, 1번 text
        content:content,
      }
    )
    return res;
  }
};

export default chatApi;
