import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicHelpCategoryApi = axios.create({
  baseURL: `${baseURL}/help-category/`,
});

const PrivateHelpCategoryApi = axios.create({
  baseURL: `${baseURL}/help-category/`,
  headers: {
    Authorization: `Bearer ${}`
  }
});

interface props {

}

const helpCategoryApi = {

};

export default helpCategoryApi;
