import Geolocation from "@react-native-community/geolocation";
import ReactNativeForegroundService from '@supersami/rn-foreground-service';
import {useEffect, useState} from "react";
import helpMatchApi from "@/apis/helpMatchApi";

interface propsType {

}

function useLocation({}: propsType) {
    const [coordinate, setCoordinate] = useState<any>();
    // let watchId:number;
    const [watchId, setWatchId] = useState<number|undefined>();
    const [status, setStatus] = useState(0);

    Geolocation.setRNConfiguration({
        authorizationLevel: 'auto',
        skipPermissionRequests: false,
    });

    const init = () => {
        ReactNativeForegroundService.remove_all_tasks(); // 앱 실행 시 백그라운드 태스크 전부 제거
        ReactNativeForegroundService.stopAll();
        Geolocation.getCurrentPosition(
            position => {
                const latitude = Number(JSON.stringify(position.coords.latitude));
                const longitude = Number(JSON.stringify(position.coords.longitude));
                setCoordinate({
                    latitude: latitude,
                    longitude: longitude,
                })
            },
            error => {console.log(error)},
        )
    }

    const sendPosition = async (latitude: number, longitude: number) => {
        try {
            const res = await helpMatchApi.sendPosition({
                latitude: latitude,
                longitude: longitude,
            })
            if (res.status === 200){
                console.log('uselocation success');
            }
        } catch (err) {
            console.log('uselocation error');
            console.log(err);
        }
    }

    const backgroundPositionFunc = (lati: number, longi: number) => {
        // TODO: background에서 사용될 작업
        sendPosition(lati, longi)
        setCoordinate({
            latitude: lati,
            longitude: longi,
        });
    }

    const foregroundPositionFunc = (lati: number, longi: number) => {
        // TODO: foreground에서 사용될 작업
        sendPosition(lati, longi);
        setCoordinate({
            latitude: lati,
            longitude: longi,
        });
    }

    const getPosition = () => {
        Geolocation.getCurrentPosition(
            position => {
                const latitude = Number(JSON.stringify(position.coords.latitude));
                const longitude = Number(JSON.stringify(position.coords.longitude));
                backgroundPositionFunc(latitude, longitude);
            },
            error => {console.log(error)},
        )
    };

    const stopWatchPosition = () => {
        Geolocation.clearWatch(watchId);
    };

    const setBackground = () => {
        console.log('background start');
        setStatus(2);
        stopWatchPosition();
        ReactNativeForegroundService.add_task(
            () => {
                getPosition();
            },
            {
                delay: 60000,
                onLoop: true,
                taskId: 'taskid',
                onError: (e) => console.log('Error logging:', e),
            },
        );

        ReactNativeForegroundService.start({
            id: 1244,
            title: "소소하지만 소중한 행복",
            message: "주변의 행운을 찾는 중입니다",
            icon: "ic_launcher",
            setOnlyAlertOnce: true,
            color: "#000000",
        });
    };

    const setForeground = () => {
        setStatus(1);
        ReactNativeForegroundService.remove_all_tasks(); // 앱 실행 시 백그라운드 태스크 전부 제거
        ReactNativeForegroundService.stopAll();

        console.log('foreground start');

        const newWatchId = Geolocation.watchPosition(
            position => {
                const latitude = Number(JSON.stringify(position.coords.latitude));
                const longitude = Number(JSON.stringify(position.coords.longitude));
                foregroundPositionFunc(latitude, longitude);
            },
            error => {
                console.log(error);
            },
            {
                distanceFilter: 10, // Minimum distance (in meters) to update the location
                interval: 900000, // Update interval (in milliseconds), which is 15 minutes
                fastestInterval: 300000, // Fastest update interval (in milliseconds)
                accuracy: {
                    android: 'highAccuracy',
                    ios: 'best',
                },
                showsBackgroundLocationIndicator: true,
                pausesLocationUpdatesAutomatically: false,
                activityType: 'fitness', // Specify the activity type (e.g., 'fitness' or 'other')
                useSignificantChanges: false,
                deferredUpdatesInterval: 0,
                deferredUpdatesDistance: 0,
                foregroundService: {
                    notificationTitle: 'Tracking your location',
                    notificationBody: 'Enable location tracking to continue', // Add a notification body
                },
            }
        );
        setWatchId(newWatchId);
    }

    useEffect(() => {
        init();
    }, []);

    return {coordinate, setBackground, setForeground, status};
}

export default useLocation;
