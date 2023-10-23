import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const SignUpSeparateStyle = StyleSheet.create({
    selectWrap:{
        marginHorizontal:"auto",
        display:"flex",
        flexDirection:"row",
        justifyContent:"center",
        alignItems:"center",
        marginTop:17,
    },
    selectContent:{
        width:responsiveWidth(45),
        height:240,
        borderWidth:4,
        borderColor:"#E9ECF1",
        borderRadius:4,
        display:"flex",
        flexDirection:"column",
        justifyContent:"center",
        alignItems:"center",
    },
    selectedContent:{
        borderColor:"#427CF9",
    },
    selectImg:{
        width:responsiveWidth(25),
        height:responsiveWidth(25),
    },
    selectName:{
        fontSize:16,
        fontWeight:"900",
        color:"#50545F",
        marginTop:6,
    },
    selectInfo:{
        fontSize:12,
        fontWeight:"500",
        color:"#7E7E80",
        textAlign:"center",
        marginTop:5,
    },
    authInfoText:{
        fontSize:10,
        fontWeight:"900",
        color:"#6A77AB",
        marginTop:8,
    },
})

export default SignUpSeparateStyle;