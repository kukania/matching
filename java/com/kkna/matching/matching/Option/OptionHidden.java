package com.kkna.matching.matching.Option;

import android.view.View;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingData;

import java.util.Calendar;

/**
 * Created by angks on 2017-01-23.
 */
public class OptionHidden extends Option {
    @Override
    public void cleanListener() {
        return;
    }

    @Override
    public void setData(MatchingData p) {
        data=p;
    }

    @Override
    public MatchingData getData() {
        return data;
    }

    @Override
    public void viewSetting(LinearLayout.LayoutParams params) {
        return;
    }

    @Override
    public String getPacketData() {
        return data.makeString();
    }

    @Override
    public View getView() {
        return null;
    }

    public OptionHidden(String input){
        if(input.equals("Date")){
            data=new MatchingData() {
                @Override
                public String makeString() {
                    String res="";
                    res+=Calendar.getInstance().getTimeInMillis();
                    return res;
                }
            };
        }
        else{
            data=null;
        }
    }
    private MatchingData data;
}
