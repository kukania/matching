package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.OptionButton;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by angks on 2017-01-11.
 */
public class MatchingChildComponent implements MatchingComponent{
    public enum orientation{HORIZONTAL,VERTICAL}
    @Override
    public String getPacketData() {
        String temp="",get="";
        for(int i=0; i<childList.size();i++){
            if((get=childList.get(i).getPacketData())!=null)
                temp+=get;
        }
        return temp;
    }
    @Override
    public View getView() {
        notifyView();
        return body;
    }

    @Override
    public String getName(){
        return "MatchingChildComponent";
    }

    @Override
    public void setPriority(int priority) {
        this.priority=priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    public boolean notifyView(){
        if(body==null){
            Log.d(LOGT,"body null");
            return false;
        }
        for(int i=0; i<childList.size();i++){
            if(childList.get(i)==null) {
                Log.d(LOGT,"Child "+i+" null");
                return false;
            }
            body.addView(childList.get(i).getView());
        }
        return true;
    }

    public boolean swap(int in1, int in2) throws NullPointerException{
        if(childList.get(in1)!=null && childList.get(in2)!=null){
            MatchingComponent temp=childList.get(in1);
            Collections.swap(childList,in1,in2);
            return notifyView();
        }
        else{
            throw new NullPointerException("null");
        }
    }

    public boolean remove(MatchingComponent view){
        return childList.remove(view);
    }

    public boolean remove(int index){
        MatchingComponent t=childList.remove(index);
        if(t==null) return true;
        else return false;
    }

    public boolean removeAdjustPriority(int index){
        MatchingComponent t=childList.get(index);
        if(t!=null){
            for(int i=index+1; i<childList.size();i++){
                childList.get(i).setPriority(childList.get(i-1).getPriority());
            }
            childList.remove(index);
            return true;
        }
        else return false;
    }

    public boolean add(MatchingComponent view){
        view.setPriority(childList.size());
        childList.add(view);
        return true;
    }
    public void changeOrientation(orientation a){
        LinearLayout.LayoutParams params;
        if(a==orientation.HORIZONTAL){
            if(body.getOrientation()==LinearLayout.HORIZONTAL)
                return;
            else{
                params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.weight=1;
                body.setLayoutParams(params);
                body.setOrientation(LinearLayout.HORIZONTAL);
                //change child view
            }
        }
        else if(a==orientation.VERTICAL){
            if(body.getOrientation()==LinearLayout.VERTICAL)
                return;
            else{
                params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0);
                params.weight=1;
                body.setLayoutParams(params);
                body.setOrientation(LinearLayout.VERTICAL);
                //change child view
            }
        }
        return;
    }
    protected MatchingChildComponent(Context context){
        body=new LinearLayout(context);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        body.setLayoutParams(params);
        body.setOrientation(LinearLayout.HORIZONTAL);
        childList=new ArrayList<MatchingComponent>();
    }
    protected ArrayList<MatchingComponent> childList;
    protected LinearLayout body;
    protected int priority;

    private String LOGT="MCC";
}
