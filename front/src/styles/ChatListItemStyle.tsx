import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const IMG_THUMBNAIL_SIZE = 55;
const PADDING_HORIZONTAL_SIZE = 24;

const ChatListItemStyle = StyleSheet.create({
    chatListItemWrap:{
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
        backgroundColor:"#FFFFFF",
        borderRadius:8,
        padding:12,
        marginTop:12,
    },
    chatListItemThumbnailImg:{
        width:55,
        height:55,
        borderRadius:50,
    },
    chatContentWrap:{
        width:responsiveWidth(92) - IMG_THUMBNAIL_SIZE - PADDING_HORIZONTAL_SIZE ,
        marginLeft:12,
    },
    chatInfoWrap:{
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
    },
    chatInfoName:{
        fontSize:20,
        fontWeight:"700",
        color:"#6A6A6C",
    },
    charInfoRank:{
        width:52,
        height:16,
        backgroundColor:"#B4F3E2",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginLeft:12,
    },
    chatInfoRankText:{
        fontSize:12,
        fontWeight:"700",
        color:"#8DCABA",
    },
    recentMessageText:{
        fontSize:12,
        fontWeight:"500",
        color:"#BDBFBE",
        marginTop:3,
    },
    msgAlarm:{
        width:22,
        height:22,
        backgroundColor:"#F25B5B",
        borderRadius:50,
        marginLeft:"auto",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginRight:12,
    },
    msgAlarmText:{
        fontSize:12,
        fontWeight:"700",
        color:"#FFFFFF",
    }
})

export default ChatListItemStyle;