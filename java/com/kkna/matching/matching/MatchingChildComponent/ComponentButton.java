package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.kkna.matching.matching.Option.OptionFactory;

/**
 * Created by angks on 2017-01-11.
 */
public class ComponentButton extends MatchingChildComponent{
    private Context myContext;
    public ComponentButton(Context context,int ButtonNum){
        super(context);
        myContext=context;
        for(int i=0; i<ButtonNum; i++)
            this.add(OptionFactory.creator("Button",i+"",this.body,myContext));
    }
    public void handlerSetting(View.OnClickListener listener){
        for(int i=0; i<childList.size();i++){
            childList.get(i).getView().setOnClickListener(listener);
        }
    }
    public void textSetting(int index,String text){

    }
    public void imgSetting(int index, Drawable src){

    }
}
