import React, {useState} from "react"
import {ScrollView, View} from "react-native"
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";

import SideMenu from "@/components/SideMenu";
import Header from "@/components/Header";
import Footer from "@/components/Footer";

interface props {
  footer: boolean;
  headerType: number;
  children: React.ReactNode;
}

const CommonLayout = ({footer, headerType, children} : props) => {
    const [isVisible, setIsVisible] = useState(false);

    const closeSide = () => {
        setIsVisible(false);
    };

    const openSide = () => {
        setIsVisible(true);
    }

    return(
        <>
          <SafeAreaProvider>
            <SafeAreaView
              edges={["top", "right", "bottom", "left"]}
              style={{flex: 1}}
            >
              <Header headerType={headerType} openSide={openSide}/>
              {
                isVisible && (<SideMenu closeSide={closeSide}/>)
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
