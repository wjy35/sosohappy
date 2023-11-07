import React, {useEffect} from 'react';
import Navigation from '@/navigators/Navigation';
import useLocation from "@/hooks/useLocation";
import {AppState} from "react-native";
import {Provider} from "mobx-react";
import userStore from "@/store/userStore";

function App(): JSX.Element {
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
    <Provider userStore={userStore}>
      <Navigation/>
    </Provider>
    );
}
export default App;
