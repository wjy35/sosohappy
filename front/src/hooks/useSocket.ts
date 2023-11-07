import * as Stomp from "webstomp-client";
import {useEffect, useState} from "react";

function useSocket(){
    const [client, setClient] = useState(null);
    const [memberId, setMemberId] = useState(1);
    const [status, setStatus] = useState<string>('');
    const [helpList, setHelpList] = useState([]);
    const [subscribe, setSubscribe] = useState('');
    const [connected, setConnected] = useState(false);

    function connect() {
        // wss://sosohappy.co.kr/help-match-socket/endpoint
        const clientInit = Stomp.client("wss://sosohappy.co.kr/help-match-socket/endpoint", {debug: false, binary: true});
        setClient(clientInit);
        clientInit.connect(
            {
                memberId:memberId,
            },
            (frame) => {
                setConnected(true)
                clientInit.subscribe(
                    `/topic/match/status/${memberId}`,
                    (frame) => {
                        // console.log("status", frame);
                        const body = JSON.parse(frame.body);
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
        client.disconnect(()=>{console.log('socket disconnect')});
    }

    function getList(){
        client.subscribe(
            `/topic/match/list/${memberId}`,
            (frame) => {
                const body = JSON.parse(frame.body);
                if (body.receiveMatchType === 'PUSH'){
                    setHelpList([...helpList, ...body.receiveMatchList])
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
        }
    }, [status]);

    return {connect, send, status, helpList, connected, disConnect};
}

export default useSocket;
