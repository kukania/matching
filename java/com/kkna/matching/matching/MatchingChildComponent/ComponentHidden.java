package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.MatchingData;
import com.kkna.matching.matching.Option.Option;
import com.kkna.matching.matching.Option.OptionFactory;

import java.util.ArrayList;

/**
 * Created by angks on 2017-01-23.
 */
public class ComponentHidden extends MatchingChildComponent {

    /**
     * constructor
     * @param context
     * @param orientation
     * @return boolean
     */
    public ComponentHidden(int orientation,Context context, int size) {
        super(context, orientation);
        this.body=null;
        for(int i=0; i<size; i++)
            childList.add(null);
        eventee=null;
    }

    public ComponentHidden valueSetting(int index, MatchingData data){
        try{
            ((Option)childList.get(index)).setData(data);
        }catch (ClassCastException e){
            Log.e(LOGT,"error casting");
        }
        return this;
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
        if(childList.size()<index+1){
            Log.e(LOGT,"index is over the child size");
            return;
        }
        String str=(String)params[1];
        childList.set(index,OptionFactory.creator("Hidden",str,null,null));
    }

    @Override
    public String getPacketData() {
        String res=getPriority()+":[";
        boolean nullChecker=true;
        for(MatchingComponent mc: childList){
            if(mc.getPacketData()==null)continue;
            nullChecker=false;
            if(mc.getName()=="Option"){
                res += "\"" + mc.getPacketData() + "\",";
            }
            else if (mc.getPacketData() != null) {
                res += "{";
                res += mc.getPacketData();
                res += "},";
            }
        }
        res = res.substring(0, res.length() - 1);
        res+="]";
        if(nullChecker)
            throw new NullPointerException();
        else
            return res;
    }

    private String LOGT="CPH";
}
