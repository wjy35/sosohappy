import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const AuthButtonStyle = StyleSheet.create({
    authInfoWrap:{
        marginHorizontal:responsiveWidth(4),
    },
    authInfoTitle:{
        fontSize:14,
        fontWeight:"900",
        color:"#000000",
        marginTop:25,
    },
    authInfoDescription:{
        fontSize:12,
        fontWeight:"400",
        color:"#9D9EA0",
        marginTop:8,
        lineHeight:18,
    },
    authButtonWrap:{
        marginHorizontal:responsiveWidth(4),
        marginTop:23,
        marginBottom:42,
    },

    nextButton:{
        width:"100%",
        height:50,
        backgroundColor:"#427CF9",
        display:"flex",
        justifyContent:"center",
        paddingLeft:22,
    },
    nextButtonText:{
        fontSize:14,
        fontWeight:"500",
        color:"#FFFFFF",
    },
    moveMainButton:{
        width:"100%",
        height:50,
        backgroundColor:"#F5F5F5",
        display:"flex",
        justifyContent:"center",
        paddingLeft:22,
        marginTop:8,
    },
    moveMainButtonText:{
        fontSize:14,
        fontWeight:"500",
        color:"#5B5B5B",
    },
})

export default AuthButtonStyle;