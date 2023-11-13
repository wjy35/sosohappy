import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const HistoryStyle = StyleSheet.create({
    historyWrap:{
        width:responsiveWidth(92),
        marginHorizontal:responsiveWidth(4),
        marginTop:12,
    },

    moreButton:{
        width:responsiveWidth(30),
        height:32,
        backgroundColor:"#ECEEED",
        borderRadius:8,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginTop:29,
        marginBottom:53,
        position:"relative",
        left:responsiveWidth(35) - 16,
    },
    moreButtonText:{
        fontSize:14,
        fontWeight:"700",
        color:"#767C7A",
    },
})

export default HistoryStyle;