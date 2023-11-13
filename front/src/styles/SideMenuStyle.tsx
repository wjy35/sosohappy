import { StyleSheet } from "react-native";
import { responsiveWidth } from "react-native-responsive-dimensions";

const SideMenuStyle = StyleSheet.create({
    sideMenuWrap:{
        width:responsiveWidth(60),
        height:"100%",
        backgroundColor:"#FFFFFF",
        position:"absolute",
        right: 0,
        paddingHorizontal:20,
        zIndex: 2
    },
    logoWrap:{
        display:"flex",
        flexDirection:"row",
        justifyContent:"space-between",
        alignItems:"center",
        marginTop:24,
    },
    logoBg:{
        width:86,
        height:30,
        backgroundColor:"#26232A",
        borderRadius:8,
        display:"flex",
        flexDirection:"row",
        justifyContent:"center",
        alignItems:"center",
    },
    logoText:{
        fontSize:16,
        fontWeight:"700",
        color:"#FFFFFF",
        marginLeft:4,
    },
    closeIcon:{
        width:16,
        height:16,
    },

    profileWrap:{
        display:"flex",
        flexDirection:"row",
        justifyContent:"flex-start",
        alignItems:"center",
        marginTop:16,
    },
    profileImgWrap:{

    },
    profileImg:{
        width:80,
        height:80,
        borderRadius:160,
    },
    profileInfoWrap:{

    },
    profileName:{
        fontSize:16,
        fontWeight:"700",
        color:"#26232A"
    },
    profileRank:{
        fontSize:13,
        fontWeight:"500",
        color:"#B4B4B4",
        marginLeft:2,
        marginTop:1,
    },


    navWrap:{
        marginTop:16,
    },
    menuList:{
        width:"100%",
        height:36,
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
        paddingLeft:8,
        marginTop:6,
        borderRadius:6,
    },
    menuItemText:{
        fontSize:16,
        fontWeight:"700",
        color:"#B4B4B4",
        marginLeft:15,
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
    nickNameWrap: {
        paddingTop: 20,

    }
})

export default SideMenuStyle;
