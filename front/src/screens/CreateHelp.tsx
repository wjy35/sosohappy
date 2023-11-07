import CommonLayout from "@/components/CommonLayout";
import CreateHelpSelectCategory from "@/components/CreateHelpSelectCategory";
import {Text, TouchableOpacity, View} from "react-native";
import CreateHelpStyle from "@/styles/CreateHelpStyle";
import AllCategoryWrap from "@/components/AllCategoryWrap";
import PlainInput from "@/components/PlainInput";
import CategoryWrapStyle from "@/styles/CategoryWrapStyle";
import {SvgXml} from "react-native-svg";
import RecommendCategoryWrap from "@/components/RecommendCategoryWrap";
import SimilarRecommendCategoryWrap from "@/components/SimilarRecommendCategoryWrap";
import {useEffect, useState} from "react";
import useStore from "@/hooks/useStore";
import useInput from "@/hooks/useInput";
import {observer} from "mobx-react";
import {useNavigation} from "@react-navigation/native";

interface propsType{
    location: {
        latitude: number,
        longitude: number,
    };
    socket: {
        connect: Function;
        send: Function;
        status: String;
        helpList: helpDetail[];
        connected: boolean;
        disConnect: Function;
    },
}

interface helpDetail {
    memberId: number,
    nickname: string,
    category: {
        categoryId: number,
        categoryName: string,
        categoryImage: string,
    },
    longitude: number,
    latitude: number,
    content: string,
    place: string,
}

const CreateHelp = observer(({location, socket}: propsType) => {
    const [category, setCategory] = useState<any>(null);
    const {userStore} = useStore();
    const navigation = useNavigation();

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
        const payload = {
            memberId: userStore.user.memberId,
            nickname: userStore.user.nickname,
            category: {
                categoryId: category.categoryId,
                categoryName: category.categoryName,
                categoryImage: category.categoryImage,
            },
            longitude: 1,
            latitude: 1,
            content: helpContent.text,
            place: locationDetail.text,
        };
        socket.send(payload);
    }

    useEffect(() => {
        const focusNav = navigation.addListener('focus', () => {
            // do something
            !socket.connected&&socket.connect()
        });
        return focusNav;
    }, [navigation]);

    return (
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
                                    <View style={CreateHelpStyle.submitButtonWrap}>
                                        <Text style={[CreateHelpStyle.submitButtonText, (helpContent.isValid&&locationDetail.isValid)&&CreateHelpStyle.submitButtonActive]}>등록하기</Text>
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
    );
});

export default CreateHelp;
