import { View, Text, StyleSheet } from "react-native"

interface propsType{
    content: string,
}

const MyChat = ({content}: propsType) => {
    return(
        <>
            <View style={styles.wordBalloon}>
                <Text style={styles.wordBalloonText}>{content}</Text>
            </View>
            <View style={styles.wordBalloonTail}></View>        
        </>
    );
}

const styles = StyleSheet.create({
    wordBalloon:{
        
    },
    wordBalloonText:{

    },
    wordBalloonTail:{

    },
})

export default MyChat;