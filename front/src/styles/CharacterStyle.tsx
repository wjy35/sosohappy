import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const CharacterStyle = StyleSheet.create({
    characterTitleWrap:{
        marginHorizontal:responsiveWidth(4),
        marginTop:33,
    },
    characterTitle:{
        fontSize:26,
        fontWeight:"500",
        color:"#363B41",
    },
    characterTitleMyName:{
        fontSize:26,
        fontWeight:"700",
        color:"#363B41",
    },
    feedButton:{
        width:200,
        height:34,
        backgroundColor:"#E8F3FE",
        borderRadius:6,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginTop:14,
    },
    feedButtonText:{
        fontSize:16,
        fontWeight:"700",
        color:"#5F72BF",
    },
    feedAnimalWrap:{
        marginTop:14,
    },
    feedAnimalImg:{
        width:responsiveWidth(70),
        height:responsiveWidth(70),
        marginHorizontal:responsiveWidth(2),
    },
    feedAnimalActive: {
        borderWidth: 1,
        borderColor: 'blue',
        borderRadius: 20,
    },
    selectedCharaterWrap:{
        marginHorizontal:responsiveWidth(4),
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
    },
    selectedCharacterImg:{
        width:100,
        height:100,
    },
    selectedCharacterInfo:{
        marginLeft:4,
    },
    selectedCharacterInfoTitle:{
        fontSize:14,
        fontWeight:"500",
        color:"#656A6E",
    },
    selectedCharacterInfoLevel:{
        fontSize:18,
        fontWeight:"700",
        color:"#32323C",
    },
    expWrap:{
        marginHorizontal:responsiveWidth(4),
    },
    expTitle:{
        textAlign:"right",
        marginBottom:4,
        fontSize:14,
        fontWeight:"700",
        color:"#656A6E",
    },
    expStatusWrap:{
        position:"relative",
    },
    expStatusBg:{
        width:"100%",
        height:28,
        borderRadius:10,
        backgroundColor:"#E4E8EB",
    },
    expStatusMy:{
        height:28,
        borderRadius:10,
        backgroundColor:"#8B8BEF",
        position:"absolute",
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

    animationButtonWrap:{
        marginHorizontal:responsiveWidth(4),
        display:"flex",
        flexDirection:"row",
        marginVertical:24,
    },
    animationButton:{
        width:responsiveWidth(45),
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        backgroundColor:"#E9F3FD",
        borderRadius:10,
        marginHorizontal:responsiveWidth(1),
        padding:responsiveWidth(8),
    },
    animationButtonThumbnail:{
        width:responsiveWidth(26),
        height:responsiveWidth(26),
    },
    animationButtonText:{
        marginTop:6,
    },
})

export default CharacterStyle;
