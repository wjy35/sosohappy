import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const queryDomain = 'help-history-query';
const commandDomain = 'help-history-command';

interface props {

}

const helpHistoryApi = {
  getListFrom: async () => {
    const res = PrivateInstance.get(
      `${queryDomain}/list/from`,
    );
    return res;
  },
  getListTo: async () => {
    const res = PrivateInstance.get(
      `${queryDomain}/list/to`,
    );
    return res;
  },
  getHelpCount: async () => {
    const res = PrivateInstance.get(
      `${queryDomain}/count`,
    );
    return res;
  },
  getCertificate: async () => {
    const res = PrivateInstance.get(
      `${queryDomain}/certificate`,
    );
    return res;
  }

};

export default helpHistoryApi;
