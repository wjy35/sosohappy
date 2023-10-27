import { StyleSheet } from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const ChatListStyle = StyleSheet.create({
    chatListBg:{
        backgroundColor:"#F5ECF1",
    },
    chatListHeaderWrap:{
        height:80,
        marginHorizontal:responsiveWidth(2),
        display:"flex",
        flexDirection:"row",
        justifyContent:"space-between",
        alignItems:"center",
    },
    preArrowIcon:{
        width:32,
        height:32,
    },
    headerTitle:{
        fontSize:20,
        fontWeight:"700",
        color:"#262221",
    },
    hamburgerIcon:{
        width:30,
        height:30,
    },

    chatListSearchWrap:{
        marginHorizontal:responsiveWidth(4),
        position:"relative",
    },
    chatListSearchIcon:{
        width:22,
        height:22,
        position:"absolute",
        top:15,
        left:18,
    },
    chatListSearchInput:{
        height:52,
        backgroundColor:"#F8F4F1",
        borderRadius:10,
        padding:10,
        paddingLeft:60,
        fontSize:16,
        fontWeight:"500",
        color:"#C1BDBC",
    },

    chatListNav:{
        marginHorizontal:responsiveWidth(4),
        display:"flex",
        flexDirection:"row",
        justifyContent:"space-evenly",
        alignItems:"center",
        paddingVertical:24,
    },
    chatListNavItem:{
        fontSize:16,
        fontWeight:"300",
        color:"#A59AB8",
    },
    chatListNavActiveItem:{
        fontSize:16,
        fontWeight:"700",
        color:"#9389A5",
    },

    chatListItemWrap:{
        marginHorizontal:responsiveWidth(4),
        paddingBottom:48,
    },
})

export default ChatListStyle;