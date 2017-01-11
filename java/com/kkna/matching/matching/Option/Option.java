package com.kkna.matching.matching.Option;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;

/**
 * Option for GroupComponent
 *
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.03.2017 make optionComponent
 * @see
 */
public abstract class Option implements MatchingComponent {
    protected boolean check;
    protected int priority;
    public void setPriority(int p){priority=p;}
    public int getPriority(){return priority;}
    public String getName(){
        return "Option";
    }
    public abstract void viewSetting(LinearLayout.LayoutParams params);
}
