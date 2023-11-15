import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'category';

interface recommendProps{
  memberId: number,
}

const helpCategoryApi = {
  default: async () => {
    const res = PublicInstance.get(
      `${domain}/default`,
    );
    return res;
  },
  recent: async () => {
    const res = PrivateInstance.get(
      `${domain}/recent`,
    );
    return res;
  },
  getCategory: async () => {
    const res = PrivateInstance.get(
      `${domain}/categories`,
    );
    return res;
  }

};

export default helpCategoryApi;
