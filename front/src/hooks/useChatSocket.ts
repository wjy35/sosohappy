import * as Stomp from "webstomp-client";
import {useState} from "react";
import useStore from "@/store/store";

function useChatSocket() {
    const [client, setClient] = useState(null);
    const {userInfo} = useStore();
    const [connected, setConnected] = useState(false);
    const [subscribe, setSubscribe] = useState('');

    function connect() {
        const clientInit = Stomp.client("wss://sosohappy.co.kr/chat/endpoint", {debug: false, binary: true});
        setClient(clientInit);
        clientInit.connect(
            {
                memberId: userInfo.memberId,
            },
            (frame) => {
                setConnected(true);
            },
            (error) => {
                console.log(error)
            },
        );
    }

    function getList() {
        subscribe && client.unsubscribe(subscribe);
        client.subscribe(
            `/topic/${userInfo.memberId}`,
            (frame) => {
                const body = JSON.parse(frame.body);
                console.log(body);
            },
            {
                id: "list"
            });
        setSubscribe('list');
    }

    function getDetail(chatRoomId: number) {
        subscribe && client.unsubscribe(subscribe);
        client.subscribe(
            `/topic/${userInfo.memberId}/${chatRoomId}`,
            (frame) => {
                const body = JSON.parse(frame.body);
                console.log(body);
            },
            {
                id: "detail"
            });
        setSubscribe('detail');
    }

    function disConnect() {
        if (!client) return;
        setConnected(false);
        setSubscribe('');
        client.disconnect(() => {
            console.log('socket disconnect')
        });
    }

    return {connect, connected, disConnect, getList, getDetail}
}
export default useChatSocket;
