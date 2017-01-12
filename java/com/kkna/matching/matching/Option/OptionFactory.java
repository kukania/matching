package com.kkna.matching.matching.Option;

import android.content.Context;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;

/**
 * Created by angks on 2017-01-03.
 */
public class OptionFactory {
    public static MatchingComponent creator(String input, String text,LinearLayout parent,Context context){
        if(input.equals("Button")){
            return new OptionButton(text,parent,context);
        }
        return null;
    }
}
