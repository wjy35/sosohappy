import {useEffect} from 'react';
import {Alert, Linking, Platform, BackHandler} from 'react-native';
import {check, PERMISSIONS, RESULTS} from 'react-native-permissions';

function usePermissions() {
    // 권한 관련
    useEffect(() => {
        // android 위치권한 설정
        if (Platform.OS === 'android') {
            check(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION)
                .then(result => {
                    console.log('check location', result);
                    if (result === RESULTS.BLOCKED || result === RESULTS.DENIED) {
                        Alert.alert(
                            '이 앱은 위치 권한 허용이 필요합니다.',
                            '앱 설정 화면을 열어서 항상 허용으로 바꿔주세요.',
                            [
                                {
                                    text: '설정으로 이동',
                                    onPress: () => Linking.openSettings(),
                                },
                                {
                                    text: '앱 종료',
                                    onPress: () => BackHandler.exitApp(),
                                    style: 'cancel',
                                },
                            ],
                        );
                    }
                })
                .catch(console.error);
        }
        // 다른 권한 설정
        // if (Platform.OS === 'android') {
        //     check(PERMISSIONS.ANDROID.CAMERA)
        //         .then(result => {
        //             if (result === RESULTS.DENIED || result === RESULTS.GRANTED) {
        //                 return request(PERMISSIONS.ANDROID.CAMERA);
        //             } else {
        //                 console.log(result);
        //                 throw new Error('카메라 지원 안 함');
        //             }
        //         })
        //         .catch(console.error);
        // }
    }, []);
}

export default usePermissions;
