import { View, Text, StyleSheet } from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

interface propsType{
    content: string,
}

const MyChat = ({content}: propsType) => {
    return(
        <>
            <View style={styles.wordBalloon}>
                <Text style={styles.wordBalloonText}>{content}</Text>
                <View style={styles.wordBalloonTail}></View>        
            </View>
        </>
    );
}

const styles = StyleSheet.create({
    wordBalloon:{
        width:responsiveWidth(50),
        backgroundColor:"#9C8AFA",
        borderRadius:6,
        padding:10,
        position:"relative",
        marginTop:12,
        marginLeft:"auto",
        marginRight:11,
    },
    wordBalloonText:{
        fontSize:12,
        fontWeight:"500",
        color:"#FFFFFF",
    },
    wordBalloonTail:{
        width:11,
        height:11,
        backgroundColor:"#9C8AFA",
        borderBottomRightRadius:100,
        position:"absolute",
        left:responsiveWidth(50) - 4,
    },
})

export default MyChat;