import {StyleSheet} from "react-native";
import { responsiveWidth } from "react-native-responsive-dimensions";

const MyPageStyle = StyleSheet.create({
    myProfileWrap:{
        width:responsiveWidth(92),
        marginHorizontal: responsiveWidth(4),
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
        marginTop:32,
    },
    myProfileImg:{
        width:48,
        height:48,
        borderRadius:50,
    },
    myProfileInfo:{
        marginLeft:17,
    },
    myName:{
        fontSize:18,
        fontWeight:"700",
        color:"#434B4D",
    },
    myRank:{
        fontSize:12,
        fontWeight:"500",
        color:"#7D8987",
    },
    myProfileIconWrap:{
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
        marginLeft:"auto",
    },
    myProfileGearIcon:{
        width:24,
        height:24,
    },
    myProfileBellIcon:{
        width:24,
        height:24,
        marginLeft:13,
    },



    myPointInfo:{
        width:responsiveWidth(92),
        marginHorizontal:responsiveWidth(4),
        marginTop:25,
    },
    myPoint:{
        fontSize:26,
        fontWeight:"700",
        color:"#97989D",
        textAlign:"center",
    },
    myAllPoint:{
        fontSize:24,
        fontWeight:"700",
        color:"#404C4C",
    },
    myPointDesc:{
        fontSize:14,
        fontWeight:"400",
        color:"#A0ABAF",
        textAlign:"center",
    },
    myPointDescPoint:{
        fontSize:14,
        fontWeight:"500",
        color:"#DFACB1"
    },


    ThumbnailCharacterWrap:{
        width:responsiveWidth(92),
        marginHorizontal:responsiveWidth(4),
        marginTop:26,
        position:"relative",
        backgrsoundColor:"#6ED4E0",
    },
    MySelectedCharImg:{
        width:responsiveWidth(92),
        height:responsiveWidth(80),
        borderRadius:12,
    },
    bookIconWrap:{
        position:"absolute",
        top:10,
        left:10,
    },
    bookIcon:{
        width:24,
        height:24,
        
    },



    expWrap:{
        width:responsiveWidth(92),
        marginHorizontal:responsiveWidth(4),
        backgroundColor:"#E6E6E6",
        borderRadius:10,
        marginTop:11,
        display:"flex",
    },
    expTitleWrap:{
        padding:20,
        paddingBottom:0,
        display:"flex",
        flexDirection:"row",
        justifyContent:"space-between",
    },
    expTitle:{

    },
    expMainTitle:{
        fontSize:16,
        fontWeight:"700",
        color:"#62676A",
    },
    expSubTitle:{
        fontSize:9,
        fontWeight:"400",
        color:"#AFB4B7",
        marginTop:2,
    },
    grayMoreIcon:{
        width:16,
        height:16,
    },

    statusWrap:{
        padding:20,
        paddingTop:0,
        position:"relative",
        top:-10,
    },
    statusPercent:{
        textAlign:"right",
    },
    expBarWrap:{
        position:"relative",
        marginTop:6,
    },
    expBarBg:{
        width:"100%",
        height:16,
        backgroundColor:"#D4D9D5",
        borderRadius:50,
    },
    expBarMy:{
        width:"40%",
        height:16,
        backgroundColor:"#306E69",
        borderRadius:50,
        position:"absolute",
    },
    expInfo:{
        fontSize:10,
        fontWeight:"500",
        color:"#8D9594",
        marginTop:12,
    },
})

export default MyPageStyle;