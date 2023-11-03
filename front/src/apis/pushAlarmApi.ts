import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const notificationDomain = 'notification';

interface propsType {
    fcmToken?: string,
}

const pushAlarmApi = {
    insertFcmToken: async ({fcmToken} : propsType) => {
        const res = PrivateInstance.post(
            `${notificationDomain}/`, {
                deviceToken: fcmToken,
            }
        )
        return res;
    }
};

export default pushAlarmApi;
