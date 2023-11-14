import CommonLayout from "@/components/CommonLayout";
import CreateHelpSelectCategory from "@/components/CreateHelpSelectCategory";
import {Text, TouchableOpacity, View, Alert} from "react-native";
import CreateHelpStyle from "@/styles/CreateHelpStyle";
import AllCategoryWrap from "@/components/AllCategoryWrap";
import PlainInput from "@/components/PlainInput";
import CategoryWrapStyle from "@/styles/CategoryWrapStyle";
import {SvgXml} from "react-native-svg";
import RecommendCategoryWrap from "@/components/RecommendCategoryWrap";
import SimilarRecommendCategoryWrap from "@/components/SimilarRecommendCategoryWrap";
import React, {useEffect, useState} from "react";
import useStore from "@/store/store";
import useInput from "@/hooks/useInput";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import {ChatSocket, helpSocket, point} from "@/types";
import MapLoading from "@/components/MapLoading";

interface propsType{
    location: point;
    socket: helpSocket,
    chatSocket: ChatSocket
}

const CreateHelp = (({location, socket, chatSocket}: propsType) => {
    const [category, setCategory] = useState<any>(null);
    const {userInfo} = useStore();
    const navigation = useNavigation();

    useEffect(() => {
        if (socket.status === 'WAIT_COMPLETE'){
            navigation.navigate('Map');
        } else if (socket.status === 'ON_MATCH_PROGRESS'){
            // navigation.navigate('Main');
        } else if (socket.status === 'ON_MOVE'){
            navigation.navigate('Map');
        }
    }, [socket.status]);

    const selectCategory = (categoryInfo: any) => {
        setCategory(categoryInfo);
    }

    const checkContent = (newText: string) => {
        helpContent.updateIsValid(newText !== "");
    };

    const checkLocationDetail = (newText: string) => {
        locationDetail.updateIsValid(newText !== "");
    };

    const helpContent = useInput({
        placeholder: 'ex) 보행이 불편합니다',
        initialIsValid: false,
        onChange: checkContent,
    });

    const locationDetail = useInput({
        placeholder: 'ex) 지하철역 1번 출구',
        initialIsValid: false,
        onChange: checkLocationDetail,
    });

    const sendHelp = () => {
        Alert.alert("개인정보 제3자 제공 동의", '"소소행"앱이 사용자의 위치를 사용하도록 허용하시겠습니까?',
        [
            {text: '취소', onPress: () => {}},
            {
                text: '확인',
                onPress: () => {
                    startPayload();
                }
            }
        ]);
        
    }

    const startPayload = () => {
        const payload = {
            memberId: userInfo.memberId,
            nickname: userInfo.nickname,
            category: {
                categoryId: category.categoryId,
                categoryName: category.categoryName,
                categoryImage: category.categoryImage,
            },
            longitude: location.longitude,
            latitude: location.latitude,
            content: helpContent.text,
            place: locationDetail.text,
        };
        socket.send(payload);
    }

    useFocusEffect(
        React.useCallback(() => {
            const connect = () => {
                if (socket.connected) return;
                socket.connect();
            }
            connect();
            return () => {};
        }, [socket.connected])
    )

    return (
        <>
            {
                socket.isSearching && (
                    <MapLoading socket={socket}/>
                )
            }
            <CommonLayout footer={true} headerType={0} nowPage={'Help'}>
                <View>
                    <View style={CreateHelpStyle.createHelpTitleWrap}>
                        <Text style={CreateHelpStyle.createHelpTitle}>
                            반가워요!{"\n"}
                            어떤 도움이 필요하신가요?
                        </Text>
                        <AllCategoryWrap category={category} selectCategory={selectCategory}/>
                    </View>
                    {
                        category ? (
                            <View style={CreateHelpStyle.detailInputWrap}>
                                <View style={CreateHelpStyle.inputTitle}>
                                    <Text style={CreateHelpStyle.inputTitleText}>도움 내용을 입력해 주세요</Text>
                                </View>
                                <PlainInput {...helpContent}/>
                                <View style={CreateHelpStyle.inputTitle}>
                                    <Text style={CreateHelpStyle.inputTitleText}>상세 위치를 입력해 주세요</Text>
                                </View>
                                <PlainInput {...locationDetail}/>
                                <View style={CreateHelpStyle.categoryWrap}>
                                    <View style={CreateHelpStyle.categoryTitle}>
                                        <Text style={CreateHelpStyle.categoryTitleText}>카테고리</Text>
                                    </View>
                                    <View style={[CategoryWrapStyle.categoryItemWrap]}>
                                        <SvgXml
                                            xml={category.categoryImage}
                                            style={CategoryWrapStyle.categoryItemImg}
                                        />
                                        <Text style={CategoryWrapStyle.categoryItemText}>{category.categoryName}</Text>
                                    </View>
                                </View>
                                <View>
                                    <TouchableOpacity activeOpacity={0.7} onPress={()=>{(helpContent.isValid&&locationDetail.isValid)&&sendHelp()}}>
                                        <View style={[CreateHelpStyle.submitButtonWrap, (helpContent.isValid&&locationDetail.isValid)&&CreateHelpStyle.submitButtonActive]}>
                                            <Text style={[CreateHelpStyle.submitButtonText]}>등록하기</Text>
                                        </View>
                                    </TouchableOpacity>
                                </View>
                            </View>
                        ):(
                            <View>
                                <View style={CreateHelpStyle.recommendWrap}>
                                    <View style={CreateHelpStyle.recommendTitlewrap}>
                                        <Text style={CreateHelpStyle.recommendTitle}>이런 카테고리는 <Text style={CreateHelpStyle.recommendPointText}>어떠세요?</Text></Text>
                                    </View>
                                    <RecommendCategoryWrap/>
                                </View>

                                <View style={CreateHelpStyle.recommendWrap}>
                                    <View style={CreateHelpStyle.recommendTitlewrap}>
                                        <Text style={CreateHelpStyle.recommendTitle}>나와 비슷한 사람들이 <Text style={CreateHelpStyle.recommendPointText}>좋아해요.</Text></Text>
                                    </View>
                                    <SimilarRecommendCategoryWrap/>
                                </View>
                            </View>
                        )
                    }
                </View>
            </CommonLayout>
        </>

    );
});

export default CreateHelp;
