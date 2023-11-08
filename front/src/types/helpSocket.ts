export interface helpData {
    helpEntity: helpEntity|null,
    otherMemberPoint: point|null,
}

export interface helpEntity {
    otherMemberId: number,
    category: {
        categoryId: number,
        categoryName: string,
        categoryImage: string,
    };
    longitude: number;
    latitude: number;
    content: string;
    place: string;
}

export interface point{
    x: number,
    y: number,
}

export interface helpDetail {
    memberId: number;
    nickname: string;
    category: {
        categoryId: number,
        categoryName: string,
        categoryImage: string,
    };
    longitude: number;
    latitude: number;
    content: string;
    place: string;
}

export interface helpSocket {
    connect: Function,
    send: Function,
    status: String,
    helpList: helpDetail[],
    connected: boolean,
    disConnect: Function,
    data: helpData
}
