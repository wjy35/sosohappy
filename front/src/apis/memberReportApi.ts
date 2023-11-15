import {PrivateInstance, PublicInstance} from "@/apis/AXIOSUTILS";

const domain = 'member-report';

interface sirenProps{
    reportingMemberId: number,
    reportedMemberId: number,
}

const memberReportApi = {
  siren: async ({reportingMemberId, reportedMemberId}: sirenProps) => {
    const res = await PrivateInstance.post(
        `${domain}/report/`,{
            reportingMemberId: reportingMemberId,
            reportedMemberId: reportedMemberId,
        }
    )
    return res;
  }
};

export default memberReportApi;
