import CommonLayout from "@/components/CommonLayout";
import {ChatSocket, helpSocket} from "@/types";
import {WebView} from "react-native-webview";
import CertificateStyle from "@/styles/CertificateStyle";
import {useFocusEffect} from "@react-navigation/native";
import {useCallback} from "react";

interface propsType{
    socket: helpSocket;
    chatSocket: ChatSocket
}

const Certificate = ({socket, chatSocket}: propsType) => {

    useFocusEffect(
        useCallback(() => {
            const disConnect = () => {
                if (!socket.connected) return;
                socket.disConnect();
            }
            disConnect();
            return () => {};
        }, [socket.connected])
    )

    useFocusEffect(
        useCallback(() => {
            const disConnect = () => {
                if (!chatSocket.connected) return;
                chatSocket.disConnect();
            }
            disConnect();
            return () => {};
        }, [chatSocket.connected])
    )

    return(
        <CommonLayout headerType={0} footer={true}>
            <WebView
                source={{uri:`http://sosohappy.co.kr:8888/docs`}}
                style={CertificateStyle.webView}
            />
        </CommonLayout>
    );
}

export default Certificate;
