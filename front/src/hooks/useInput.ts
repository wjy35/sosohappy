import {useState} from "react";


interface props {
    initialState?: string;
    placeholder: string;
    title?: string;
    onChange?: Function;
    initialIsValid?: boolean;
    errorMessage?: string;
}

function useInput({placeholder, title, initialState='', onChange, initialIsValid=true, errorMessage}: props){
    const [text, setText] = useState<string>(initialState);
    const [isValid, setIsValid] = useState(initialIsValid);

    function onChangeText(newText: string){
        setText(newText);
        onChange&&onChange(newText);
    }

    function updateIsValid(newIsValid: boolean){
        setIsValid(newIsValid);
    }

    function reset(){
        setText('');
    }

    return {text, title, onChangeText, placeholder, reset, updateIsValid, isValid, errorMessage}
}

export default useInput;
