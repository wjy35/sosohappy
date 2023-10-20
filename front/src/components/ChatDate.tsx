import { View, Text, StyleSheet } from "react-native"

interface propsType{
    date: string,
}

const ChatDate = ({date} : propsType) => {
    return(
        <>
            <Text style={styles.chatDate}>{date}</Text>
        </>
    );
}

const styles = StyleSheet.create({
    chatDate:{
        fontSize:12,
        fontWeight:"700",
        color:"#B6B6B6",
        textAlign:"center",
        marginTop:12,
    },
})

export default ChatDate;