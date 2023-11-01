import {Image, View} from "react-native";
import test from "@/assets/sosomon/type1/FennecFox.png";

interface propsType {
    src: any;
}

const SingleSosomon = ({src}: propsType) => {
    return (
        <View style={{width: 50, height: 50, borderWidth: 1}}>
            <Image
                source={src}
                style={{width: 50, height: 50, tintColor: '#000000', transform: [{scale: 2}]}}
            />
        </View>
    )
}

export default SingleSosomon;
