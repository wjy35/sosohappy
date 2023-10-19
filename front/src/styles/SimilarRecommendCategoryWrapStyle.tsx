import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const LikeRecommendWrapStyle = StyleSheet.create({
    likeRecommendContentWrap:{
        width:responsiveWidth(80),
        height:80,
        backgroundColor:"#EEF1F8",
        borderRadius:8,
        display:"flex",
        flexDirection:"row",
        justifyContent:"center",
        alignItems:"center",
        padding:10,
        marginTop:18,
        marginHorizontal:responsiveWidth(2),
    },
    likeRecommendContentMainTitle:{
        fontSize:18,
        fontWeight:"700",
        color:"#63678C",
    },
    likeRecommendContentSubTitle:{
        fontSize:12,
        fontWeight:"500",
        color:"#ACB0B3",
        marginTop:3,
    },
    likeRecommendIcon:{
        width:54,
        height:54,
        marginLeft:30,
    },
})

export default LikeRecommendWrapStyle;