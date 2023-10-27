import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'http://10.0.2.2:4002/';

interface props {
  memberId?: number;
  chatRoomId?: number;
}

const helpChatApi = {
  getChatList: async ({memberId}: props) => {
    const res = PrivateInstance.get(
      `${domain}/chat-room/${memberId}`
    );
    return res;
  },
  getChatDetails: async ({chatRoomId}: props) => {
    const res = PrivateInstance.get(
      `${domain}/${chatRoomId}`
    );
    return res;
  }
};

export default helpChatApi;
