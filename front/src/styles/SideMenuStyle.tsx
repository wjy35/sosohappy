import { StyleSheet } from "react-native";
import { responsiveWidth } from "react-native-responsive-dimensions";

const SideMenuStyle = StyleSheet.create({
    sideMenuWrap:{
        width:responsiveWidth(60),
        height:"100%",
        backgroundColor:"#FFFFFF",
        position:"absolute",
        paddingHorizontal:20,
        zIndex: 2
    },
    logoWrap:{
        display:"flex",
        flexDirection:"row",
        justifyContent:"space-between",
        alignItems:"center",
        marginTop:44,
    },
    logoBg:{
        width:80,
        height:25,
        backgroundColor:"#26232A",
        borderRadius:8,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
    },
    logo:{
        fontSize:16,
        fontWeight:"700",
        color:"#FFFFFF",
    },
    closeIcon:{
        width:16,
        height:16,
    },


    navWrap:{
        marginTop:32,
    },
    menuList:{
        width:"100%",
        height:36,
        display:"flex",
        justifyContent:"center",
        paddingLeft:8,
        marginTop:6,
        borderRadius:6,
    },
    menuItemText:{
        fontSize:16,
        fontWeight:"700",
        color:"#B4B4B4",
    },
    menuListActive:{
        backgroundColor:"#EEEEEE",
    },


    authButtonWrap:{
        marginTop:24,
    },
    loginButton:{
        width:"100%",
        height:36,
        backgroundColor:"#427CF9",
        borderRadius:6,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
    },
    loginButtonText:{
        fontSize:16,
        fontWeight:"500",
        color:"#FFFFFF",
    },
    signUpButton:{
        fontSize:12,
        fontWeight:"500",
        color:"#A4A4A4",
        textAlign:"center",
        marginTop:8,
    },
})

export default SideMenuStyle;
