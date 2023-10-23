import { View, Text, Image, TouchableOpacity } from "react-native"
import Modal from "react-native-modal"

import FishThumbnail from "@/assets/img/fish-thumbnail.png"

import BottomSheetStyle from "@/styles/BottomSheetStyle";

const BottomSheet = () => {
    return(
        <>
            <Modal
                isVisible={true}
                style={BottomSheetStyle.modal}

            >
                <View style={BottomSheetStyle.modalContent}>
                    <View style={BottomSheetStyle.styleLine}></View>
                    <View style={BottomSheetStyle.modalTitleWrap}>
                        <View style={BottomSheetStyle.modalTitleCategory}>
                            <Text style={BottomSheetStyle.modalTitleCategoryText}>새로운 행운력</Text>
                        </View>
                        <Text style={BottomSheetStyle.modalTitleContent}>보행이 불편합니다!</Text>
                        <Text style={BottomSheetStyle.modalTitleDescription}>5km · 지하철 플랫폼 내부</Text>
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
                                    <Text style={BottomSheetStyle.userInfoName}>김싸피</Text>
                                </View>
                            </View>
                        </View>
                        <View style={BottomSheetStyle.userInfoItemWrap}>
                            <Text style={BottomSheetStyle.userTitle}>카테고리</Text>
                            <View style={BottomSheetStyle.userCategoryContentWrap}>
                                <View style={BottomSheetStyle.userCategory}>
                                    <Text style={BottomSheetStyle.userCategoryText}>보행</Text>
                                </View>
                                <View style={BottomSheetStyle.userCategory}>
                                    <Text style={BottomSheetStyle.userCategoryText}>카테고리2</Text>
                                </View>
                            </View>
                        </View>
                    </View>


                    <View style={BottomSheetStyle.matchingWrap}>
                        <View style={BottomSheetStyle.styleCircle}></View>
                        <Text style={BottomSheetStyle.matchingText}>매칭된 나눔이/모음이를 곧 만날 수 있어요.</Text>
                    </View>

                    <TouchableOpacity activeOpacity={0.7}>
                        <View style={BottomSheetStyle.connectButton}>
                            <Text style={BottomSheetStyle.connectButtonText}>연결하기</Text>
                        </View>
                    </TouchableOpacity>
                </View>
            </Modal>
        </>
    );
}

export default BottomSheet;