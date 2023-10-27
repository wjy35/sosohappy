import {StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

const HistoryStyle = StyleSheet.create({
    historyWrap:{
        width:responsiveWidth(92),
        marginHorizontal:responsiveWidth(4),
        marginTop:12,
    },
})

export default HistoryStyle;