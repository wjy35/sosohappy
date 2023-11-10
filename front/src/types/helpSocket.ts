export interface helpSocket {
    connect: Function,
    send: Function,
    status: String,
    helpList: helpDetail[],
    connected: boolean,
    disConnect: Function,
    data: helpData,
    isSearching: boolean,
    getMemberId: Function,
    otherMemberPoint: point,
    otherMember: number|null,
}

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
    latitude: number,
    longitude: number,
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
