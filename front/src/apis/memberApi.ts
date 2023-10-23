import axios from 'axios';
import {baseURL} from "@/apis/BASEURL";

const PublicMemberApi = axios.create({
  baseURL: `${baseURL}/member/`,
});

const PrivateMemberApi = axios.create({
  baseURL: `${baseURL}/member/`,
  headers: {
    // Authorization: `Bearer ${}`
  }
});

interface props {
  memberSignId?: string;
  memberSignPassword?: string;
  memberName?: string;
  memberNickname?: string;
  disabled?: boolean;
  gender?: number;
  documentNumber?: number;
  profileMonsterId?: number;
  profileBackgroundId?: number;
}

const memberApi = {
  getMember: async () => {
    const res = PrivateMemberApi.get(
      '',
    )
    return res;
  },
  signUp: async ({memberSignId, memberSignPassword, memberName, memberNickname, disabled, gender}: props) => {
    const res = PublicMemberApi.post(
      '',
      {
        memberSignId: memberSignId,
        memberSignPassword: memberSignPassword,
        memberName: memberName,
        memberNickname: memberNickname,
        disabled: disabled,
        gender: gender,
      }
    );
    return res;
  },
  checkDisabled: async ({documentNumber}: props) => {
    const res = PublicMemberApi.post(
      'disabled-member',
      {
        documentNumber: documentNumber,
      }
    );
    return res;
  },
  login: async ({memberSignId, memberSignPassword}: props) => {
    const res = PublicMemberApi.post(
      'login',
      {
        memberSignId: memberSignId,
        memberSignPassword: memberSignPassword,
      }
    );
    return res;
  },
  updateMember: async ({memberName, memberNickname, memberSignPassword, disabled, gender}: props) => {
    const res = PrivateMemberApi.patch(
      '',
      {
        memberName: memberName,
        memberNickname: memberNickname,
        memberSignPassword: memberSignPassword,
        disabled: disabled,
        gender: gender,

      }
    );
    return res;
  },
  updateProfile: async ({profileMonsterId, profileBackgroundId}: props) => {
    const res = PrivateMemberApi.patch(
      'profile',
      {
        profileMonsterId: profileMonsterId,
        profileBackgroundId: profileBackgroundId,
      }
    );
    return res;
  },
  deleteMember: async () => {
    const res = PrivateMemberApi.delete(
      '',
    );
    return res;
  },
  logout: async () => {
    const res = PrivateMemberApi.delete(
      '',
    );
    return res;
  },


};

export default memberApi;
