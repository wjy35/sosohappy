import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpMatchApi = axios.create({
  baseURL: `${baseURL}/help-match/`,
});

const PrivateHelpMatchApi = axios.create({
  baseURL: `${baseURL}/help-match/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpMatchApi = {

};

export default helpMatchApi;
