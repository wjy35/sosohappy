import {View, Text, Image, TouchableOpacity} from "react-native"

import {SvgXml} from "react-native-svg";
import {backIcon, hamburgerMenu} from "@/assets/icons/icons";

import "@/styles/HeaderStyle";
import HeaderStyle from "@/styles/HeaderStyle";
import {useNavigation} from "@react-navigation/native";
import SideMenu from "@/components/SideMenu";
import {useState} from "react";

interface props {
    headerType: number;
    openSide: Function;
}

const Header = ({headerType, openSide}: props) => {

    const navigation = useNavigation();
    const goBack = () => {
        navigation.goBack();
    };

    const goMain = () => {
        navigation.navigate("Main");
    }

    return(
        <>
            <View style={HeaderStyle.headerWrap}>
                <TouchableOpacity activeOpacity={0.7} onPress={goBack}>
                    <SvgXml
                      xml={backIcon}
                      width={32}
                      height={32}
                    />
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7} onPress={goMain}>
                    <Text style={HeaderStyle.serviceName}>LOGO</Text>
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.7} onPress={()=>openSide()}>
                    <SvgXml
                      xml={hamburgerMenu}
                      width={30}
                      height={30}
                    />
                </TouchableOpacity>
            </View>
        </>
    );
}

export default Header;
