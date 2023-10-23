import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpChatSocketApi = axios.create({
  baseURL: `${baseURL}/help-chat-socket/`,
});

const PrivateHelpChatSocketApi = axios.create({
  baseURL: `${baseURL}/help-chat-socket/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpChatSocketApi = {

};

export default helpChatSocketApi;
