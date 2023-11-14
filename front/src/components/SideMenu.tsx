import {View, Text, TouchableOpacity, Alert, Image} from "react-native"
import SideMenuStyle from "@/styles/SideMenuStyle";
import {useNavigation} from "@react-navigation/native";
import { sosohappyWhiteLogo, user, gear, chat, peace, home, close, menuDocs } from "@/assets/icons/icons";
import { SvgXml } from "react-native-svg";
import memberApi from "@/apis/memberApi";
import Modal from "react-native-modal";
import useStore from "@/store/store";
import { type1, type2, type3, type4 } from "@/assets/sosomon";

interface props {
    closeSide: Function;
    nowPage?: string;
    isVisible: boolean;
}

const SideMenu = ({closeSide, nowPage, isVisible}: props) => {
    const navigation = useNavigation();
    const {userInfo, logout} = useStore();

    const userLogout = async () => {
        navigation.navigate('Main');
        await logout();
    };

    const goto = (next: string) => {
        userInfo?(navigation.navigate(next)):(navigation.navigate('Login'));
    }

    const moveSettingPage = () => {
        Alert.alert('향후 업데이트 예정입니다.');
    }

    const whatIsMyThumbnail = () => {
        if(userInfo && userInfo.profileMonsterId != null){
            let tempMonsterId = userInfo.profileMonsterId;
            const animalType = Math.floor((tempMonsterId-1) / 10) + 1;
            const animalLevel = (tempMonsterId % 10 === 0)?10:(tempMonsterId%10);
            switch(animalType){
                case 1:
                    return type1[animalLevel-1];
                    break;
                case 2:
                    return type2[animalLevel-1];
                    break;
                case 3:
                    return type3[animalLevel-1];
                    break;
                case 4:
                    return type4[animalLevel-1];
                    break;
            }
        }
    }

    return(
        <>
            <Modal
                isVisible={isVisible}
                onBackdropPress={()=>closeSide()}
                style={{margin: 0, padding: 0}}
                animationIn='slideInRight'
                animationOut='slideOutRight'
            >
                <View style={SideMenuStyle.sideMenuWrap}>
                    <View style={SideMenuStyle.logoWrap}>
                        <View style={SideMenuStyle.logoBg}>
                            <SvgXml
                                xml={sosohappyWhiteLogo}
                                width={16}
                                height={16}
                            />
                            <Text style={SideMenuStyle.logoText}>소소행</Text>
                        </View>
                        <TouchableOpacity activeOpacity={0.7} onPress={() => closeSide()}>
                            <SvgXml
                                xml={close}
                                width={16}
                                height={16}
                            />
                        </TouchableOpacity>
                    </View>

                    {
                        userInfo &&
                        <View style={SideMenuStyle.profileWrap}>
                            <View style={SideMenuStyle.profileImgWrap}>
                                <Image
                                    source={whatIsMyThumbnail()}
                                    style={SideMenuStyle.profileImg}
                                />
                            </View>
                            <View style={SideMenuStyle.profileInfoWrap}>
                                <Text style={SideMenuStyle.profileName}>{userInfo.name}</Text>
                                    {
                                        userInfo.disabled === true ?
                                        <Text style={SideMenuStyle.profileRank}>나눔이</Text>
                                        :
                                        <Text style={SideMenuStyle.profileRank}>모음이</Text>
                                    }
                            </View>
                        </View>
                    }

                    <View style={SideMenuStyle.navWrap}>
                        <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("Main")}>
                            <View style={[SideMenuStyle.menuList, nowPage==="Main" && SideMenuStyle.menuListActive]}>
                                <SvgXml
                                    xml={home}
                                    width={24}
                                    height={24}
                                />
                                <Text style={[SideMenuStyle.menuItemText]}>메인</Text>
                            </View>
                        </TouchableOpacity>
                        {
                            userInfo?.memberId && (
                                <>
                                    {
                                        userInfo?.disabled && (
                                            <TouchableOpacity activeOpacity={0.7} onPress={()=>goto("CreateHelp")}>
                                                <View style={[SideMenuStyle.menuList, nowPage==="Help" && SideMenuStyle.menuListActive]}>
                                                    <SvgXml
                                                        xml={peace}
                                                        width={24}
                                                        height={24}
                                                    />
                                                    <Text style={[SideMenuStyle.menuItemText]}>도움 요청</Text>
                                                </View>
                                            </TouchableOpacity>
                                        )
                                    }
                                    <TouchableOpacity activeOpacity={0.7} onPress={()=>goto("ChatList")}>
                                        <View style={[SideMenuStyle.menuList, nowPage==="Chat" && SideMenuStyle.menuListActive]}>
                                            <SvgXml
                                                xml={chat}
                                                width={24}
                                                height={24}
                                            />
                                            <Text style={[SideMenuStyle.menuItemText]}>채팅</Text>
                                        </View>
                                    </TouchableOpacity>
                                    <TouchableOpacity activeOpacity={0.7} onPress={()=>goto("MyPage")}>
                                        <View style={[SideMenuStyle.menuList, nowPage==="MyPage" && SideMenuStyle.menuListActive]}>
                                            <SvgXml
                                                xml={user}
                                                width={24}
                                                height={24}
                                            />
                                            <Text style={[SideMenuStyle.menuItemText]}>마이페이지</Text>
                                        </View>
                                    </TouchableOpacity>
                                    {/*<TouchableOpacity activeOpacity={0.7} onPress={moveSettingPage}>*/}
                                    {/*    <View style={[SideMenuStyle.menuList]}>*/}
                                    {/*        <SvgXml*/}
                                    {/*            xml={gear}*/}
                                    {/*            width={24}*/}
                                    {/*            height={24}*/}
                                    {/*        />*/}
                                    {/*        <Text style={[SideMenuStyle.menuItemText]}>정보수정</Text>*/}
                                    {/*    </View>*/}
                                    {/*</TouchableOpacity>*/}
                                    <TouchableOpacity activeOpacity={0.7} onPress={() => goto('Certificate')}>
                                        <View style={[SideMenuStyle.menuList]}>
                                            <SvgXml
                                                xml={menuDocs}
                                                width={24}
                                                height={24}
                                            />
                                            <Text style={[SideMenuStyle.menuItemText]}>증명서 발급</Text>
                                        </View>
                                    </TouchableOpacity>
                                </>
                            )
                        }
                    </View>

                    <View style={SideMenuStyle.authButtonWrap}>
                        {
                            userInfo ? (
                                <>
                                    <TouchableOpacity activeOpacity={0.7} onPress={userLogout}>
                                        <View style={SideMenuStyle.loginButton}>
                                            <Text style={SideMenuStyle.loginButtonText}>로그아웃</Text>
                                        </View>
                                    </TouchableOpacity>
                                </>
                            ):(
                                <>
                                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("Login")}>
                                        <View style={SideMenuStyle.loginButton}>
                                            <Text style={SideMenuStyle.loginButtonText}>로그인</Text>
                                        </View>
                                    </TouchableOpacity>
                                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("SignUpSeparate")}>
                                        <Text style={SideMenuStyle.signUpButton}>아직 회원이 아니신가요?</Text>
                                    </TouchableOpacity>
                                </>
                            )
                        }
                    </View>
                </View>
            </Modal>
        </>
    );
};

export default SideMenu;
