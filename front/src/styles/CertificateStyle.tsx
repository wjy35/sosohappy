import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const CertificateStyle = StyleSheet.create({
    webView:{
        width:responsiveWidth(100),
        height:responsiveWidth(140),
    }
})

export default CertificateStyle;