import * as Stomp from "webstomp-client";
import {useState} from "react";
import useStore from "@/store/store";
import {SingleChatInfo} from "@/types";

function useChatSocket() {
    const [client, setClient] = useState(null);
    const {userInfo} = useStore();
    const [connected, setConnected] = useState(false);
    const [subscribe, setSubscribe] = useState('');
    const [helpChatList, setHelpChatList] = useState<SingleChatInfo[]>([]);
    const [msgList, setMsgList] = useState([]);

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
                setMsgList([...msgList, body])
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

    function getHelpChatList (chatList: any[]) {
        setHelpChatList(chatList);
    }

    function getMsgList (MsgList: any[]) {
        setMsgList(MsgList);
    }

    function addMsg (Msg: any) {
        setMsgList([...msgList, Msg]);
    }

    return {connect, connected, disConnect, getList, getDetail, getHelpChatList, helpChatList, getMsgList, msgList, addMsg}
}
export default useChatSocket;
