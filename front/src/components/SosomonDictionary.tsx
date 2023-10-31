import React from 'react';
import {View} from "react-native";
import SingleSosomon from "@/components/SingleSosomon";
import {type1} from '@/assets/sosomon/index'
// import test from "@/assets/sosomon/type1/FennecFox.png";
import FennecFox from "@/assets/sosomon/type1/FennecFox.png";
import Buffalo from "@/assets/sosomon/type1/Buffalo.png";
import Dog from "@/assets/sosomon/type1/Dog.png";
import Elephant from "@/assets/sosomon/type1/Elephant.png";
import Fox from "@/assets/sosomon/type1/Fox.png";
import Gazelle from "@/assets/sosomon/type1/Gazelle.png";
import Ox from "@/assets/sosomon/type1/Ox.png";
import Pig from "@/assets/sosomon/type1/Pig.png";
import Rabbit from "@/assets/sosomon/type1/Rabbit.png";
import Zebra from "@/assets/sosomon/type1/Zebra.png";


const SosomonDictionary = ({}) => {

    return (
        <View style={[{borderWidth: 1}]}>
            <View style={[{flexWrap: 'wrap', flexDirection: 'row', justifyContent: 'center'}]}>
                {/*{*/}
                {/*    type1.map((el, idx)=>{*/}
                {/*        console.log(el);*/}
                {/*        return (*/}
                {/*            // <React.Fragment }>*/}
                {/*                <SingleSosomon src={el} key={`type1-${idx}`} />*/}
                {/*            // </React.Fragment>*/}
                {/*        )*/}
                {/*    })*/}
                {/*}*/}
                <SingleSosomon src={FennecFox}/>
                <SingleSosomon src={Buffalo}/>
                <SingleSosomon src={Dog}/>
                <SingleSosomon src={Elephant}/>
                <SingleSosomon src={Fox}/>
                <SingleSosomon src={Gazelle}/>
                <SingleSosomon src={Ox}/>
                <SingleSosomon src={Pig}/>
                <SingleSosomon src={Rabbit}/>
                <SingleSosomon src={Zebra}/>
            </View>
        </View>
    )
}

export default SosomonDictionary;
