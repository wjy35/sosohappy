import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'help-category';

const helpCategoryApi = {
  getCategory: async () => {
    const res = PrivateInstance.get(
      `${domain}/categories`,
    );
    return res;
  }

};

export default helpCategoryApi;
