import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const CategoryWrapStyle = StyleSheet.create({
    categoryItemWrap:{
        width:responsiveWidth(30),
        height:130,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
    },
    categoryItemImg:{
        width:45,
        height:45,
    },
    categoryItemText:{
        fontSize:14,
        fontWeight:"700",
        color:"#5F5F5F",
        marginTop:5,
    },
})

export default CategoryWrapStyle;