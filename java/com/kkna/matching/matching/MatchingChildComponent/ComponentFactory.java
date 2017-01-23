package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;

/**
 *
 * MCC factory
 * this is used by user
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */
public class ComponentFactory {
    static public MatchingComponent creator(String input, int orientation, Context context, int size){
        if(input.equals("Button")){
            return new ComponentButton(orientation,context,size);
        }
        else if(input.equals("Hidden")){
            return new ComponentHidden(orientation,context,size);
        }
        return null;
    }
}
