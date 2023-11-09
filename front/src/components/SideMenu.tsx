import {View, Text, TouchableOpacity, Alert} from "react-native"
import SideMenuStyle from "@/styles/SideMenuStyle";
import {useNavigation} from "@react-navigation/native";
import { sosohappyWhiteLogo, user, gear, chat, peace, home, close, menuDocs } from "@/assets/icons/icons";
import { SvgXml } from "react-native-svg";
import useStore from "@/store/store";

interface props {
    closeSide: Function;
    nowPage?: string;
}

const SideMenu = (({closeSide, nowPage}: props) => {
    const navigation = useNavigation();
    const {userInfo, logout} = useStore();

    const userLogout = async () => {
        logout();
        Alert.alert("로그아웃 되었습니다.");
    };

    const goto = (next: string) => {
        userInfo?(navigation.navigate(next)):(navigation.navigate('Login'));
    }

    const moveSettingPage = () => {
        Alert.alert('향후 업데이트 예정입니다.');
    }

    return(
        <>
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
                {/* {
                    userStore.user && (
                        <View style={SideMenuStyle.nickNameWrap}>
                            <Text>{userStore.user.nickname}</Text>
                        </View>
                    )
                } */}

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
                        userInfo?.disabled === true && (
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
                    <TouchableOpacity activeOpacity={0.7} onPress={moveSettingPage}>
                        <View style={[SideMenuStyle.menuList]}>
                            <SvgXml
                                xml={gear}
                                width={24}
                                height={24}
                            />
                            <Text style={[SideMenuStyle.menuItemText]}>정보수정</Text>
                        </View>
                    </TouchableOpacity>
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
        </>
    );
});

export default SideMenu;
