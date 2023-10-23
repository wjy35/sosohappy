import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpCategoryApi = axios.create({
  baseURL: `${baseURL}/help-category/`,
});

const PrivateHelpCategoryApi = axios.create({
  baseURL: `${baseURL}/help-category/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpCategoryApi = {
  getCategory: async () => {
    const res = PrivateHelpCategoryApi.get(
      'categories',
    );
    return res;
  }

};

export default helpCategoryApi;
