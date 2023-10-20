import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicMemberApi = axios.create({
  baseURL: `${baseURL}/member/`,
});

const PrivateMemberApi = axios.create({
  baseURL: `${baseURL}/member/`,
  headers: {
    Authorization: `Bearer ${}`
  }
});

interface props {

}

const memberApi = {

};

export default memberApi;
