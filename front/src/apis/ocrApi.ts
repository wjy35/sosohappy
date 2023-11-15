import {PrivateInstance, PublicInstance, PublicMultipartInstance} from "@/apis/AXIOSUTILS";

const domain: string = 'ocr';

interface propsType {
    file?: any;
    name?: string;
    documentNumber?: string;
}

const ocrApi = {
    getImageInfo: async ({file, name}: propsType) => {
        const res = PublicMultipartInstance.post(
            `${domain}/send`,
            {
                file: file.uri,
                // name: name,
            }
        )
        return res;
    },
    checkAuth: async ({name, documentNumber}: propsType)=> {
        const res = PublicInstance.post(
            `${domain}/check`,
            {
                name: name,
                documentNumber: documentNumber,
            }
        )
        return res;
    }
}

export default ocrApi;
