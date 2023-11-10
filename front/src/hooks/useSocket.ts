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
    const [otherMember, setOtherMember] = useState<number|null>(null);
    const [isSearching, setIsSearching] = useState(false);
    const [otherMemberPoint, setOtherMemberPoint] = useState<point>();
    const {userInfo} = useStore();

    function getMemberId(newMemberId: number) {
        setMemberId(newMemberId)
    }

    function connect() {
        // wss://sosohappy.co.kr/help-match-socket/endpoint
        setStatus('');
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
        if (!client) return;
        if (subscribe){
            client.unsubscribe(subscribe);
            // console.log(subscribe);
        }
        if (status === 'DEFAULT'){
            getList();
        } else if (status === 'helpMatchStatus'){

        }
    }, [status]);

    useEffect(() => {
        const init = async () => {
            try {
                const res = await helpMatchApi.getHelpStatus();
                if (res.status === 200){
                    const body = res.data.result.matchStatus;
                    setData(body.data);
                    setStatus(body.helpMatchStatus);
                    body.data.helpEntity?.otherMemberId&&setOtherMember(body.data.helpEntity.otherMemberId);
                }
            } catch (err) {
                console.log(err)
            }
        }
        userInfo&&init();
    }, [userInfo])

    return {connect, send, status, helpList, connected, disConnect, data, isSearching, getMemberId, otherMemberPoint, otherMember};
}

export default useSocket;
