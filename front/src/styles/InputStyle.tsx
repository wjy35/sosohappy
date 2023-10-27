import {StyleSheet} from "react-native"
import {responsiveWidth} from "react-native-responsive-dimensions";

const InputStyle = StyleSheet.create({
    InputTitle:{
        fontSize:12,
        fontWeight:"500",
        color:"#B6B6B6",
        marginTop:12,
    },
    Input:{
        width:responsiveWidth(92),
        height:50,
        borderWidth:1,
        borderColor:"#9D9D9D",
        borderRadius: 16,
        marginTop:6,
        padding:10,
        fontSize:14,
        fontWeight:"900",
        color:"#535353",
    },
    alertInput: {
        borderColor: 'red',
    },
    alertText: {
        color: 'red',
    }
});

export default InputStyle;
