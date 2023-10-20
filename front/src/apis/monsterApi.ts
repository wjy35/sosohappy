import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicMonsterApi = axios.create({
  baseURL: `${baseURL}/monster/`,
});

const PrivateMonsterApi = axios.create({
  baseURL: `${baseURL}/monster/`,
  headers: {
    Authorization: `Bearer ${}`
  }
});

interface props {

}

const monsterApi = {

};

export default monsterApi;

