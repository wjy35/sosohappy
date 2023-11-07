import React, { useState, useEffect } from "react"
import { View, Text, Image, TouchableOpacity, Dimensions } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import BottomSheet from "@/components/BottomSheet";
import MapLoading from "@/components/MapLoading";
import MapView, {PROVIDER_GOOGLE, Marker, Polyline} from "react-native-maps";
import {MAP_LINE_API_KEY} from "@env"

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

import MapStyle from "@/styles/MapStyle";

interface propsType{
  location: any;
}

const Map = ({location}: propsType) => {
  const mapWidth = Dimensions.get("window").width;
  const mapHeight = Dimensions.get("window").height;
  const [bottomSheetStatus, setBottomSheetStatus] = useState<Boolean>(false);
  // 이곳에 GPS에서 가져온 내 위치 정보를 넣으면 됩니다, 지금 default 정적으로 넣은 거는 멀티캠퍼스 역삼 위도 경도입니다.
  const [aroundPositions, setAroundPositions] = useState<any[]>([
    {
      latitude: 37.500069,
      longitude: 127.036841,
    },
  ]);
  const [points, setPoints] = useState<any>();

  const updateBottomSheetStatus = (updateStatus: Boolean) => {
    setBottomSheetStatus(updateStatus);
  }

  const pressAroundMarker = () => {
    setBottomSheetStatus(true);
  }

  //Todo : 현재는 파이낸셜센터 - 멀티캠퍼스 정적 위경도 넣어줌 (추후 GPS에 따른 위경도 변경)
  const getArrivalToDesinationPointLine = async () => {
    const options = {
      method: 'POST',
      headers: {
        accept: 'application/json',
        'content-type': 'application/json',
        appKey: MAP_LINE_API_KEY,
      },
      body: JSON.stringify({
        startX: location.longitude,
        startY: location.latitude,
        angle: 20,
        speed: 30,
        endPoiId: '10001',
        endX: 127.036841,
        endY: 37.500069,
        passList: `${location.longitude},${location.latitude}_127.036841,37.500069`,
        reqCoordType: 'WGS84GEO',
        startName: '%EC%B6%9C%EB%B0%9C',
        endName: '%EB%8F%84%EC%B0%A9',
        searchOption: '0',
        resCoordType: 'WGS84GEO',
        sort: 'index'
      })
    };

    fetch('https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&callback=function', options)
      .then(response => response.json())
      .then(response => setPoints(response))
      .catch(err => console.error(err));
  }

  useEffect(() => {
    const getApi = async () => {
      await getArrivalToDesinationPointLine();
    }

    getApi();
  }, [])

  return (
    <>
      <CommonLayout footer={true} headerType={0}>
        <View style={MapStyle.mapContainer}>
          {
            location && (
                <>
                  <MapView
                      style={{width: mapWidth, height: mapHeight}}
                      provider={PROVIDER_GOOGLE}
                      zoomEnabled={true}
                      rotateEnabled={true}
                      showsUserLocation={true}
                      showsMyLocationButton={true}
                      initialRegion={{
                        latitude: location.latitude,
                        longitude: location.longitude,
                        latitudeDelta: 0.009,
                        longitudeDelta: 0.009,
                      }}
                  >
                    <Marker
                        description="my position"
                        coordinate={{latitude: location.latitude, longitude: location.longitude}}
                        pinColor="#37DDEB"
                    />

                    {
                      aroundPositions.map((aroundMarker, index) => {
                        return(
                            <React.Fragment key={`aroundMarker${index}`}>
                              <Marker
                                  description="around"
                                  coordinate={{latitude: aroundMarker.latitude, longitude: aroundMarker.longitude}}
                                  pinColor="#E9747A"
                                  onPress={() => pressAroundMarker()}
                              />
                            </React.Fragment>
                        );
                      })
                    }
                    {
                      points?.features?.map((point, index) => {
                        console.log(point);
                        if(point.geometry.type === "LineString"){
                          const pathCoordinates = [];

                          for(let i=0; i<point.geometry.coordinates.length; i++){
                            pathCoordinates.push({latitude: point.geometry.coordinates[i][1], longitude: point.geometry.coordinates[i][0]});
                          }
                          return(
                              <React.Fragment key={`points${index}`}>
                                <Polyline
                                    coordinates={pathCoordinates}
                                    strokeColor="red"
                                    strokeWidth={2}
                                />
                              </React.Fragment>
                          );
                        }
                      })
                    }
                  </MapView>
                </>
              )
          }
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
      <MapLoading/>
    </>
  );
};

export default Map;
