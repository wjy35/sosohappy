import {View, Text, TouchableOpacity} from "react-native"
import SideMenuStyle from "@/styles/SideMenuStyle";
import {useNavigation} from "@react-navigation/native";
import { sosohappyWhiteLogo, user, gear, chat, peace, home, close } from "@/assets/icons/icons";
import { SvgXml } from "react-native-svg";
import {observer} from "mobx-react";
import useStore from "@/hooks/useStore";
import memberApi from "@/apis/memberApi";

interface props {
    closeSide: Function;
    nowPage?: string;
}

const SideMenu = observer(({closeSide, nowPage}: props) => {
    const navigation = useNavigation();
    const {userStore} = useStore();

    const logout = async () => {
        try {
            const res = await memberApi.logout();
            if (res.status === 200){
                userStore.setUser(null);
                navigation.replace('Main');
            }
        } catch (err) {
            console.log(err);
        }
    };

    const goto = (next: string) => {
        userStore.user?(navigation.navigate(next)):(navigation.navigate('Login'));
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
                {
                    userStore.user && (
                        <View style={SideMenuStyle.nickNameWrap}>
                            <Text>{userStore.user.nickname}</Text>
                        </View>
                    )
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
                        userStore.user?.disabled === true && (
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
                    <TouchableOpacity activeOpacity={0.7}>
                        <View style={[SideMenuStyle.menuList]}>
                            <SvgXml
                                xml={gear}
                                width={24}
                                height={24}
                            />
                            <Text style={[SideMenuStyle.menuItemText]}>정보수정</Text>
                        </View>
                    </TouchableOpacity>
                </View>

                <View style={SideMenuStyle.authButtonWrap}>
                    {
                        userStore.user ? (
                            <>
                                <TouchableOpacity activeOpacity={0.7} onPress={logout}>
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
