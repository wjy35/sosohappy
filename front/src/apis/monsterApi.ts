import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicMonsterApi = axios.create({
  baseURL: `${baseURL}/monster/`,
});

const PrivateMonsterApi = axios.create({
  baseURL: `${baseURL}/monster/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {
  memberMonsterId?: number;
  clover?: number;
}

const monsterApi = {
  getMyDetail: async () => {
    const res = PrivateMonsterApi.get(
      'my',
    );
    return res;
  },
  getMyDict: async () => {
    const res = PrivateMonsterApi.get(
      'collection',
    );
    return res;
  },
  levelUp: async ({memberMonsterId, clover}: props) => {
    const res = PrivateMonsterApi.patch(
      'level-up',
      {
        memberMonsterId: memberMonsterId,
        clover: clover
      }
    );
    return res;
  }



};

export default monsterApi;

