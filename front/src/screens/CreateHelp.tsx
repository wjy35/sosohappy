import { View, Text, TextInput, TouchableOpacity, Image } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import AllCategoryWrap from "@/components/AllCategoryWrap";
import RecommendCategoryWrap from "@/components/RecommendCategoryWrap";
import SimilarRecommendCategoryWrap from "@/components/SimilarRecommendCategoryWrap";

import MicIcon from "@/assets/img/mic-icon.png"

import CreateHelpStyle from "@/styles/CreateHelpStyle"

const CreateHelp = () => {
  return (
    <CommonLayout footer={true} headerType={0} nowPage={'Help'}>
      <View style={CreateHelpStyle.createHelpTitleWrap}>
        <Text style={CreateHelpStyle.createHelpTitle}>
          반가워요!{"\n"}
          어떤 도움이 필요하신가요?
        </Text>
        <View style={CreateHelpStyle.createHelpFlexWrap}>
          <View style={CreateHelpStyle.helpWriteWrap}>
            <TextInput
              placeholder="어떤 도움이 필요하신가요?"
              style={CreateHelpStyle.helpContentInput}
            />
            <TouchableOpacity activeOpacity={0.7} style={CreateHelpStyle.micImgWrap}>
              <Image
                source={MicIcon}
                style={CreateHelpStyle.micImg}
              />
            </TouchableOpacity>
          </View>
          <TouchableOpacity activeOpacity={0.7}>
            <View style={CreateHelpStyle.submitButton}>
              <Text>등록하기</Text>
            </View>
          </TouchableOpacity>
        </View>
        <AllCategoryWrap/>
      </View>

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
    </CommonLayout>
  );
};

export default CreateHelp;
