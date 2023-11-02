import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const SosomonCardStyle = StyleSheet.create({
    cardWrap:{
        backgroundColor:"#FFF",
        borderRadius:15,
        marginHorizontal:responsiveWidth(1),
        marginTop:19,
    },
    characterRoomWrap:{
        width:responsiveWidth(40),
        height:responsiveWidth(30),
        position:"relative",
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
    },
    characterRoom:{
        width:"100%",
        height:"100%",
    },
    lockIcon:{
        width:46,
        height:46,
        position:"absolute",
    },
    cardInfoFlexWrap:{
        padding:20,
    },
    cardInfoWrap:{

    },
    characterName:{
        fontSize:14,
        fontWeight:"700",
        color:"#020203",
    },
    characterTypeLevel:{
        fontSize:12,
        fontWeight:"400",
        color:"#898D90",
    },
    inActiveButton:{
        padding:4,
        backgroundColor:"#F1F1F1",
        marginTop:8,
        borderRadius:8,
    },
    activeButton:{
        padding:4,
        backgroundColor:"#9D9AF9",
        marginTop:8,
        borderRadius:8,
    },
    inActiveButtonText:{
        textAlign:"center",
        fontSize:12,
        fontWeight:"500",
        color:"#B2B2B2",
    },
    activeButtonText:{
        textAlign:"center",
        fontSize:12,
        fontWeight:"500",
        color:"#FFFFFF",
    },
    lock: {
        tintColor: '#000000',
    }
})

export default SosomonCardStyle;
