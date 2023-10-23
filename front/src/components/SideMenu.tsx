import {View, Text, Image, TouchableOpacity} from "react-native"
import CloseIcon from "@/assets/img/close-icon.png"
import SideMenuStyle from "@/styles/SideMenuStyle";
import {useNavigation} from "@react-navigation/native";

interface props {
    closeSide: Function;
    nowPage?: string;
}

const SideMenu = ({closeSide, nowPage}: props) => {
    const navigation = useNavigation();

    return(
        <>
            <View style={SideMenuStyle.sideMenuWrap}>
                <View style={SideMenuStyle.logoWrap}>
                    <View style={SideMenuStyle.logoBg}>
                        <Text style={SideMenuStyle.logo}>LOGO</Text>
                    </View>
                    <TouchableOpacity activeOpacity={0.7} onPress={() => closeSide()}>
                        <Image
                            source={CloseIcon}
                            style={SideMenuStyle.closeIcon}
                        />
                    </TouchableOpacity>
                </View>

                <View style={SideMenuStyle.navWrap}>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("Main")}>
                        <View style={[SideMenuStyle.menuList, nowPage==="Main" && SideMenuStyle.menuListActive]}>
                            <Text style={[SideMenuStyle.menuItemText]}>메인</Text>
                        </View>
                    </TouchableOpacity>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("CreateHelp")}>
                        <View style={[SideMenuStyle.menuList, nowPage==="Help" && SideMenuStyle.menuListActive]}>
                            <Text style={[SideMenuStyle.menuItemText]}>도움 요청</Text>
                        </View>
                    </TouchableOpacity>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("ChatList")}>
                        <View style={[SideMenuStyle.menuList, nowPage==="Chat" && SideMenuStyle.menuListActive]}>
                            <Text style={[SideMenuStyle.menuItemText]}>채팅</Text>
                        </View>
                    </TouchableOpacity>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("MyPage")}>
                        <View style={[SideMenuStyle.menuList, nowPage==="MyPage" && SideMenuStyle.menuListActive]}>
                            <Text style={[SideMenuStyle.menuItemText]}>마이페이지</Text>
                        </View>
                    </TouchableOpacity>
                    <TouchableOpacity activeOpacity={0.7}>
                        <View style={[SideMenuStyle.menuList]}>
                            <Text style={[SideMenuStyle.menuItemText]}>정보수정</Text>
                        </View>
                    </TouchableOpacity>
                </View>

                <View style={SideMenuStyle.authButtonWrap}>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("Login")}>
                        <View style={SideMenuStyle.loginButton}>
                            <Text style={SideMenuStyle.loginButtonText}>로그인</Text>
                        </View>
                    </TouchableOpacity>
                    <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate("SignUpSeparate")}>
                        <Text style={SideMenuStyle.signUpButton}>아직 회원이 아니신가요?</Text>
                    </TouchableOpacity>
                </View>
            </View>
        </>
    );
}

export default SideMenu;
