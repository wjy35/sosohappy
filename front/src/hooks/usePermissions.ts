import {useEffect, useState} from 'react';
import {Alert, Linking, Platform, BackHandler} from 'react-native';
import {check, PERMISSIONS, RESULTS} from 'react-native-permissions';

function usePermissions() {
    const [locationStatus, setLocationStatus] = useState('denied');
    const [notificationStatus, setNotificationStatus] = useState('denied');

    const checkPermissions = () => {
        checkLocationPermissions();
        checkNotificationPermissions();
    };

    const checkLocationPermissions = () => {
        if (Platform.OS === 'android') {
            check(PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION)
                .then(result => {
                    if (result === RESULTS.BLOCKED || result === RESULTS.DENIED) {
                        // Alert.alert(
                        //     '이 앱은 위치 권한 허용이 필요합니다.',
                        //     '앱 설정 화면을 열어서 항상 허용으로 바꿔주세요.',
                        //     [
                        //         {
                        //             text: '설정으로 이동',
                        //             onPress: () => {
                        //                 Linking.openSettings();
                        //                 setStatus('check');
                        //             },
                        //         },
                        //         {
                        //             text: '앱 종료',
                        //             onPress: () => BackHandler.exitApp(),
                        //             style: 'cancel',
                        //         },
                        //     ],
                        // );
                    } else {
                        setLocationStatus(result);
                    }
                })
                .catch(console.error);
        }
    }

    const checkNotificationPermissions = () => {
        if (Platform.OS === 'android') {
            check(PERMISSIONS.ANDROID.POST_NOTIFICATIONS)
                .then(result => {
                    if (result === RESULTS.BLOCKED || result === RESULTS.DENIED) {

                    } else {
                        setNotificationStatus(result);
                    }
                })
                .catch(console.error);
        }
    }

    // 권한 관련
    useEffect(() => {
        checkPermissions();
    }, []);

    return {locationStatus, checkPermissions}
}

export default usePermissions;
