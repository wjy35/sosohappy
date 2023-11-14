import React, { useState, useEffect } from "react"
import { View, Text, Image, TouchableOpacity, Dimensions } from "react-native";
import CommonLayout from "@/components/CommonLayout";
import BottomSheet from "@/components/BottomSheet";
import MapLoading from "@/components/MapLoading";
import MapView, {PROVIDER_GOOGLE, Marker, Polyline} from "react-native-maps";
import {MAP_LINE_API_KEY} from "@env"
import {clover} from "@/assets/icons/icons";

import ColorMegaphoneIcon from "@/assets/img/color-megaphone-icon.png"

import MapStyle from "@/styles/MapStyle";
import {useFocusEffect, useNavigation} from "@react-navigation/native";
import {ChatSocket, helpDetail, helpSocket} from "@/types";
import useStore from "@/store/store";

interface propsType{
  location: any;
  socket: helpSocket;
  chatSocket: ChatSocket
}

const Map = ({location, socket, chatSocket}: propsType) => {
  const mapWidth = Dimensions.get("window").width;
  const mapHeight = Dimensions.get("window").height;
  const [bottomSheetStatus, setBottomSheetStatus] = useState<Boolean>(false);
  const [selectedHelp, setSelectedHelp] = useState<helpDetail>();
  const navigation = useNavigation();
  // 이곳에 GPS에서 가져온 내 위치 정보를 넣으면 됩니다, 지금 default 정적으로 넣은 거는 멀티캠퍼스 역삼 위도 경도입니다.
  const [points, setPoints] = useState<any>();
  const [loading, setLoading] = useState(false);
  const {userInfo} = useStore();

  const updateBottomSheetStatus = (updateStatus: Boolean) => {
    setBottomSheetStatus(updateStatus);
  }

  const pressAroundMarker = (aroundMarker: helpDetail) => {
      setSelectedHelp(aroundMarker);
      setBottomSheetStatus(true);
  }

  //Todo : 현재는 파이낸셜센터 - 멀티캠퍼스 정적 위경도 넣어줌 (추후 GPS에 따른 위경도 변경)
  const getArrivalToDesinationPointLine = async (longitude: number, latitude: number) => {
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
        endX: longitude,
        endY: latitude,
        passList: `${location.longitude},${location.latitude}_${longitude},${latitude}`,
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
      .then(response => {
          setPoints(response);
      })
      .catch(err => console.error(err));
  }

  useFocusEffect(
      React.useCallback(() => {
        const connect = () => {
          if (socket.connected) return;
          socket.connect();
        }
        connect();
        return () => {};
      }, [socket.connected])
  )

    useEffect(() => {
        if (socket.status === 'ON_MOVE'){
            getArrivalToDesinationPointLine(socket.data.helpEntity.longitude, socket.data.helpEntity.latitude);
            setSelectedHelp(socket.data.helpEntity);
        } else if (socket.status === 'DEFAULT'){
            setPoints(null);
        }
    }, [socket.status]);

  useEffect(()=>{
      console.log(socket.otherMemberPoint)
  }, [socket.otherMemberPoint])

  return (
      <>
          <CommonLayout footer={true} headerType={0}>
              <View style={MapStyle.mapContainer}>
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
                      {
                          (socket.status==='WAIT_COMPLETE'&&socket.otherMemberPoint?.latitude)&&(
                                  <Marker
                                      description="helperPosition"
                                      coordinate={{latitude: socket.otherMemberPoint?.latitude, longitude: socket.otherMemberPoint?.longitude}}
                                  >
                                      <Image
                                          source={require("@/assets/sosomon/type4/Whale.png")}
                                          style={{width: 100, height: 100, bottom: -20}}
                                      />
                                  </Marker>
                          )
                      }
                      {
                          (socket.status==='WAIT_COMPLETE'&&socket.data.otherMemberPoint?.latitude)&&(
                              <Marker
                                  description="helperStartPosition"
                                  coordinate={{latitude: socket.data.otherMemberPoint?.latitude, longitude: socket.data.otherMemberPoint?.longitude}}
                                  pinColor="#E9747A"
                              />
                          )
                      }
                      {
                          (socket.status==='DEFAULT')&&socket.helpList.map((aroundMarker, index) => {
                              return(
                                  <React.Fragment key={`aroundMarker${index}`}>
                                      <Marker
                                          description="around"
                                          coordinate={{latitude: aroundMarker.latitude, longitude: aroundMarker.longitude}}
                                          pinColor="#E9747A"
                                          onPress={() => pressAroundMarker(aroundMarker)}
                                      />
                                  </React.Fragment>
                              );
                          })
                      }
                      {
                          (socket.status==='ON_MOVE'&&socket.data.helpEntity?.latitude)&&(
                              <>
                                  <Marker
                                      description="helpTargetLocation"
                                      coordinate={{latitude: socket.data?.helpEntity?.latitude, longitude: socket.data?.helpEntity?.longitude}}
                                      pinColor="#E9747A"
                                      onPress={() => pressAroundMarker(socket.data?.helpEntity)}
                                  />
                                  {
                                      points?.features?.map((point, index) => {
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
                                                          strokeWidth={5}
                                                      />
                                                  </React.Fragment>
                                              );
                                          }
                                      })
                                  }
                              </>
                          )
                      }
                  </MapView>
              </View>
              {
                  (socket.status==='DEFAULT' && userInfo.disabled) && (
                      <View style={{position: 'absolute', top: 10}}>
                          <TouchableOpacity activeOpacity={0.7} onPress={()=>navigation.navigate('CreateHelp')}>
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
                      </View>
                  )
              }

              {
                  bottomSheetStatus ?
                      <BottomSheet selectedHelp={selectedHelp} updateBottomSheetStatus={(updateStatus:Boolean) => updateBottomSheetStatus(updateStatus)} status={socket.status}/>
                      :
                      <></>
              }
          </CommonLayout>
      </>
  );
};

export default Map;
