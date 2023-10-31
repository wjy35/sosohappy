import axios, { AxiosError, AxiosRequestConfig } from "axios";
import {Alert} from "react-native";
import {baseURL} from "@/apis/BASEURL";
import RNSecureStorage, {ACCESSIBLE} from "rn-secure-storage";

const CONTENT_TYPE = "application/json; charset=utf-8";
const TIMEOUT = 1000;

export const PublicInstance = axios.create({
    baseURL: baseURL,
    timeout: TIMEOUT,
});

export const PrivateInstance = axios.create({
    baseURL: baseURL,
    timeout: TIMEOUT,
})

const getValueFor = async (key: string) => {
    return await RNSecureStorage.get(key);
};

const getAuthorizationHeader = async (tokenKey: string) => {
    return await getValueFor(tokenKey);
};

const setPublicHeaders = async (config: any) => {
    // default header 설정
    config.headers["Content-Type"] = CONTENT_TYPE;
    return config;
};

const setPrivateHeaders = async (config: any) => {
    // default header 설정
    config.headers["Content-Type"] = CONTENT_TYPE;
    config.headers["authorization"] = await getAuthorizationHeader("accessToken");
    return config;
};

// TODO: refresh token 로직 수정
// const refreshAccessTokenAndRetry = async (config: AxiosRequestConfig) => {
//     // accessToken 만료시 refreshToken으로 재발급
//     console.log("refreshAccessTokenAndRetry");
//     try {
//         const response = await axios.post(
//             `${baseURL}/user/token`,
//             {},
//             {
//                 headers: {
//                     "Content-Type": CONTENT_TYPE,
//                     Authorization: await getAuthorizationHeader("refreshToken"),
//                 },
//             },
//         );
//         console.log("response data ㅇㅇㅇ :", response.status);
//         if (response.status === 201) {
//             const newAccessToken = response.data.data.accessToken;
//             await RNSecureStorage.set("accessToken", newAccessToken, {accessible: ACCESSIBLE.WHEN_UNLOCKED});
//             if (!config.headers) {
//                 config.headers = {};
//             }
//             config.headers["Authorization"] = `Bearer ${newAccessToken}`;
//             return axios(config);
//         }
//         console.error("refreshAccessTokenAndRetry error :", response);
//         return Promise.reject(response);
//     } catch (error: any) {
//         console.error(error.response.status);
//         if (error.response.status === 401) {
//             await logout();
//             RNSecureStorage.set("session_expire", 'expired', {accessible: ACCESSIBLE.WHEN_UNLOCKED});
//             Alert.alert("토큰 갱신에 실패했습니다. 다시 로그인 해주세요.");
//             return Promise.reject(error);
//         }
//     }
// };

const handleResponseError = async (error: AxiosError) => {
    if (!error.response) return Promise.reject(error);
    const { status, config } = error.response;
    console.log("status :", status);

    switch (status) {
        case 400:
            Alert.alert(
                "올바르지 않은 내용을 입력하셨습니다. 다시 확인해주세요.",
            );
            break;
            // TODO: refresh token logic 수정 후 주석 제거
        // case 401:
        //     return await refreshAccessTokenAndRetry(config);
        case 409:
            Alert.alert("이미 등록되어 있는 사용자입니다.");
        case 500:
            Alert.alert("시스템 에러, 관리자에게 문의 바랍니다.");
            break;
        default:
            console.error(error);
            return Promise.reject(error);
    }
};

const handleResponseSuccess = (response: any) => {
    console.log("Success response");
    return response;
};

const handleRequestError = (error: AxiosError) => {
    console.error("handleRequestError :", error);
    return Promise.reject(error);
};

PrivateInstance.interceptors.request.use(setPrivateHeaders, handleRequestError);
PublicInstance.interceptors.request.use(setPublicHeaders, handleRequestError);
PrivateInstance.interceptors.response.use(handleResponseSuccess, handleResponseError);
PublicInstance.interceptors.response.use(handleResponseSuccess, handleResponseError);
