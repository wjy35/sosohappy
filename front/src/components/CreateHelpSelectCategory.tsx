import {Image, Text, TextInput, TouchableOpacity, View} from "react-native";
import CreateHelpStyle from "@/styles/CreateHelpStyle";
import MicIcon from "@/assets/img/mic-icon.png";
import AllCategoryWrap from "@/components/AllCategoryWrap";
import RecommendCategoryWrap from "@/components/RecommendCategoryWrap";
import SimilarRecommendCategoryWrap from "@/components/SimilarRecommendCategoryWrap";
import {useState} from "react";
import useInput from "@/hooks/useInput";
import PlainInput from "@/components/PlainInput";
import AuthButtonStyle from "@/styles/AuthButtonStyle";
import CategoryWrapStyle from "@/styles/CategoryWrapStyle";
import {SvgXml} from "react-native-svg";

const CreateHelpSelectCategory = () => {
    const [category, setCategory] = useState<any>(null);

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

    return (
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
                    <View style={CreateHelpStyle.helpDetailWrap}>
                        <View style={{marginBottom: 20}}>
                            <View style={{padding: 10}}>
                                <Text style={{fontSize: 16, fontWeight: 'bold'}}>도움 내용을 입력해 주세요</Text>
                            </View>
                            <PlainInput {...helpContent}/>
                        </View>
                        <View style={{marginBottom: 20}}>
                            <View style={{padding: 10}}>
                                <Text style={{fontSize: 16, fontWeight: 'bold'}}>상세 위치를 입력해 주세요</Text>
                            </View>
                            <PlainInput {...locationDetail}/>
                        </View>
                        <View style={{width: '100%', padding: 10}}>
                            <View style={{padding: 10}}>
                                <Text style={{fontSize: 16, fontWeight: 'bold'}}>카테고리</Text>
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
                            <TouchableOpacity activeOpacity={0.7} onPress={()=>{}}>
                                <View style={[AuthButtonStyle.nextButton, AuthButtonStyle.nextButtonActive]}>
                                    <Text style={AuthButtonStyle.nextButtonText}>등록하기</Text>
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
    )
}

export default CreateHelpSelectCategory;
