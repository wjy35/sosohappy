import React, {useEffect, useState} from "react"
import {ScrollView, View} from "react-native"
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";
import messaging from '@react-native-firebase/messaging'

import SideMenu from "@/components/SideMenu";
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import {useFocusEffect, useNavigation} from "@react-navigation/native";

interface props {
  footer: boolean;
  headerType: number;
  nowPage?: string;
  children: React.ReactNode;
}

messaging().setBackgroundMessageHandler(async remoteMessage => {
  console.log('[Background Remote Message]', remoteMessage);
});

const CommonLayout = ({footer, headerType, nowPage, children} : props) => {
    const [isVisible, setIsVisible] = useState(false);
    const navigation = useNavigation();

    const closeSide = () => {
        setIsVisible(false);
    };

    const openSide = () => {
        setIsVisible(true);
    }

    const getFcmToken = async () => {
      const fcmToken = await messaging().getToken();
      console.log('[FCM Token] ', fcmToken);
    };

    useEffect(() => {
        const focusNav = navigation.addListener('blur', () => {
            // do something
            closeSide();
        });
        return focusNav;
    }, [navigation]);

    useEffect(() => {
      getFcmToken();
  
      const unsubscribe = messaging().onMessage(async remoteMessage => {
        console.log('[Remote Message] ', JSON.stringify(remoteMessage));
      });
  
      return unsubscribe;
    }, []);

    return(
        <>
          <SafeAreaProvider>
            <SafeAreaView
              edges={["top", "right", "bottom", "left"]}
              style={{flex: 1}}
            >
              <Header headerType={headerType} openSide={openSide}/>
              {
                isVisible && (<SideMenu closeSide={closeSide} nowPage={nowPage}/>)
              }
              <ScrollView>
                {children}
                {
                  footer && (<Footer />)
                }
              </ScrollView>
            </SafeAreaView>
          </SafeAreaProvider>
        </>
    );
}

export default CommonLayout;
