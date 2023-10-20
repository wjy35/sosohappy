import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpHistoryApi = axios.create({
  baseURL: `${baseURL}/help-history/`,
});

const PrivateHelpHistoryApi = axios.create({
  baseURL: `${baseURL}/help-history/`,
  headers: {
    Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpHistoryApi = {

};

export default helpHistoryApi;
