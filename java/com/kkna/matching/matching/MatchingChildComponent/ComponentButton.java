package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.MatchingData;
import com.kkna.matching.matching.Option.Option;
import com.kkna.matching.matching.Option.OptionButton;
import com.kkna.matching.matching.Option.OptionFactory;

/**
 * Created by angks on 2017-01-11.
 */
public class ComponentButton extends MatchingChildComponent {
    public boolean isMultiSelect;

    public ComponentButton(int orientation, Context context, int ButtonNum) {
        super(context, orientation);
        myContext = context;
        for (int i = 0; i < ButtonNum; i++) {
            this.add(OptionFactory.creator("Button", i + "", this.body, myContext));
        }
        config("");
    }

    public void handlerSetting(View.OnClickListener listener) {
        for (int i = 0; i < childList.size(); i++) {
            ((OptionButton) childList.get(i)).addListener(listener);
        }
    }

    public ComponentButton textSetting(int index, String text) {
        ((Button) this.get(index).getView()).setText(text);
        return this;
    }

    public ComponentButton imgSetting(int index, Drawable normal, Drawable selected) {
        ((OptionButton) this.get(index)).normal = normal;
        ((OptionButton) this.get(index)).select = selected;
        ((Button) this.get(index).getView()).setBackground(normal);
        return this;
    }

    public ComponentButton valueSetting(int index, MatchingData data) {
        usingData = true;
        ((Option) this.get(index)).setData(data);
        return this;
    }

    public ComponentButton priority(int index, int data) {
        ((Option) this.get(index)).setPriority(data);
        return this;
    }


    @Override
    public void config(Object... params) {
        String input = (String) params[0];
        for (MatchingComponent mc : childList) {
            ((OptionButton) mc).cleanListener();
        }

        if (input.equals("MultiSelect")) {
            isMultiSelect = true;
            for (MatchingComponent mc : childList) {
                ((OptionButton) mc).addListener(MultiSelect);
            }
        } else {
            isMultiSelect = false;
            for (MatchingComponent mc : childList) {
                ((OptionButton) mc).addListener(SingleSelect);
            }
        }
    }

    private Context myContext;

    private View.OnClickListener SingleSelect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (MatchingComponent mc : childList) {
                OptionButton op = (OptionButton) mc;
                if (op.normal == null || op.select == null) continue;
                if (op.getView() == v && op.check == false) {
                    op.check = true;
                    ((Button) op.getView()).setBackground(op.select);
                } else {
                    op.check = false;
                    ((Button) op.getView()).setBackground(op.normal);
                }
            }
        }
    };
    private View.OnClickListener MultiSelect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (MatchingComponent mc : childList) {
                OptionButton op = (OptionButton) mc;
                if (op.normal == null || op.select == null) continue;
                if (op.check && op.getView() == v) {
                    op.check = false;
                    ((Button) op.getView()).setBackground(op.normal);
                } else if (op.getView() == v) {
                    op.check = true;
                    ((Button) op.getView()).setBackground(op.select);
                }
            }
        }
    };
    @Override
    public String getPacketData() {
        String res = getPriority() + ":";
        if (!usingData) {
            int sum = 0;
            for (MatchingComponent mc : childList) {
                if (mc.getPacketData() != null) {
                    sum += Integer.parseInt(mc.getPacketData());
                    if (!isMultiSelect) break;
                }
            }
            res +=sum;
        }
        else{
            for (MatchingComponent mc : childList) {
                if (mc.getPacketData() != null) {
                    res+=","+((OptionButton)mc).getData().makeString();
                    if (!isMultiSelect) break;
                }
            }
        }
        return res;
    }

    @Override
    public boolean addComponent(MatchingComponent view) throws ClassCastException {
        MatchingChildComponent mcc=(MatchingChildComponent)view;
        mcc.extendsEvents=true;
        cleanChildListener();
        /*need to change child listeners...*/
        childList.add(mcc);
        return true;
    }
    @Override
    public void cleanChildListener(){
        for(MatchingComponent mc:childList){
            try{
                ((Option)mc).cleanListener();
            }
            catch (ClassCastException e){
                ((MatchingChildComponent)mc).cleanChildListener();
            }
        }
    }

    @Override
    public void changeOrientationAll(orientation a){
        LinearLayout.LayoutParams params=null;
        if(a==orientation.HORIZONTAL){
            if(body.getOrientation()==LinearLayout.HORIZONTAL)
                return;
            else{
                params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                params.weight=1;
                body.setOrientation(LinearLayout.HORIZONTAL);
            }
        }
        else if(a==orientation.VERTICAL){
            if(body.getOrientation()==LinearLayout.VERTICAL)
                return;
            else{
                params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                params.weight=1;
                body.setOrientation(LinearLayout.VERTICAL);
            }
        }

        for (MatchingComponent mc: childList) {
            if(mc.getName()=="Option"){
                ((Option)mc).viewSetting(params);
            }
            else{
                ((MatchingChildComponent)mc).viewSetting(params);
                ((MatchingChildComponent)mc).changeOrientationAll(a);
            }
        }
        return;
    }

    @Override
    public void changeOrientationOne(orientation a) {
        LinearLayout.LayoutParams params;
        if(a==orientation.HORIZONTAL){
            params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight=1;
            body.setOrientation(LinearLayout.HORIZONTAL);
        }
        else{
            params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            params.weight=1;
            body.setOrientation(LinearLayout.VERTICAL);
        }
        for(MatchingComponent mc:childList)
            mc.viewSetting(params);
    }

    private String LOGT = "CPB";
    private boolean usingData = false;
}
