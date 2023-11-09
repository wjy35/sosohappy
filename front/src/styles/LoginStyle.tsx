import { StyleSheet } from "react-native";
import { responsiveWidth } from "react-native-responsive-dimensions";

const LoginStyle = StyleSheet.create({
    loginTitleWrap:{
        marginHorizontal:responsiveWidth(4),
        marginTop:24,
    },
    loginCategory:{
        fontSize:26,
        fontWeight:"700",
        color:"#3B3D9A",
    },
    loginTitle:{
        fontSize:26,
        fontWeight:"700",
        color:"#32323C",
        marginTop:2,
    },
    loginDescription:{
        fontSize:16,
        fontWeight:"400",
        color:"#646464",
        marginTop:6,
    },

    loginContentWrap:{
        marginHorizontal:responsiveWidth(4),
        marginTop:48,
        marginBottom:39,
    },
    loginInput:{
        width:responsiveWidth(92),
        height:48,
        borderWidth:1,
        borderColor:"#D1D1D1",
        borderRadius:15,
        padding:10,
        fontSize:14,
        fontWeight:"500",
        color:"#646464",
    },
    passwordInput:{
        width:responsiveWidth(92),
        height:48,
        borderWidth:1,
        borderColor:"#D1D1D1",
        borderRadius:15,
        marginTop:12,
        padding:10,
        fontSize:14,
        fontWeight:"500",
        color:"#646464",
    },
    loginButton:{
        width:responsiveWidth(92),
        height:50,
        backgroundColor:"#B6B2C0",
        borderRadius:15,
        display:"flex",
        justifyContent:"center",
        alignItems:"center",
        marginTop:12,
    },
    loginButtonActive:{
        backgroundColor:"#427CF9",
    },
    loginButtonText:{
        fontSize:16,
        fontWeight:"500",
        color:"#FFFFFF",
    },
    authText:{
        fontSize:14,
        fontWeight:"500",
        color:"#5B5B5B",
        textAlign:"center",
        marginTop:20,
    },
    loginFailText: {
        fontWeight: 'bold',
        fontSize: 16,
        color: 'red'
    },
    loginFailWrap: {
        alignItems: 'center',
        justifyContent: 'center',

    }
})

export default LoginStyle;
