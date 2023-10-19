import {View, Text, Image, TouchableOpacity} from "react-native"
import PreArrow from "@/assets/img/pre-arrow.png"
import HamburgerMenu from "@/assets/img/hamburger-menu.png"

import "@/styles/HeaderStyle";
import HeaderStyle from "@/styles/HeaderStyle";

const Header = () => {
    return(
        <>
            <View style={HeaderStyle.headerWrap}>
                <TouchableOpacity activeOpacity={0.7}>
                    <Image
                        source={PreArrow}
                        style={HeaderStyle.preArrowImg}
                    />
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7}>
                    <Text style={HeaderStyle.serviceName}>LOGO</Text>
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7}>
                    <Image
                        source={HamburgerMenu}
                        style={HeaderStyle.hamburgerMenuImg}
                    />
                </TouchableOpacity>
            </View>
        </>
    );
}

export default Header;