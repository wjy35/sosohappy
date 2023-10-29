import { useState, useEffect, useCallback } from "react"
import { View, Text, Image, TouchableOpacity, Dimensions } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import BottomSheet from "@/components/BottomSheet";
import MapView, {PROVIDER_GOOGLE, Marker} from "react-native-maps"
import messaging from '@react-native-firebase/messaging'

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

import MapStyle from "@/styles/MapStyle";

messaging().setBackgroundMessageHandler(async remoteMessage => {
  console.log('[Background Remote Message]', remoteMessage);
});

const Map = () => {
  const mapWidth = Dimensions.get("window").width;
  const mapHeight = Dimensions.get("window").height;
  const [bottomSheetStatus, setBottomSheetStatus] = useState<Boolean>(false);
  // 이곳에 GPS에서 가져온 내 위치 정보를 넣으면 됩니다, 지금 default 정적으로 넣은 거는 멀티캠퍼스 역삼 위도 경도입니다.
  const [myPosition, setMyPosition] = useState<any>({
    latitude:37.501409,
    longitude:127.039681,
    latitudeDelta: 0.009,
    longitudeDelta: 0.009,
  });
  const [aroundPositions, setAroundPositions] = useState<any[]>([
    {
      latitude: 37.500069,
      longitude: 127.036841,
    },
  ]);

  const updateBottomSheetStatus = (updateStatus: Boolean) => {
    setBottomSheetStatus(updateStatus);
  }

  const getFcmToken = async () => {
    const fcmToken = await messaging().getToken();
    console.log('[FCM Token] ', fcmToken);
  };
 
  useEffect(() => {
    getFcmToken();

    const unsubscribe = messaging().onMessage(async remoteMessage => {
      console.log('[Remote Message] ', JSON.stringify(remoteMessage));
    });

    return unsubscribe;
  }, []);

  return (
    <CommonLayout footer={true} headerType={0}>
      <View style={MapStyle.mapContainer}>
        <MapView
          style={{width: mapWidth, height: mapHeight}}
          provider={PROVIDER_GOOGLE}
          zoomEnabled={true}
          rotateEnabled={true}
          showsUserLocation={true}
          showsMyLocationButton={true}
          initialRegion={myPosition}
        >
          <Marker
            description="my position"
            coordinate={{latitude: myPosition.latitude, longitude: myPosition.longitude}}
            pinColor="#37DDEB"
          />

          {
            aroundPositions.map((aroundMarker, index) => {
              return(
                <>
                  <Marker
                    description="around"
                    coordinate={{latitude: aroundMarker.latitude, longitude: aroundMarker.longitude}}
                    pinColor="#E9747A"
                  />
                </>
              );
            })
          }
        </MapView>
      </View>
      <TouchableOpacity activeOpacity={0.7}>
        <View style={MapStyle.createHelpWrap}>
          <Image
            source={ColorMegaphoneIcon}
            style={MapStyle.megaphoneIcon}
          />
          <View style={MapStyle.createHelpInfo}>
            <Text style={MapStyle.helpSubTitle}>도움이 필요하신가요?</Text>
            <Text style={MapStyle.helpMainTitle}>주변에 요청해보세요!</Text>
          </View>
          <Text style={MapStyle.helpButton}>도움요청</Text>
        </View>
      </TouchableOpacity>
      {
        bottomSheetStatus ?
        <BottomSheet updateBottomSheetStatus={(updateStatus:Boolean) => updateBottomSheetStatus(updateStatus)}/>
        :
        <></>
      }
    </CommonLayout>
  );
};

export default Map;
