import { StyleSheet } from "react-native";
import { responsiveWidth } from "react-native-responsive-dimensions";

const SignUpAuthStyle = StyleSheet.create({
    inputNameWrap:{
        marginTop:8,
    },
    uploadWrap:{
        width:responsiveWidth(92),
        height:132,
        borderWidth:2,
        borderColor:"#E5E5E5",
        marginHorizontal:responsiveWidth(4),
        display:"flex",
        justifyContent:"space-around",
        alignItems:"center",
        marginTop:20,
        flexDirection: 'row'
    },
    uploadImageWrap:{
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
    },
    uploadImage: {
        width: '100%',
        resizeMode: 'cover',
    },
    nameWrap: {
        marginHorizontal:responsiveWidth(4),
    },
    rotateAlertWrap:{
        display:"flex",
        flexDirection:"row",
        justifyContent:"center",
        alignItems:"center",
    },
    rotateAlertImg:{
        width:80,
        height:80,
    },
    rotateAlertText:{

    },
})

export default SignUpAuthStyle;
