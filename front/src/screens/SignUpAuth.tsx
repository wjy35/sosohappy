import { View, Text, Image, TouchableOpacity } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import AuthTitle from "@/components/AuthTitle";
import AuthButton from "@/components/AuthButton";

import {SvgXml} from "react-native-svg";
import {addPlus, camera, gallery} from "@/assets/icons/icons";

import SignUpAuthStyle from "@/styles/SignUpAuthStyle";
import {useEffect, useState} from "react";
import {useFocusEffect, useNavigation, useRoute} from "@react-navigation/native";
import {launchCamera, launchImageLibrary} from "react-native-image-picker";
import PlainInput from "@/components/PlainInput";
import useInput from "@/hooks/useInput";
import ocrApi from "@/apis/ocrApi";
import {ChatSocket, helpSocket} from "@/types";

interface propsType{
    socket: helpSocket;
    chatSocket: ChatSocket;
}

const SignUpAuth = ({socket, chatSocket}: propsType) => {
    const route = useRoute();
    const [isActive, setIsActive] = useState(false);
    const navigation = useNavigation();
    const [image, setImage] = useState<any>();
    const [docsNumber, setDocsNumber] = useState<string>('');

    const goNext = async () => {
        await checkDocs()
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

    const createFormData = (photo, body= {}) => {
        const data = new FormData();

        data.append("file", {
            name: photo.fileName,
            type: photo.type,
            uri: photo.uri
        });

        Object.keys(body).forEach(key => {
            data.append(key, body[key]);
        });

        return data;
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

    const sendImage = async () => {
        const data = {
            name: image.assets[0].fileName,
            type: image.assets[0].type,
            uri: image.assets[0].uri,
        }
            // data: image.assets[0].base64,

        try {
            fetch('https://sosohappy.co.kr/ocr/send',{
                method: 'post',
                body: createFormData(image.assets[0], {name: memberName.text}),
            }).then(res=>res.json())
                .then(res => {
                    setDocsNumber(res.result.documentNumber);
                    setIsActive(true);
                })
                .catch(err => console.log(err));
        } catch (err) {
            console.log(err);
        }
    }

    const checkDocs = async () => {
        try {
            const res = await ocrApi.checkAuth({
                name: memberName.text,
                documentNumber: docsNumber,
            })
            if (res.status === 200){
                // console.log(res)
                navigation.navigate('SignUpInput', {selectedType: route.params.selectedType, disabled: true, name: memberName.text});
            }
        } catch (err) {
            console.log(err)
        }
    }

    const checkAuth = () => {
        if (image){
            sendImage();
        } else {
            setIsActive(false);
        }
    }

    useEffect(() => {
        checkAuth();
        // console.log(image.assets[0])
    }, [image]);

    useFocusEffect(()=>{
        if (!socket.connected) return;
        socket.disConnect();
    })

    const checkMemberName = (newText: string) => {
        // TODO: 글자수제한 필요 5글자 넘어가면 에러남
        memberName.updateIsValid(newText !== "");
    };

    const memberName = useInput({
        placeholder: '이름을 입력해 주세요',
        title: '이름을 입력해 주세요',
        initialIsValid: false,
        onChange: checkMemberName,
    });

    return (
        <CommonLayout headerType={0} footer={true}>
            <AuthTitle level="2" title="증명서를 인증해주세요. " description="사진 한 장으로 나를 증명할 수 있어요"/>
            <View>
                <View style={SignUpAuthStyle.nameWrap}>
                    <PlainInput {...memberName}/>
                </View>
                <View style={SignUpAuthStyle.uploadWrap}>
                    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                        <TouchableOpacity activeOpacity={0.7} onPress={takeCamera}>
                            <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                              <SvgXml
                                xml={camera}
                                width={40}
                                height={40}
                              />
                              <Text style={SignUpAuthStyle.uploadText}>카메라로 사진 등록하기</Text>
                            </View>
                        </TouchableOpacity>
                    </View>
                    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                        <TouchableOpacity activeOpacity={0.7} onPress={selectImage}>
                            <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
                                <SvgXml
                                    xml={gallery}
                                    width={40}
                                    height={40}
                                />
                                <Text style={SignUpAuthStyle.uploadText}>갤러리에서 사진 등록하기</Text>
                            </View>
                        </TouchableOpacity>
                    </View>
                </View>
                {
                    image && (
                        <View style={SignUpAuthStyle.uploadImageWrap}>
                            <Image source={{uri:image.assets[0].uri}} style={[SignUpAuthStyle.uploadImage, {aspectRatio: image.assets[0].width/image.assets[0].height}]}/>
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
