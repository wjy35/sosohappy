import React, {useEffect} from 'react';
import Navigation from '@/navigators/Navigation';
import useLocation from "@/hooks/useLocation";
import {AppState} from "react-native";

import useStore from "@/store/store";
import {Provider} from "mobx-react";
import * as Sentry from "@sentry/react-native";
import {SENTRY_DSN} from "@env"
import usePermissions from "@/hooks/usePermissions";
import {useFocusEffect} from "@react-navigation/native";
import RNSecureStorage from "rn-secure-storage";
import memberApi from "@/apis/memberApi";

function App(): JSX.Element {
    const {login} = useStore();


    Sentry.init({
      dsn: SENTRY_DSN,
      tracesSampleRate: 1.0,
    })
    usePermissions();

    const autologin = async () => {
        try {
            const token = await RNSecureStorage.get("accessToken");
            if (token){
                try {
                    const userRes = await memberApi.getMember();
                    if (userRes.status === 200){
                        login(userRes.data.result.member);
                    }
                } catch (err) {
                    console.log(err);
                }
            }
        } catch (err) {
            console.log(err);
        }

    }

    useEffect(() => {
        autologin();
    }, []);

    return (
      <>
        <Navigation/>
      </>
    );
}
export default Sentry.wrap(App);
