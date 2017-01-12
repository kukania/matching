package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.Option;
import com.kkna.matching.matching.Option.OptionButton;
import com.kkna.matching.matching.Option.OptionFactory;

/**
 * Created by angks on 2017-01-11.
 */
public class ComponentButton extends MatchingChildComponent{
    public boolean isMultiSelect;
    public ComponentButton(Context context,int ButtonNum){
        super(context);
        myContext=context;
        for(int i=0; i<ButtonNum; i++){
            this.add(OptionFactory.creator("Button",i+"",this.body,myContext));
        }

    }
    public void handlerSetting(View.OnClickListener listener){
        for(int i=0; i<childList.size();i++){
            ((OptionButton)childList.get(i)).addListener(listener);
        }
    }
    public ComponentButton textSetting(int index,String text){
        ((Button)this.get(index).getView()).setText(text);
        return this;
    }
    public ComponentButton imgSetting(int index, Drawable normal, Drawable selected){
        ((OptionButton)this.get(index)).normal=normal;
        ((OptionButton)this.get(index)).select=selected;
        ((Button)this.get(index).getView()).setBackground(normal);
        return this;
    }
    public ComponentButton valueSetting(int index, int data){
        ((Option)this.get(index)).setData(data);
        return this;
    }
    public ComponentButton priority(int index, int data){
        ((Option)this.get(index)).setPriority(data);
        return this;
    }
    private Context myContext;

    @Override
    public void config(Object... params) {
        String input=(String)params[0];
        if(input.equals("MultiSelect")){
            isMultiSelect=true;
            for(MatchingComponent mc:childList){
                ((OptionButton)mc).addListener(MultiSelect);
            }
        }else{
            isMultiSelect=false;
            for(MatchingComponent mc:childList){
                ((OptionButton)mc).addListener(SingleSelect);
            }
        }
    }

    private View.OnClickListener SingleSelect=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(MatchingComponent mc: childList){
                OptionButton op=(OptionButton)mc;
                Log.d(LOGT,"singleselect");
                if(op.normal==null || op.select==null) continue;
                if(op.getView()==v && op.check==false){
                    op.check=true;
                    op.cleanListener();
                    ((Button)op.getView()).setBackground(op.select);
                }
                else{
                    op.check=false;
                    op.cleanListener();
                    ((Button)op.getView()).setBackground(op.normal);
                }
            }
        }
    };
    private View.OnClickListener MultiSelect=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            for(MatchingComponent mc: childList){
                Log.d(LOGT,"multiselect");
                OptionButton op=(OptionButton)mc;
                if(op.normal==null || op.select==null) continue;
                if(op.check){
                    op.check=false;
                    ((Button)op.getView()).setBackground(op.normal);
                }
                else{
                    op.check=true;
                    ((Button)op.getView()).setBackground(op.select);
                }
            }
        }
    };
    private String LOGT="CPB";

    @Override
    public String getPacketData() {
        String res=getPriority()+"";
        int sum=0;
        for(MatchingComponent mc:childList){
            if(mc.getPacketData()!=null){
                sum+=Integer.parseInt(mc.getPacketData());
                if(!isMultiSelect)break;
            }
        }
        res+=":"+sum;
        return res;
    }
}
