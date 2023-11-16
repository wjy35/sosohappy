import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'category';

interface findByIdProps{
  categoryId: number,
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
  },
  findCategoryById: async ({categoryId}: findByIdProps) => {
    const res = PrivateInstance.get(
      `${domain}//getCategoryInfo/${categoryId}`
    )
    return res;
  },

};

export default helpCategoryApi;
