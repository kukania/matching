package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;

/**
 * Created by angks on 2017-01-11.
 */

public class ComponentFactory {
    static public MatchingComponent creator(String input, int orientation, Context context, int size){
        if(input.equals("Button")){
            return new ComponentButton(orientation,context,size);
        }
        return null;
    }
}
