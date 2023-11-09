import CommonLayout from "@/components/CommonLayout";

const Certificate = () => {
    return(
        <CommonLayout headerType={0} footer={true}>
            <WebView
                source={{uri:`http://70.12.247.139:5173/docs`}}
                style={CertificateStyle.webView}
            />
        </CommonLayout>
    );
}

export default Certificate;