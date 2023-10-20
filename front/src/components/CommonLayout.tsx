import {useState} from "react"
import {ScrollView} from "react-native"
import { SafeAreaProvider, SafeAreaView } from "react-native-safe-area-context";

import SideMenu from "@/components/SideMenu";

const CommonLayout = ({children} : any) => {
    return(
        <>
            <SafeAreaProvider>
				<SafeAreaView edges={["top", "right", "bottom", "left"]}>
					<ScrollView>
                        {children}
                    </ScrollView>
				</SafeAreaView>
			</SafeAreaProvider>
        </>
    );
}

export default CommonLayout;