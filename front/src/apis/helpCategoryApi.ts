import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'category';

const helpCategoryApi = {
  default: async () => {
    const res = PublicInstance.get(
      `${domain}/default`,
    );
    return res;
  },
  recent: async () => {
    const res = PublicInstance.get(
      `${domain}/recent`,
    );
    return res;
  },
  recommend: async () => {
    const res = PublicInstance.get(
      `${domain}/recommend`,
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
