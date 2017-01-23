package com.kkna.matching.matching;

import android.view.View;
import android.widget.LinearLayout;
/**
 * Option && GroupComponent interface
 *
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */
public interface MatchingComponent {

    /**
     * M.C params setting
     * @param LinearLayout.layoutParams
     * @return void
     */
    public void viewSetting(LinearLayout.LayoutParams params);

    /**
     * data to packet string
     * @param void
     * @return processed packet string
     */
    public String getPacketData();

    public View getView();

    /**
     *  get class name (option or M.C.C)
     * @param void
     * @return processed packet string
     */
    public String getName();


    public void setPriority(int priority);
    public int getPriority();
}
