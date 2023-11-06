import {useState} from "react"
import { View, Text, Image, TouchableOpacity } from "react-native"
import Modal from "react-native-modal"

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import BottomSheetStyle from "@/styles/BottomSheetStyle";

import axios from "axios"
import { useNavigation } from "@react-navigation/native";

enum matchingStatus {
    Wait,
    Matching,
    Arrive
}

interface propsType{
    updateBottomSheetStatus: Function,
    selectedHelp: helpDetail
}

interface helpDetail {
    memberId: number,
    nickname: string,
    categoryList: {
        categoryId: number,
        categoryName: string,
        categoryImage: string,
    }[],
    longitude: number,
    latitude: number,
    content: string,
    place: string,
}

const BottomSheet = ({updateBottomSheetStatus, selectedHelp}: propsType) => {
    const [currentMatchingStatus, setCurrentMatchingStatus] = useState<matchingStatus>(matchingStatus.Wait);
    const navigation = useNavigation();
    return(
        <>
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
                                currentMatchingStatus === matchingStatus.Wait ?
                                <Text style={BottomSheetStyle.modalTitleCategoryText}>새로운 행운력</Text>
                                :
                                currentMatchingStatus === matchingStatus.Matching ?
                                <Text style={BottomSheetStyle.modalTitleCategoryText}>매칭완료</Text>
                                :
                                currentMatchingStatus === matchingStatus.Arrive ?
                                <Text style={BottomSheetStyle.modalTitleCategoryText}>컨택완료</Text>
                                :
                                <></>
                            }

                        </View>
                        {
                            currentMatchingStatus === matchingStatus.Wait ?
                            <Text style={BottomSheetStyle.modalTitleContent}>{selectedHelp.content}</Text>
                            :
                            currentMatchingStatus === matchingStatus.Matching ?
                            <Text style={BottomSheetStyle.modalTitleContent}>컨택 진행중입니다.</Text>
                            :
                            currentMatchingStatus === matchingStatus.Arrive ?
                            <Text style={BottomSheetStyle.modalTitleContent}>나눔이/모음이 컨택완료!</Text>
                            :
                            <></>

                        }
                        {
                            currentMatchingStatus === matchingStatus.Wait ?
                            <Text style={BottomSheetStyle.modalTitleDescription}>5km · {selectedHelp.place}</Text>
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
                                    <Text style={BottomSheetStyle.userCategoryText}>{selectedHelp.categoryList[0].categoryName}</Text>
                                </View>
                            </View>
                        </View>
                    </View>


                    <View style={BottomSheetStyle.matchingWrap}>
                        <View style={BottomSheetStyle.styleCircle}></View>
                        <Text style={BottomSheetStyle.matchingText}>매칭된 나눔이/모음이를 곧 만날 수 있어요.</Text>
                    </View>

                    {
                        currentMatchingStatus === matchingStatus.Wait ?
                        <TouchableOpacity activeOpacity={0.7} onPress={() => navigation.navigate('Chat')}>
                            <View style={BottomSheetStyle.connectButton}>
                                <Text style={BottomSheetStyle.connectButtonText}>연결하기</Text>
                            </View>
                        </TouchableOpacity>
                        :
                        <TouchableOpacity activeOpacity={0.7} onPress={() => updateBottomSheetStatus(false)}>
                            <View style={BottomSheetStyle.connectButton}>
                                <Text style={BottomSheetStyle.connectButtonText}>취소하기</Text>
                            </View>
                        </TouchableOpacity>
                    }
                </View>
            </Modal>
        </>
    );
}

export default BottomSheet;
