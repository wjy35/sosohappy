import {recommendInstance} from "@/apis/AXIOSUTILS";

const domain = "member-report";

const recommendApi = {
    recommend: async ({memberId}) => {
        const res = await recommendInstance.post(
            `recommend/${memberId}`
        )
        return res;
    }
}

export default recommendApi;