package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;

import com.kkna.matching.matching.Option.OptionHidden;

/**
 * Created by angks on 2017-01-23.
 */
public class ComponentHidden extends MatchingChildComponent {

    /**
     * constructor
     *
     * @param context
     * @param orientation
     * @return boolean
     */
    public ComponentHidden(int orientation,Context context, int size) {
        super(context, orientation);
        for(int i=0; i<size; i++)
            childList.add(null);
    }

    @Override
    public boolean addComponent(MatchingChildComponent view) throws ClassCastException {
        return false;
    }

    @Override
    public void changeOrientationAll(orientation a) {
        return;
    }

    @Override
    public void changeOrientationOne(orientation a) {
        return;
    }

    @Override
    public void cleanChildListener() {
        return;
    }

    @Override
    public void config(Object... params) {
        int index=(int)params[0];
        String str=(String)params[1];
        childList.set(index,new OptionHidden(str));
    }

    @Override
    public String getPacketData() {
        return null;
    }
}
