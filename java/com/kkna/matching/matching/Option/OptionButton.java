package com.kkna.matching.matching.Option;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by angks on 2017-01-03.
 */
public class OptionButton extends Option {
    private String LOGT="BTN component";
    private LinearLayout parent;
    private Button button=null;
    private Context context=null;
    private LinearLayout.LayoutParams basicParams;
    private int data;

    public boolean check=false;
    public OptionButton(String text, LinearLayout parent , Context mycontext){
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
    public void setData(int input){data=input;}
    public void textSetting(String text){
        button.setText(text);
    }
    @Override
    public void viewSetting(LinearLayout.LayoutParams params){
        if(button!=null){
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

}
