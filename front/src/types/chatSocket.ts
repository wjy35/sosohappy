export interface ChatSocket {
    connect: Function;
    connected: boolean;
    disConnect: Function;
    getList: Function;
    getDetail: Function;
    helpChatList: SingleChatInfo[];
    msgList: any[];
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
