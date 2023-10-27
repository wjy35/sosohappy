import Geolocation from "@react-native-community/geolocation";
import ReactNativeForegroundService from '@supersami/rn-foreground-service';
import {useState} from "react";

interface propsType {

}

function useLocation({}: propsType) {
    const [coordinate, setCoordinate] = useState<any>();
    let watchId:number;

    Geolocation.setRNConfiguration({
        authorizationLevel: 'auto',
        skipPermissionRequests: false,
    });

    const backgroundPositionFunc = (y: string, x: string) => {
        console.log('background longitude: ', x, 'latitude: ', y);
        // TODO: background에서 사용될 작업
    }

    const foregroundPositionFunc = (y: string, x: string) => {
        console.log('foreground longitude: ', x, 'latitude: ', y);
        // TODO: foreground에서 사용될 작업
    }

    const getPosition = () => {
        Geolocation.getCurrentPosition(
            position => {
                const latitude = JSON.stringify(position.coords.latitude);
                const longitude = JSON.stringify(position.coords.longitude);

                setCoordinate({
                    y: latitude,
                    x: longitude,
                });
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
        stopWatchPosition();
        ReactNativeForegroundService.add_task(
            () => {
                getPosition();
            },
            {
                delay: 300000,
                onLoop: true,
                taskId: 'taskid',
                onError: (e) => console.log('Error logging:', e),
            },
        );

        ReactNativeForegroundService.start({
            id: 1244,
            title: "Foreground Service",
            message: "We are live World",
            icon: "ic_launcher",
            button: true,
            button2: true,
            buttonText: "Button",
            button2Text: "Anther Button",
            buttonOnPress: "cray",
            setOnlyAlertOnce: true,
            color: "#000000",
            progress: {
                max: 100,
                curr: 50,
            },
        });
    };

    const setForeground = () => {
        ReactNativeForegroundService.remove_all_tasks(); // 앱 실행 시 백그라운드 태스크 전부 제거
        ReactNativeForegroundService.stopAll();

        console.log('foreground start');

        watchId = Geolocation.watchPosition(
            position => {
                const latitude = JSON.stringify(position.coords.latitude);
                const longitude = JSON.stringify(position.coords.longitude);
                setCoordinate({
                    y: latitude,
                    x: longitude,
                });
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
    }

    return {coordinate, setBackground, setForeground};
}

export default useLocation;
