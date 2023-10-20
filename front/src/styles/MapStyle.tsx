import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const MapStyle = StyleSheet.create({
    createHelpWrap:{
        width:responsiveWidth(92),
        height:70,
        marginHorizontal:responsiveWidth(4),
        display:"flex",
        flexDirection:"row",
        justifyContent:"flex-start",
        alignItems:"center",
        backgroundColor:"#FFF",
    },
    megaphoneIcon:{
        width:36,
        height:36,
    },
    createHelpInfo:{
        marginLeft:7,
    },
    helpSubTitle:{
        fontSize:10,
        fontWeight:"400",
        color:"#909092",
    },
    helpMainTitle:{
        fontSize:14,
        fontWeight:"700",
        color:"#3C3C3E",
    },
    helpButton:{
        marginLeft:"auto",
        marginRight:20,
        fontSize:12,
        fontWeight:"700",
        color:"#000000",
    },
})

export default MapStyle;