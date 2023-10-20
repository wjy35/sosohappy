import {Button, Text, View} from "react-native";

const NavigatorDummy = ({navigation}: any) => {
  return (
    <View>
      <Text>Dummy Screen for Navigate </Text>
      <Button title='Main' onPress={()=>navigation.navigate('Main')}/>
      <Button title='Chat' onPress={()=>navigation.navigate('Chat')}/>
      <Button title='ChatList' onPress={()=>navigation.navigate('ChatList')}/>
      <Button title='CreateHelp' onPress={()=>navigation.navigate('CreateHelp')}/>
      <Button title='Login' onPress={()=>navigation.navigate('Login')}/>
      <Button title='Map' onPress={()=>navigation.navigate('Map')}/>
      <Button title='MyPage' onPress={()=>navigation.navigate('MyPage')}/>
      <Button title='SignUpAuth' onPress={()=>navigation.navigate('SignUpAuth')}/>
      <Button title='SignUpInput' onPress={()=>navigation.navigate('SignUpInput')}/>
      <Button title='SignUpSeparate' onPress={()=>navigation.navigate('SignUpSeparate')}/>
    </View>
  );
};

export default NavigatorDummy;
