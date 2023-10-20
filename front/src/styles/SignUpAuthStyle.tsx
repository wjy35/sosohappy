import { StyleSheet } from "react-native";
import { responsiveWidth } from "react-native-responsive-dimensions";

const SignUpAuthStyle = StyleSheet.create({
    uploadWrap:{
        width:responsiveWidth(92),
        height:132,
        borderWidth:2,
        borderColor:"#E5E5E5",
        marginHorizontal:responsiveWidth(4),
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginTop:27,
    },
    addPlusImg:{
        width:34,
        height:34,
    },
    uploadText:{
        fontSize:10,
        fontWeight:"500",
        color:"#97989A",
        marginTop:6,
    }
})

export default SignUpAuthStyle;