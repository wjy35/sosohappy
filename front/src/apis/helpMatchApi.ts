import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'help-match';

interface propsType {
    latitude: number;
    longitude: number;
}

const helpMatchApi = {
    sendPosition: async ({latitude, longitude}: propsType) => {
        const res = PrivateInstance.post(
            `${domain}/point`,
            {
                latitude: latitude,
                longitude: longitude,
            }
        );
        return res;
    },

};

export default helpMatchApi;
