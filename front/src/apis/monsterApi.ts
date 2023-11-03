import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'monster';

interface props {
  memberMonsterId?: number;
  clover?: number;
}

const monsterApi = {
  getMyDetail: async () => {
    const res = PrivateInstance.get(
        `${domain}/my`,
    );
    return res;
  },
  getMyClover: async () => {
    const res = PrivateInstance.get(
      `${domain}/clover`,
    );
    return res;
  },
  getMyDict: async () => {
    const res = PrivateInstance.get(
      `${domain}/collection`,
    );
    return res;
  },
  levelUp: async ({memberMonsterId, clover}: props) => {
    const res = PrivateInstance.patch(
      `${domain}/level-up`,
      {
        memberMonsterId: memberMonsterId,
        clover: clover
      }
    );
    return res;
  }



};

export default monsterApi;

