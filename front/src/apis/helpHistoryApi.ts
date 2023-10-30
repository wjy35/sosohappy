import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const queryDomain = 'help-history-query';
const commandDomain = 'help-history-command';

interface props {

}

const helpHistoryApi = {
  getHistoryList: async () => {
    const res = PrivateInstance.get(
      `${queryDomain}/`,
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
