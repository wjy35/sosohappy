import React, {useEffect} from 'react';
import Navigation from '@/navigators/Navigation';
import useLocation from "@/hooks/useLocation";
import {AppState} from "react-native";

import useStore from "@/store/store";
import {Provider} from "mobx-react";
import * as Sentry from "@sentry/react-native";
import {SENTRY_DSN} from "@env"

function App(): JSX.Element {
    Sentry.init({
      dsn: SENTRY_DSN,
      tracesSampleRate: 1.0,
    })
    // const location = useLocation({});
    //
    // useEffect(() => {
    //     const appState = AppState.addEventListener('change', ()=>{
    //         if (AppState.currentState === 'active'){
    //             location.setForeground();
    //         } else if (AppState.currentState === 'background'){
    //             location.setBackground();
    //         }
    //     });
    //     return () => {
    //         appState.remove();
    //     }
    // }, []);

    return (
      <>
        <Navigation/>
      </>
    );
}
export default Sentry.wrap(App);
