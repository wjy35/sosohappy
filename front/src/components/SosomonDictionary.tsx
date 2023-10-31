import React from 'react';
import {View} from "react-native";
import SingleSosomon from "@/components/SingleSosomon";
import {type1, type2, type3, type4} from '@/assets/sosomon/index'

const SosomonDictionary = ({}) => {

    return (
        <View style={[{borderWidth: 1}]}>
            <View style={[{flexWrap: 'wrap', flexDirection: 'row', justifyContent: 'center'}]}>
                {
                    type1.map((el, idx)=>{
                        return (
                                <SingleSosomon src={el} key={`type1-${idx}`} />
                        )
                    })
                }
                {
                    type2.map((el, idx)=>{
                        return (
                          <SingleSosomon src={el} key={`type2-${idx}`} />
                        )
                    })
                }
                {
                    type3.map((el, idx)=>{
                        return (
                          <SingleSosomon src={el} key={`type3-${idx}`} />
                        )
                    })
                }
                <SingleSosomon src={type4[0]}/>
            </View>
        </View>
    )
}

export default SosomonDictionary;
