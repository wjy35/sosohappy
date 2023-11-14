import {useState} from "react"
import { View, Text, Image, TouchableOpacity } from "react-native"
import Modal from "react-native-modal"

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import BottomSheetStyle from "@/styles/BottomSheetStyle";

import { useNavigation } from "@react-navigation/native";
import helpMatchApi from "@/apis/helpMatchApi";
import {helpDetail} from "@/types";
import useStore from "@/store/store";

interface propsType{
    updateBottomSheetStatus: Function,
    selectedHelp: helpDetail|undefined,
    status: string,
}

const BottomSheet = ({updateBottomSheetStatus, selectedHelp, status}: propsType) => {
    const navigation = useNavigation();
    const {userInfo} = useStore();

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
                                                <></>
                                    }

                                </View>
                                {
                                    status === 'DEFAULT' ?
                                        <Text style={BottomSheetStyle.modalTitleContent}>{selectedHelp.content}</Text>
                                        :
                                        status === 'ON_MOVE' ?
                                            <Text style={BottomSheetStyle.modalTitleContent}>컨택 진행중입니다.</Text>
                                            :
                                            <></>
                                }
                                {
                                    status === 'DEFAULT' ?
                                        <Text style={BottomSheetStyle.modalTitleDescription}>약 {Math.round(selectedHelp.distance)}m · {selectedHelp.place}</Text>
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
                                                source={FishThumbnail}
                                                style={BottomSheetStyle.userThumbnail}
                                            />
                                            <Text style={BottomSheetStyle.userInfoName}>{selectedHelp.nickname}</Text>
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
                            </View>

                            {
                                status === 'DEFAULT' ?
                                    <TouchableOpacity activeOpacity={0.7} onPress={acceptHelp}>
                                        <View style={BottomSheetStyle.connectButton}>
                                            <Text style={BottomSheetStyle.connectButtonText}>연결하기</Text>
                                        </View>
                                    </TouchableOpacity>
                                    :
                                    <View style={{flexDirection: 'row'}}>
                                        <View style={{flex: 1}}>
                                            <TouchableOpacity activeOpacity={0.7} onPress={arriveHelp}>
                                                <View style={BottomSheetStyle.connectButton}>
                                                    <Text style={BottomSheetStyle.connectButtonText}>도착완료</Text>
                                                </View>
                                            </TouchableOpacity>
                                        </View>
                                        <View style={{flex: 1}}>
                                            <TouchableOpacity activeOpacity={0.7} onPress={cancelHelp}>
                                                <View style={BottomSheetStyle.cancelButton}>
                                                    <Text style={BottomSheetStyle.connectButtonText}>취소하기</Text>
                                                </View>
                                            </TouchableOpacity>
                                        </View>
                                    </View>
                            }
                        </View>
                    </Modal>
                )
            }
        </>
    );
}

export default BottomSheet;
