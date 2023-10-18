import {StyleSheet} from "react-native"
import { responsiveWidth, responsiveHeight } from "react-native-responsive-dimensions"

const HeaderStyle = StyleSheet.create({
    headerWrap:{
        header:80,
        display:"flex",
        flexDirection:"row",
        justifyContent:"space-between",
        alignItems:"center",
        marginHorizontal:responsiveWidth(2),
    },
    preArrowImg:{
        width:32,
        height:32,
    },
    serviceName:{
        height:80,
        fontSize:22,
        fontWeight:"900",
        color:"#6283D8",
        lineHeight:80,
    },
    hamburgerMenuImg:{
        width:30,
        height:30,
    },
});

export default HeaderStyle;