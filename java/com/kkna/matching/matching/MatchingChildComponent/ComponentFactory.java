package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;

import com.kkna.matching.matching.MatchingComponent;

/**
 * Created by angks on 2017-01-11.
 */

public class ComponentFactory {
    static public MatchingComponent creator(String input, Context context, int size){
        if(input.equals("Button")){
            return new ComponentButton(context,size);
        }
        return null;
    }
}
