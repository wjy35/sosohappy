import * as Stomp from "webstomp-client";
import {useEffect, useState} from "react";
import {helpData, point} from "@/types";
import helpMatchApi from "@/apis/helpMatchApi";
import useStore from "@/store/store";

function useSocket(){
    const [client, setClient] = useState(null);
    const [memberId, setMemberId] = useState(2);
    const [status, setStatus] = useState<string>('');
    const [helpList, setHelpList] = useState([]);
    const [subscribe, setSubscribe] = useState('');
    const [connected, setConnected] = useState(false);
    const [data, setData] = useState<helpData>({
        helpEntity: null,
        otherMemberPoint: null,
    });

    function connect() {
        // wss://sosohappy.co.kr/help-match-socket/endpoint
        const clientInit = Stomp.client("wss://sosohappy.co.kr/help-match-socket/endpoint", {debug: false, binary: true});
        setClient(clientInit);
        clientInit.connect(
            {
                memberId:memberId,
            },
            (frame) => {
                clientInit.subscribe(
                    `/topic/match/status/${memberId}`,
                    (frame) => {
                        setConnected(true);
                        const body = JSON.parse(frame.body);
                        setData(body.data);
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
        setConnected(false);
        setStatus('');
        setSubscribe('');
        client.disconnect(()=>{console.log('socket disconnect')});
    }

    function getList(){
        client.subscribe(
            `/topic/match/list/${memberId}`,
            (frame) => {
                const body = JSON.parse(frame.body);
                if (body.matchListCommand === 'PUSH'){
                    setHelpList([...helpList, ...body.receiveMatchList])
                } else if (body.matchListCommand === 'POP') {


                }
                console.log(body);
            },
            {
                id:"list"
            });
        setSubscribe('list');
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
        setSubscribe('progress');
    }

    function send(payload: any){
        const data= {
            memberId:payload.memberId,
            nickname:payload.nickname,
            category:{
                categoryId:payload.category.categoryId,
                categoryName:payload.category.categoryName,
                categoryImage:payload.category.categoryImage,
            },
            longitude:payload.longitude,
            latitude:payload.latitude,
            content:payload.content,
            place:payload.place,
        };
        client.send( '/match', JSON.stringify(data));
    }

    useEffect(() => {
        if (subscribe){
            client.unsubscribe(subscribe);
            // console.log(subscribe);
        }
        if (status === 'DEFAULT'){
            getList();
        } else if (status === 'helpMatchStatus'){

        }
    }, [status]);

    return {connect, send, status, helpList, connected, disConnect, data};
}

export default useSocket;
