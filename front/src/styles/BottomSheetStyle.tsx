import { StyleSheet } from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const BottomSheetStyle = StyleSheet.create({
    modal:{
        justifyContent: 'flex-end',
        margin: 0,
    },
    modalContent:{
        backgroundColor: 'white',
        padding: 16,
        borderTopLeftRadius:16,
        borderTopRightRadius:16,
    },
    styleLine:{
        width:76,
        height:4,
        backgroundColor:"#EEEEF0",
        borderRadius:6,
        position:"relative",
        left:"50%",
        marginLeft:-36,
    },
    modalTitleWrap:{
        marginTop:24,
    },
    modalTitleCategory:{
        width:100,
        height:24,
        backgroundColor:"#E3F6FF",
        borderRadius:50,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
    },
    modalTitleCategoryText:{
        fontSize:12,
        fontWeight:"700",
        color:"#8CA8DA",
    },
    modalTitleContent:{
        marginTop:7,
        fontSize:30,
        fontWeight:"700",
        color:"#3D4144",
    },
    modalTitleDescription:{
        fontSize:14,
        fontWeight:"700",
        color:"#B4BDC4",
        marginTop:4,
    },

    userInfoWrap:{
        marginTop:32,
        display:"flex",
        flexDirection:"row",
    },
    userInfoItemWrap:{
        width:responsiveWidth(45),
        marginHorizontal:responsiveWidth(2),
    },
    userTitle:{
        fontSize:12,
        fontWeight:"500",
        color:"#8D9192",
    },
    userInfoContentWrap:{
        marginTop:10,
        backgroundColor:"#EEF3F7",
        borderRadius:70,
    },
    userInfoContentFlexWrap:{
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
    },
    userThumbnail:{
        width:32,
        height:32,
        borderRadius:50,
    },
    userInfoName:{
        marginLeft:16,
        fontSize:16,
        fontWeight:"600",
        color:"#6D7275",
    },
    userCategoryContentWrap:{
        marginTop:10,
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
    },
    userCategory:{
        height:32,
        padding:6,
        paddingHorizontal:14,
        backgroundColor:"#EEF3F7",
        borderRadius:70,
        marginHorizontal:responsiveWidth(1),
    },
    userCategoryText:{
        fontSize:14,
        fontWeight:"700",
        color:"#6E777C",
    },

    matchingWrap:{
        marginTop:30,
        display:"flex",
        flexDirection:"row",
        alignItems:"center",
    },
    styleCircle:{
        width:4,
        height:4,
        borderRadius:70,
        backgroundColor:"#6DA1C7",
    },
    matchingText:{
        marginLeft:14,
        fontSize:14,
        fontWeight:"500",
        color:"#ACADBF",
    },

    sirenWrap:{
        marginLeft:6,
        paddingHorizontal:14,
        paddingVertical:6,
        backgroundColor:"#E3F6FF",
        borderRadius:50,
    },
    sirenButton:{

    },
    sirenText:{
        fontSize:12,
        fontWeight:"700",
        color:"#8CA8DA",
    },

    connectButton:{
        width:"100%",
        // flex: 1,
        height:42,
        borderRadius:10,
        backgroundColor:"#9D9AF9",
        justifyContent:"center",
        alignItems:"center",
        marginTop:26,
        marginBottom:20,
        marginHorizontal: 5,
    },
    connectButtonText:{
        fontSize:18,
        fontWeight:"700",
        color:"#FFFFFF",
    },
    cancelButton:{
        width:"100%",
        // flex: 1,
        height:42,
        borderRadius:10,
        backgroundColor:"#ff3333",
        justifyContent:"center",
        alignItems:"center",
        marginTop:26,
        marginBottom:20,
        marginHorizontal: 5,
    },
})

export default BottomSheetStyle;
