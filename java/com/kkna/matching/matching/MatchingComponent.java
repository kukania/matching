package com.kkna.matching.matching;

import android.view.View;

/**
 * Option && GroupComponent interface
 *
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.03.2017 make optionComponent
 * @see    None
 */
public interface MatchingComponent {
    /**
     * data to packet string
     *
     * @param void
     * @return processed packet string
     */
    public String getPacketData();
    public View getView();
    public String getName();
    public void setPriority(int priority);
    public int getPriority();
}
