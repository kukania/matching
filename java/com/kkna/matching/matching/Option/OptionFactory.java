package com.kkna.matching.matching.Option;

import android.content.Context;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.MatchingData;

/**
 *
 *  Factory is to make Option class
 * <pre>
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */
public class OptionFactory {
    public static MatchingComponent creator(String input, String text,LinearLayout parent,Context context){
        if(input.equals("Button")){
            return new OptionButton(text,parent,context);
        }
        else if(input.equals("Hidden")){
            return new OptionHidden("Date");
        }
        return null;
    }
}
