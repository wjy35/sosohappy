import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpHistoryApi = axios.create({
  baseURL: `${baseURL}/help-history/`,
});

const PrivateHelpHistoryApi = axios.create({
  baseURL: `${baseURL}/help-history/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpHistoryApi = {
  getListFrom: async () => {
    const res = PrivateHelpHistoryApi.get(
      'list/from',
    );
    return res;
  },
  getListTo: async () => {
    const res = PrivateHelpHistoryApi.get(
      'list/to',
    );
    return res;
  },
  getHelpCount: async () => {
    const res = PrivateHelpHistoryApi.get(
      'count',
    );
    return res;
  },
  getCertificate: async () => {
    const res = PrivateHelpHistoryApi.get(
      'certificate',
    );
    return res;
  }

};

export default helpHistoryApi;
