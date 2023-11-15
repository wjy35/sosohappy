import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Chat from '@/screens/Chat';
import ChatList from '@/screens/ChatList';
import CreateHelp from '@/screens/CreateHelp';
import Login from '@/screens/Login';
import Main from '@/screens/Main';
import Map from '@/screens/Map';
import MyPage from '@/screens/MyPage';
import SignUpAuth from '@/screens/SignUpAuth';
import SignUpInput from '@/screens/SignUpInput';
import SignUpSeparate from '@/screens/SignUpSeparate';
import NavigatorDummy from '@/screens/NavigatorDummy';
import Character from '@/screens/Character';
import useStore from "@/store/store";
import Certificate from '@/screens/Certificate';
import useSocket from "@/hooks/useSocket";
import {useEffect, useState} from "react";
import useLocation from "@/hooks/useLocation";
import {AppState} from "react-native";
import messaging from "@react-native-firebase/messaging";
import useChatSocket from "@/hooks/useChatSocket";


const Stack = createStackNavigator();

interface propsType{
  // location: any;
}

messaging().setBackgroundMessageHandler(async remoteMessage => {
  console.log('[Background Remote Message]', remoteMessage);
});

const Navigation = ({}: propsType) => {
  const {userInfo} = useStore();
  const socket = useSocket();
  const location = useLocation({});
  const chatSocket = useChatSocket();
  const [member, setMember] = useState({
    memberId: null
  });

  useEffect(() => {
    const appState = AppState.addEventListener('change', () => {
      console.log(AppState.currentState);
      if (AppState.currentState === 'active') {
        userInfo && location.setForeground(userInfo.memberId, socket.otherMember);
      } else if (AppState.currentState === 'background'&&member.memberId) {
        location.setBackground(member.memberId, socket.otherMember);
      }
    });
    return () => {
      appState.remove();
    }
  }, []);

  useEffect(() => {
    member.memberId = userInfo?.memberId;
    userInfo?.memberId&&socket.getMemberId(userInfo?.memberId);
  }, [userInfo])

  useEffect(() => {
    const unsubscribe = messaging().onMessage(async remoteMessage => {
      console.log('[Remote Message] ', JSON.stringify(remoteMessage));
    });

    return unsubscribe;
  }, []);

  useEffect(() => {
    if (userInfo) {
      location.setForeground(userInfo.memberId, socket.otherMember);
    } else {
      location.stopWatchPosition();
      socket.disConnect();
    }
  }, [userInfo, socket.otherMember])

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{ headerShown: false, animationEnabled: false }} initialRouteName='Main'>
        <Stack.Screen name="Dummy" component={NavigatorDummy}/>
        <Stack.Screen name="Chat">
          {
            props => (
                <Chat helpSocket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="ChatList">
          {
            props => (
                <ChatList socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="CreateHelp">
          {
            props => (
                <CreateHelp location={location.coordinate} socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="Login">
          {
            props => (
                <Login socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="Main">
          {
            props => (
                <Main socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="Map">
          {
            props => (
                <Map location={location.coordinate} socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="MyPage">
          {
            props => (
                <MyPage socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="SignUpAuth">
          {
            props => (
                <SignUpAuth socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="SignUpInput">
          {
            props => (
                <SignUpInput socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="SignUpSeparate">
          {
            props => (
                <SignUpSeparate socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="Character">
          {
            props => (
                <Character socket={socket} chatSocket={chatSocket}/>
            )
          }
        </Stack.Screen>
        <Stack.Screen name="Certificate">
          {
              props => (
                  <Certificate socket={socket} chatSocket={chatSocket}/>
              )
          }
        </Stack.Screen>
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default Navigation;

