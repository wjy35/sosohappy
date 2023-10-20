import { StyleSheet } from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const SignUpInputStyle = StyleSheet.create({
    signUpInputWrap:{
        marginHorizontal:responsiveWidth(4),
        marginTop:28,
    },
    signUpInputText:{
        fontSize:12,
        fontWeight:"500",
        color:"#B6B6B6",
        marginTop:12,
    },
    signUpInput:{
        width:responsiveWidth(92),
        height:36,
        borderWidth:1,
        borderColor:"#9D9D9D",
        marginTop:6,
        padding:10,
        fontSize:14,
        fontWeight:"900",
        color:"#535353",
    }
})

export default SignUpInputStyle;