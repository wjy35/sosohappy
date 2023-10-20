import {View, Text, StyleSheet} from "react-native"
import { responsiveWidth } from "react-native-responsive-dimensions";

interface propsType {
    level: string,
    title: string,
    description: string
}

const AuthTitle = ({level, title, description} : propsType) => {
    return(
        <>
            <View style={styles.authTitleWrap}>
                <Text style={styles.authPointTitle}>{level}단계</Text>
                <Text style={styles.authMainTitle}>{title}</Text>
                <Text style={styles.authDescription}>{description}</Text>
            </View>
        </>
    );
}

const styles = StyleSheet.create({
    authTitleWrap:{
        marginHorizontal:responsiveWidth(4),
        marginTop:27,
    },
    authPointTitle:{
        fontSize:24,
        fontWeight:"700",
        color:"#32323C",
    },
    authMainTitle:{
        fontSize:24,
        fontWeight:"700",
        color:"#3B3D9A",
        marginTop:2,
    },
    authDescription:{
        fontSize:14,
        fontWeight:"400",
        color:"#646464",
        marginTop:6,
    },
})

export default AuthTitle;