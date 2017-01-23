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
import com.kkna.matching.matching.MatchingData;
import java.util.ArrayList;

/**
 *
 *  Button option class
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */
public class OptionButton extends Option {
    public Drawable normal;
    public Drawable select;
    public boolean check=false;

    /**
     *  constructor
     *  make new button and set the Layout param like
     *  weight = 1.0f , gravity=center
     * @param String text, LinearLayout parent, Context context
     * @return processed packet string
     */
    public OptionButton(String text, LinearLayout parent , Context mycontext){
        listeners=new ArrayList<View.OnClickListener>();
        context=mycontext;
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

    /**
     *  add onclick listener for itself
     * @param void
     * @return processed packet string
     */
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
    public void setData(MatchingData input){data=input;}
    @Override
    public MatchingData getData(){return data;}
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
            if(usingData)
                return ""+data.makeString();
            else
                return ""+getPriority();
        }
        return null;
    }
    public void setUseData(){usingData=true;}
    @Override
    public View getView() {
        return button;
    }

    //listener array. it has all listener make event call this.
    private ArrayList<View.OnClickListener> listeners;
    private String LOGT="BTN component";
    //this is option view
    private Button button=null;
    private Context context=null;
    private LinearLayout.LayoutParams basicParams;

    //this is option data
    private MatchingData data;

    //check for usage of data;
    private boolean usingData = false;
}
