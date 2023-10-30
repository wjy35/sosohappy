import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const queryDomain = 'member-query';
const commandDomain = 'member-command';

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
        const res = PrivateInstance.get(
          `${queryDomain}/`,
        )
        return res;
    },
    signUp: async ({memberSignId, memberSignPassword, memberName, memberNickname, disabled, gender}: props) => {
        const res = PublicInstance.post(
          `${commandDomain}/`,
          {
            memberSignId: memberSignId,
            memberSignPassword: memberSignPassword,
            name: memberName,
            nickname: memberNickname,
            disabled: disabled,
            gender: gender,
          }
        );
        return res;
    },
    checkDisabled: async ({documentNumber}: props) => {
        const res = PublicInstance.post(
          `${queryDomain}/disabled-member/`,
          {
            documentNumber: documentNumber,
          }
        );
        return res;
    },
    login: async ({memberSignId, memberSignPassword}: props) => {
        const res = PublicInstance.post(
          `${commandDomain}/sign-in/`,
          {
            id: memberSignId,
            password: memberSignPassword,
          }
        );
        return res;
    },
    updateMember: async ({memberName, memberNickname, memberSignPassword, disabled, gender}: props) => {
        const res = PrivateInstance.patch(
          `${commandDomain}/`,
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
    deleteMember: async () => {
        const res = PrivateInstance.delete(
          `${commandDomain}/`,
        );
        return res;
    },
    logout: async () => {
        const res = PrivateInstance.delete(
          `${queryDomain}/sign-out/`,
        );
        return res;
    },
    checkNicknameDuplicate: async ({memberNickname}: props) => {
        const res = PublicInstance.get(
            `${queryDomain}/availability/nickname/${memberNickname}/`
        );
        return res;
    },
    checkIdDuplicate: async ({memberSignId}: props) => {
        const res = PublicInstance.get(
            `${queryDomain}/availability/memberSignId/${memberSignId}/`
        );
        return res;
    }

};

export default memberApi;
