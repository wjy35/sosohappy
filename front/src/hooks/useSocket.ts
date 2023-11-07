import * as Stomp from "webstomp-client";
import {useEffect, useState} from "react";

function useSocket(){
    const [client, setClient] = useState(null);
    const [memberId, setMemberId] = useState(1);
    const [status, setStatus] = useState<string>('DEFAULT');
    const [helpList, setHelpList] = useState([]);

    function connect() {
        // wss://sosohappy.co.kr/help-match-socket/endpoint
        const clientInit = Stomp.client("wss://sosohappy.co.kr/help-match-socket/endpoint", {debug: false, binary: true});
        setClient(clientInit);
        clientInit.connect(
            {
                memberId:memberId,
            },
            (frame) => {
                console.log("socket: ", frame);
                clientInit.subscribe(
                    `/topic/match/status/${memberId}`,
                    (frame) => {
                        console.log("status", frame);
                        const body = JSON.parse(frame.body);
                        console.log(body.helpMatchStatus);
                        setStatus(body.helpMatchStatus);
                    },
                    {
                        id: "status"
                    }
                );
            },
            (error) => {
                console.log(error)
            },
        );
    }

    function disConnect() {
        client.disconnect();
    }

    function getList(){
        client.subscribe(
            `/topic/match/list/${memberId}`,
            (frame) => {
                console.log(frame);
                const body = JSON.parse(frame.body);
                console.log(body);
            },
            {
                id:"list"
            });
    }

    function getProgress() {
        client.subscribe(
            `/topic/match/progress/${memberId}`,
            (frame) => {
                console.log(frame);
                const body = JSON.parse(frame.body);
                console.log(body);
            },
            {
                id:"progress"
            });
    }

    function send(payload: any){
        // const data={
        //     memberId:1,
        //     nickname:"김싸피",
        //     categoryList:[
        //         {
        //             categoryId:1,
        //             categoryName:"모름",
        //             categoryImage:"모름"
        //         },
        //         {
        //             categoryId:2,
        //             categoryName:"모름",
        //             categoryImage:"모름"
        //         }
        //     ],
        //     longitude:127.04403462366,
        //     latitude:37.503325874722,
        //     content:"보행이 불편합니다.",
        //     place:"태영이네 집"
        // };
        const data={
            memberId:payload.memberId,
            nickname:payload.nickname,
            categoryList:[
                {
                    categoryId:payload.category.categoryId,
                    categoryName:payload.category.categoryName,
                    categoryImage:payload.category.categoryImage,
                },
            ],
            longitude:payload.longitude,
            latitude:payload.latitude,
            content:payload.content,
            place:payload.place,
        };
        client.send( '/match', JSON.stringify(data));
    }

    return {connect, send, status, helpList};
}

export default useSocket;
