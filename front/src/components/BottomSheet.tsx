import {useEffect, useState} from "react"
import { View, Text, Image, TouchableOpacity, Alert } from "react-native"
import Modal from "react-native-modal"

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import BottomSheetStyle from "@/styles/BottomSheetStyle";

import { useNavigation } from "@react-navigation/native";
import helpMatchApi from "@/apis/helpMatchApi";
import {helpDetail} from "@/types";
import useStore from "@/store/store";
import memberApi from "@/apis/memberApi";
import memberReportApi from "@/apis/memberReportApi";
import {type1, type2, type3, type4} from "@/assets/sosomon";

interface propsType{
    updateBottomSheetStatus: Function,
    selectedHelp: helpDetail|undefined,
    status: string,
}

const BottomSheet = ({updateBottomSheetStatus, selectedHelp, status}: propsType) => {
    const navigation = useNavigation();
    const {userInfo} = useStore();
    const [helpUserInfo, setHelpUserInfo] = useState<any|null>(null);
    const [src, setSrc] = useState();

    useEffect(() => {
        if (status === 'WAIT_COMPLETE'){
            setHelpUserInfo(userInfo);
            const type = Math.floor((userInfo.profileMonsterId-1)/10) + 1;
            const level = (userInfo.profileMonsterId % 10 === 0)?10:userInfo.profileMonsterId%10;
            if (type === 1){
                setSrc(type1[level-1])
            } else if (type === 2){
                setSrc(type2[level-1])
            } else if (type === 3){
                setSrc(type3[level-1])
            } else {
                setSrc(type4[0])
            }
        } else {
            getUserInfo();
        }
    }, []);

    const getUserInfo = async () => {
        try {
            const res = await memberApi.getUserInfo({
                memberId: status==='DEFAULT'?selectedHelp.memberId:selectedHelp.otherMemberId
            })
            if (res.status === 200) {
                setHelpUserInfo(res.data.result.member);
                const type = Math.floor((res.data.result.member.profileMonsterId-1)/10) + 1;
                const level = (res.data.result.member.profileMonsterId % 10 === 0)?10:res.data.result.member.profileMonsterId%10;
                if (type === 1){
                    setSrc(type1[level-1])
                } else if (type === 2){
                    setSrc(type2[level-1])
                } else if (type === 3){
                    setSrc(type3[level-1])
                } else {
                    setSrc(type4[0])
                }
            }
        } catch (err) {
            console.log(err)
        }

    }

    const acceptHelp = async () => {
        try {
            const res = await helpMatchApi.acceptHelp({
                helperMemberId: userInfo.memberId,
                disabledMemberId: selectedHelp?.memberId,
            })
            if(res.status === 200){
                updateBottomSheetStatus(false);
            }
        } catch (err) {
            console.log(err)
            if (err.response.status === 406) {
                Alert.alert('도움 요청', '만료된 도움 요청입니다', [
                    {text: '확인', onPress: () => updateBottomSheetStatus(false)}
                ])

            }
        }
    }

    const cancelHelp = async () => {
        try {
            const res = await helpMatchApi.cancelHelp({
                memberId: userInfo.memberId,
            })
            if (res.status === 200) {
                updateBottomSheetStatus(false);
            }
        } catch (err) {
            console.log(err)
        }
    }

    const arriveHelp = async () => {
        try {
            const res = await helpMatchApi.helpArrive({
                memberId: userInfo.memberId,
            })
            if (res.status === 200) {
                updateBottomSheetStatus(false);
            }
        } catch (err) {
            console.log(err);
        }
    }

    const completeHelp = async () => {
        try {
            const res = await helpMatchApi.helpComplete({
                memberId: userInfo.memberId
            })
            if (res.status === 200) {
                updateBottomSheetStatus(false);
            }
        } catch (err) {
            console.log(err);
        }
    }

    const sirenCall = () => {
        Alert.alert("신고접수", "해당 사용자를 신고접수하시겠습니까?",[
            {text:'취소하기', onPress:() => {}},
            {text:'신고하기', onPress:async () => {
                const myInfoRes = await memberApi.getMember();
                if(myInfoRes.status === 200){
                const myMemberId = myInfoRes.data.result.member.memberId;

                const sirenRes = await memberReportApi.siren({reportingMemberId: myMemberId, reportedMemberId:selectedHelp.data.helpEntity.otherMemberId});
                    if(sirenRes.status === 200){
                        Alert.alert("신고 접수가 성공적으로 처리되었습니다.");
                    }
                }
            }}
        ])
    }

    return(
        <>
            {
                selectedHelp && (
                    <Modal
                        isVisible={true}
                        style={BottomSheetStyle.modal}
                        onBackdropPress={()=>updateBottomSheetStatus(false)}
                    >
                        <View style={BottomSheetStyle.modalContent}>
                            <View style={BottomSheetStyle.styleLine}></View>
                            <View style={BottomSheetStyle.modalTitleWrap}>
                                <View style={BottomSheetStyle.modalTitleCategory}>

                                    {
                                        status === 'DEFAULT' ?
                                            <Text style={BottomSheetStyle.modalTitleCategoryText}>새로운 행운력</Text>
                                            :
                                            status === 'ON_MOVE' ?
                                                <Text style={BottomSheetStyle.modalTitleCategoryText}>매칭완료</Text>
                                                :
                                                status === 'WAIT_COMPLETE' ?
                                                    <Text style={BottomSheetStyle.modalTitleCategoryText}>행운 나눔 중</Text>
                                                    : <></>
                                    }

                                </View>
                                {
                                    status === 'DEFAULT' ?
                                        <Text style={BottomSheetStyle.modalTitleContent}>{selectedHelp.content}</Text>
                                        :
                                        status === 'ON_MOVE' ?
                                            <Text style={BottomSheetStyle.modalTitleContent}>컨택 진행중입니다.</Text>
                                            :
                                            status === 'WAIT_COMPLETE' ?
                                                <Text style={BottomSheetStyle.modalTitleContent}>도움이 완료되면 완료를 눌러주세요</Text>
                                                : <></>
                                }
                                {
                                    status === 'DEFAULT' ?
                                        <Text style={BottomSheetStyle.modalTitleDescription}>약 {Math.round(selectedHelp.distance)}m {selectedHelp.place&&('· '+selectedHelp.place)}</Text>
                                        :
                                        <Text style={BottomSheetStyle.modalTitleDescription}>도움을 제공하고, 승인해주세요.</Text>
                                }
                            </View>

                            <View style={BottomSheetStyle.userInfoWrap}>
                                <View style={BottomSheetStyle.userInfoItemWrap}>
                                    <Text style={BottomSheetStyle.userTitle}>요청자</Text>
                                    <View style={BottomSheetStyle.userInfoContentWrap}>
                                        <View style={BottomSheetStyle.userInfoContentFlexWrap}>
                                            <Image
                                                source={src?src:FishThumbnail}
                                                style={BottomSheetStyle.userThumbnail}
                                            />
                                            <Text style={BottomSheetStyle.userInfoName}>{helpUserInfo?.nickname?helpUserInfo.nickname:''}</Text>
                                        </View>
                                    </View>
                                </View>
                                <View style={BottomSheetStyle.userInfoItemWrap}>
                                    <Text style={BottomSheetStyle.userTitle}>카테고리</Text>
                                    <View style={BottomSheetStyle.userCategoryContentWrap}>
                                        <View style={BottomSheetStyle.userCategory}>
                                            <Text style={BottomSheetStyle.userCategoryText}>{selectedHelp.category.categoryName}</Text>
                                        </View>
                                    </View>
                                </View>
                            </View>


                            <View style={BottomSheetStyle.matchingWrap}>
                                <View style={BottomSheetStyle.styleCircle}></View>
                                <Text style={BottomSheetStyle.matchingText}>매칭된 나눔이/모음이를 곧 만날 수 있어요.</Text>
                                <TouchableOpacity activeOpacity={0.7} style={BottomSheetStyle.sirenWrap} onPress={() => sirenCall()}>
                                    <View style={BottomSheetStyle.sirenButton}>
                                        <Text style={BottomSheetStyle.sirenText}>신고하기</Text>
                                    </View>
                                </TouchableOpacity>
                            </View>

                            {
                                status === 'DEFAULT' ? (
                                    <View>
                                        <TouchableOpacity activeOpacity={0.7} onPress={acceptHelp}>
                                            <View style={BottomSheetStyle.connectButton}>
                                                <Text style={BottomSheetStyle.connectButtonText}>연결하기</Text>
                                            </View>
                                        </TouchableOpacity>
                                    </View>
                                )
                                :
                                status === 'ON_MOVE' ? (
                                    <View style={{flexDirection: 'row'}}>
                                        <View style={{flex: 6}}>
                                            <TouchableOpacity activeOpacity={0.7} onPress={arriveHelp}>
                                                <View style={BottomSheetStyle.connectButton}>
                                                    <Text style={BottomSheetStyle.connectButtonText}>도착완료</Text>
                                                </View>
                                            </TouchableOpacity>
                                        </View>
                                        <View style={{flex: 1}}></View>
                                        <View style={{flex: 6}}>
                                            <TouchableOpacity activeOpacity={0.7} onPress={cancelHelp}>
                                                <View style={BottomSheetStyle.cancelButton}>
                                                    <Text style={BottomSheetStyle.connectButtonText}>취소하기</Text>
                                                </View>
                                            </TouchableOpacity>
                                        </View>
                                    </View>
                                )
                                :
                                status === 'WAIT_COMPLETE' ? (
                                    <View style={{flexDirection: 'row'}}>
                                        <View style={{flex: 6}}>
                                            <TouchableOpacity activeOpacity={0.7} onPress={completeHelp}>
                                                <View style={BottomSheetStyle.connectButton}>
                                                    <Text style={BottomSheetStyle.connectButtonText}>도움완료</Text>
                                                </View>
                                            </TouchableOpacity>
                                        </View>
                                        <View style={{flex: 1}}></View>
                                        <View style={{flex: 6}}>
                                            <TouchableOpacity activeOpacity={0.7} onPress={cancelHelp}>
                                                <View style={BottomSheetStyle.cancelButton}>
                                                    <Text style={BottomSheetStyle.connectButtonText}>취소하기</Text>
                                                </View>
                                            </TouchableOpacity>
                                        </View>
                                    </View>
                                )
                                : <></>

                            }
                        </View>
                    </Modal>
                )
            }
        </>
    );
}

export default BottomSheet;
