import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import {SvgXml} from "react-native-svg";
import {addPlus} from "@/assets/icons/icons";

import SignUpAuthStyle from "@/styles/SignUpAuthStyle";
import {useEffect, useState} from "react";
import {useFocusEffect, useNavigation, useRoute} from "@react-navigation/native";
import {launchCamera, launchImageLibrary} from "react-native-image-picker";
import PlainInput from "@/components/PlainInput";
import useInput from "@/hooks/useInput";
import ocrApi from "@/apis/ocrApi";
import {helpSocket} from "@/types";

interface propsType{
    socket: helpSocket;
}

const SignUpAuth = ({socket}: propsType) => {
    const route = useRoute();
    const [isActive, setIsActive] = useState(false);
    const navigation = useNavigation();
    const [image, setImage] = useState<any>();

    const goNext = () => {
        navigation.navigate('SignUpInput', {selectedType: route.params.selectedType, disabled: true});
    }

    const selectImage = () => {
        launchImageLibrary(
            {
                mediaType: 'photo',
                selectionLimit: 1,
                includeBase64: true,
            },
            (res) => {
                if (res.didCancel) return;
                setImage(res);
            }
        ).catch((err) => {
            console.log(err);
        });
    };

    const takeCamera = () => {
        launchCamera(
            {
                mediaType: 'photo',
                includeBase64: true,
            },
            (res) => {
                if (res.didCancel) return;
                setImage(res);
            }
        ).catch((err) => {
            console.warn(err);
        });
    }

    const checkAuth = () => {
        if (image){
            setIsActive(true);
        } else {
            setIsActive(false);
        }
    }

    useEffect(() => {
        checkAuth();
    }, [image]);

    useFocusEffect(()=>{
        if (!socket.connected) return;
        socket.disConnect();
    })

    return (
        <CommonLayout headerType={0} footer={true}>
            <AuthTitle level="2" title="증명서를 인증해주세요. " description="사진 한 장으로 나를 증명할 수 있어요"/>
            <View>
                <TouchableOpacity activeOpacity={0.7} onPress={takeCamera}>
                    <View style={SignUpAuthStyle.uploadWrap}>
                      <SvgXml
                        xml={addPlus}
                        width={40}
                        height={40}
                      />
                      <Text style={SignUpAuthStyle.uploadText}>사진 등록하기</Text>
                    </View>
                </TouchableOpacity>
                {
                    image && (
                        <View style={SignUpAuthStyle.uploadImageWrap}>
                            <Image source={{uri:image.assets[0].uri}} style={SignUpAuthStyle.uploadImage}/>
                        </View>
                    )
                }
            </View>
            <AuthButton
                isActive={isActive}
                buttonText={'다음단계 진행하기'}
                goNext={goNext}
            />
        </CommonLayout>
  );
};

export default SignUpAuth;
