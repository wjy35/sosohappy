import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'help-match';

interface propsType {
    latitude?: number;
    longitude?: number;
    helperMemberId?: number;
    disabledMemberId?: number;
    memberId?: number;
    otherMemberId?: number;
    fortuneCookieId?: number;
}

const helpMatchApi = {
    getHelpStatus: async () => {
        const res = PrivateInstance.get(
            `${domain}/match/status`,
        );
        return res;
    },
    sendPosition: async ({latitude, longitude, memberId, otherMemberId}: propsType) => {
        const res = PrivateInstance.post(
            `${domain}/point`,
            {
                latitude: latitude,
                longitude: longitude,
                memberId: memberId,
                otherMemberId: otherMemberId,
            }
        );
        return res;
    },
    acceptHelp: async ({helperMemberId, disabledMemberId}: propsType)=>{
        const res = PrivateInstance.post(
            `${domain}/accept`,
            {
                helperMemberId: helperMemberId,
                disabledMemberId: disabledMemberId,
            }
        );
        return res;
    },
    cancelHelp: async ({memberId}: propsType) => {
        const res = PrivateInstance.post(
            `${domain}/cancel/help`,
            {
                memberId: memberId,
            }
        )
        return res;
    },
    cancelMatch: async ({memberId}: propsType) => {
        const res = PrivateInstance.post(
            `${domain}/cancel/match`,
            {
                memberId: memberId,
            }
        )
        return res;
    },
    helpArrive: async ({memberId}: propsType) => {
        const res = PrivateInstance.post(
            `${domain}/arrival`,
            {
                memberId: memberId,
            }
        )
        return res;
    },
    helpComplete: async ({memberId}: propsType) => {
        const res = PrivateInstance.post(
            `${domain}/complete`,
            {
                memberId: memberId,
            }
        )
        return res;
    },
    getFortuneList: async () => {
        const res = PrivateInstance.get(
            `${domain}/fortune-cookie/list`
        )
        return res;
    },
    openFortuneCookie: async ({fortuneCookieId}: propsType)=>{
        const res = PrivateInstance.delete(
            `${domain}/fortune-cookie/use/${fortuneCookieId}`
        )
        return res;
    }
};

export default helpMatchApi;
