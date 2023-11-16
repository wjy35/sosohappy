import * as Stomp from "webstomp-client";
import {useState} from "react";
import useStore from "@/store/store";
import {SingleChatInfo} from "@/types";

function useChatSocket() {
    const [client, setClient] = useState(null);
    const {userInfo} = useStore();
    const [connected, setConnected] = useState(false);
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
                console.log('chatSocket connected')
                setConnected(true);
            },
            (error) => {
                console.log(error)
            },
        );
    }

    function getList() {
        client.unsubscribe('list');
        client.unsubscribe('detail');
        setMsgList([]);
        client.subscribe(
            `/topic/${userInfo.memberId}`,
            (frame) => {
                const body = JSON.parse(frame.body);
                console.log(body);
            },
            {
                id: "list"
            });
    }

    function getDetail(chatRoomId: number) {
        client.unsubscribe('list');
        client.unsubscribe('detail');
        setHelpChatList([]);
        client.subscribe(
            `/topic/${userInfo.memberId}/${chatRoomId}`,
            (frame) => {
                const body = JSON.parse(frame.body);
                setMsgList([...msgList, ...body])
            },
            {
                id: "detail"
            });

    }

    function disConnect() {
        if (!client) return;
        client.unsubscribe('list')
        client.unsubscribe('detail')
        setConnected(false);
        client.disconnect(() => {
            console.log('chatSocket disconnect')
        });
    }

    return {connect, connected, disConnect, getList, getDetail, helpChatList, msgList}
}
export default useChatSocket;
