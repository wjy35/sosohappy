import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const HistoryItemStyle = StyleSheet.create({
    historyItemWrap:{
        width:responsiveWidth(92),
        backgroundColor:"#ECEEED",
        borderRadius:8,
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
        padding:6,
        paddingHorizontal:10,
    },

    historyItemProfileBg:{
        width:58,
        height:58,
        backgroundColor:"#D9D9D9",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        borderRadius:8,
    },
    historyItemProfileImg:{
        borderRadius:50,
    },
    historyItemInfo:{
        marginLeft:13,
    },
    historyItemContent:{
        width:responsiveWidth(60),
        fontSize:14,
        fontWeight:"700",
        color:"#767C7A",
    },
    historyItemDate:{
        fontSize:12,
        fontWeight:"400",
        color:"#A1A2A4",
        marginTop:1,
    },
    historyItemStateWrap:{
        marginLeft:"auto",
    },
})

export default HistoryItemStyle;
