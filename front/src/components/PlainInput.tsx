import React, {useRef} from 'react';
import {Text, TextInput, View} from "react-native";
import InputStyle from "@/styles/InputStyle";
import inputStyle from "@/styles/InputStyle";

interface props {
    title?: string;
    placeholder: string;
    onChangeText: Function;
    onPressIn?: Function;
    text: string;
    onSubmit?: number;
    isValid?: boolean;
    errorMessage?: string;
    secureTextEntry?: boolean;
    editable?: boolean;
}

const PlainInput = ({title, placeholder, onChangeText, onPressIn, text, isValid, onSubmit, errorMessage, secureTextEntry, editable}: props) => {
    function onPressInFunction(){
        onPressIn(now);
    }
    function onSubmitFunction(newText: string){
        onChangeText('');
        onSubmit(text);
    }

    return (
        <View>
            {
                title && (
                    <View style={[]}>
                        <Text style={[InputStyle.InputTitle]}>{title}</Text>
                    </View>
                )
            }
            <View style={[]}>
                <TextInput
                    style={[InputStyle.Input]}
                    placeholder={placeholder}
                    onChangeText={(newText) => onChangeText(newText)}
                    onPressIn={onPressIn&&onPressInFunction}
                    onSubmitEditing={onSubmit&&onSubmitFunction}
                    value={text}
                    secureTextEntry={secureTextEntry&&secureTextEntry}
                    editable={editable&&editable}
                />
            </View>
            {
                (!isValid&&errorMessage&&text) && (
                    <View>
                        <Text>{errorMessage}</Text>
                    </View>
                )
            }

        </View>
    );
};

export default PlainInput;
