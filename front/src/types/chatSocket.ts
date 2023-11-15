export interface ChatSocket {
    connect: Function;
    connected: boolean;
    disConnect: Function;
    getList: Function;
    getDetail: Function;
    getHelpChatList: Function;
    helpChatList: SingleChatInfo[];
    getMsgList: Function;
    msgList: any[];
    addMsg: Function;
}

export interface SingleChatInfo {
    chatRoomId: number;
    memberList: number[];
    currentChat: recentChatInfo;
}

export interface recentChatInfo {
    type: number;
    memberId: number;
    content: string;
    timestamp: string;
}
