import { View, Text, StyleSheet, Image } from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

interface propsType{
    thumbnail: HTMLImageElement,
    content: string,
}

const YourChat = ({thumbnail, content}: propsType) => {
    return(
        <>
            <View style={styles.yourChatWrap}>                
                <View>
                    <Image
                        source={thumbnail}
                        style={styles.thumbanailImg}
                    />
                </View>
                <View style={styles.wordBalloon}>
                    <Text style={styles.wordBalloonText}>{content}</Text>
                    <View style={styles.wordBalloonTail}></View>        
                </View>
            </View>
        </>
    );
}

const styles = StyleSheet.create({
    yourChatWrap:{
        marginTop:12,
    },
    thumbanailImg:{
        width:36,
        height:36,
        borderRadius:50,
    },
    wordBalloon:{
        width:responsiveWidth(50),
        backgroundColor:"#FDE4F9",
        borderRadius:6,
        padding:10,
        position:"relative",
        left:46,
    },
    wordBalloonText:{
        fontSize:12,
        fontWeight:"500",
        color:"#826D7E",
    },
    wordBalloonTail:{
        width:11,
        height:11,
        backgroundColor:"#FDE4F9",
        borderBottomLeftRadius:100,
        position:"absolute",
        left:-10,
    },
})

export default YourChat;