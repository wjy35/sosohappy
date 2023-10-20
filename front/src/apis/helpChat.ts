import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpChatApi = axios.create({
  baseURL: `${baseURL}/help-chat/`,
});

const PrivateHelpChatApi = axios.create({
  baseURL: `${baseURL}/help-chat/`,
  headers: {
    Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpChatApi = {

};

export default helpChatApi;
