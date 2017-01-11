package com.kkna.matching.matching.Option;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingChildComponent.MatchingChildComponent;

import java.util.ArrayList;

/**
 * Created by angks on 2017-01-03.
 */
public class OptionButton extends Option {
    public Drawable normal;
    public Drawable select;
    public boolean check=false;


    public OptionButton(String text, LinearLayout parent , Context mycontext){
        listeners=new ArrayList<View.OnClickListener>();
        context=mycontext; this.parent=parent;
        basicParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        if((((LinearLayout)parent).getOrientation())== LinearLayout.HORIZONTAL){
            basicParams.width=0;
           // Log.d(LOGT,"Horizontal");
        }
        else{
            basicParams.height=0;
            //Log.d(LOGT,"Vertical");
        }
        basicParams.weight=1.0f;
        basicParams.gravity= Gravity.CENTER;
        button=new Button(context);
        button.setLayoutParams(basicParams);
        textSetting(text);
        //Log.d(LOGT,button.getText().toString());
    }
    public void textSetting(String text){
        button.setText(text);
    }

    public void cleanListener(){this.listeners.clear();}
    public void addListener(final View.OnClickListener ilistener){
        listeners.add(ilistener);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(View.OnClickListener li:listeners)
                    li.onClick(v);
            }
        });
    }
    @Override
    public void setData(Object input){data=(int)input;}
    @Override
    public Object getData(){return data;}
    @Override
    public void viewSetting(LinearLayout.LayoutParams params){
        if(button==null){
            button=new Button(context);
        }
        button.setLayoutParams(params);
    }
    @Override
    public String getPacketData() {
        if(check){
            return ""+data;
        }
        return null;
    }

    @Override
    public View getView() {
        return button;
    }

    private ArrayList<View.OnClickListener> listeners;
    private String LOGT="BTN component";
    private LinearLayout parent;
    private Button button=null;
    private Context context=null;
    private LinearLayout.LayoutParams basicParams;
    private int data;

}
