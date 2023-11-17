import {StyleSheet} from "react-native"
import { responsiveWidth, responsiveHeight } from "react-native-responsive-dimensions";

const ChatStyle = StyleSheet.create({
    heightWrap:{
        // height:responsiveHeight(100) - 80,
        flex: 1
    },
    chatTitleWrap:{
        paddingHorizontal:responsiveWidth(4),
        height:responsiveHeight(10),
        display:"flex",
        flexDirection:"row",
        justifyContent:"flex-start",
        alignItems:"center",
        backgroundColor:"#FFF",
        zIndex:999,
    },
    chatTitle:{
        marginRight:"auto",
    },
    chatTitleName:{
        fontSize:20,
        fontWeight:"700",
        color:"#7E7D82",
    },
    chatTitleRank:{
        fontSize:14,
        fontWeight:"400",
        color:"#C3C0CB",
    },

    chatContentWrap:{
        // height:responsiveHeight(56),
        flex: 1,
        paddingTop:"auto",
        marginTop:"auto",
        marginHorizontal:responsiveWidth(4),
        paddingVertical: 10,
    },


    agreeWrap:{
        marginHorizontal:responsiveWidth(4),
        height:responsiveHeight(20),
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginTop:"auto",
    },
    agreeInfoText:{
        marginTop:12,
    },
    agreeButton:{
        width:70,
        height:22,
        backgroundColor:"#B6B2C0",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginTop:12,
        borderRadius:6,
    },
    agreeButtonText:{
        fontSize:10,
        fontWeight:"700",
        color:"#FFF",
    },

    controlWrap:{
        height:responsiveHeight(10),
        display:"flex",
        flexDirection:"row",
        justifyContent:"center",
        alignItems:"center",
        backgroundColor:"#FFF",
    },
    exitFlexWrap:{
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
    },
    exitText:{
        fontSize:10,
        fontWeight:"700",
        color:"#9085D3",
        marginLeft:6,
    },
    chanMsgInput:{
        width:responsiveWidth(60),
        height:30,
        backgroundColor:"#EFEDFA",
        padding:4,
        marginLeft:10,
    },
    sendButton:{
        width:responsiveWidth(10),
        height:30,
        backgroundColor:"#9C8DE8",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        borderRadius:4,
    },
    sendIcon:{
        width:14,
        height:14,
    },
});

export default ChatStyle;
