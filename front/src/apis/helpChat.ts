import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpChatApi = axios.create({
  baseURL: `${baseURL}/help-chat/`,
});

const PrivateHelpChatApi = axios.create({
  baseURL: `${baseURL}/help-chat/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {
  memberId?: number;
  chatRoomId?: number;
}

const helpChatApi = {
  getChatList: async ({memberId}: props) => {
    const res = PrivateHelpChatApi.get(
      `chat-room/${memberId}`
    );
    return res;
  },
  getChatDetails: async ({chatRoomId}: props) => {
    const res = PrivateHelpChatApi.get(
      `${chatRoomId}`
    );
    return res;
  }
};

export default helpChatApi;
