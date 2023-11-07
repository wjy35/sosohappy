import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const MapLoadingStyle = StyleSheet.create({
    modalBack:{
        width:"100%",
        height:"100%",
        backgroundColor:"#000",
        opacity:0.8,
        position:"absolute",
        zIndex: 10,
    },
    modalMain:{
        width:responsiveWidth(80),
        height:"100%",
        position:"absolute",
        left: responsiveWidth(20)/2,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        zIndex: 11,
    },
    turtleLoading:{
        width:280,
        height:160,
    },
    loadingText:{
        textAlign:"center",
        fontSize:16,
        fontWeight:"500",
        color:"#FFFFFF",
    },
    loadingTextBold:{
        fontWeight:"700",
    },
})

export default MapLoadingStyle;
