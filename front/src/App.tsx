import React, {useEffect} from 'react';
import Navigation from '@/navigators/Navigation';
import useLocation from "@/hooks/useLocation";
import {AppState} from "react-native";

function App(): JSX.Element {
    const location = useLocation({});
    useEffect(() => {
        console.log('gdgd')
        const appState = AppState.addEventListener('change', ()=>{
            if (AppState.currentState === 'active'){
                location.setForeground();
            } else if (AppState.currentState === 'background'){
                location.setBackground();
            }
        });
        return () => {
            appState.remove();
        }
    }, []);

    return (
    <>
      <Navigation/>
    </>
    );
}
export default App;
