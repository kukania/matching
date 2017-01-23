package com.kkna.matching.matching.MatchingChildComponent;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.Option;
import com.kkna.matching.matching.Option.OptionButton;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 *  Matching child Component as M.C.C
 *  this is the group of option and developer's unit
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */

public abstract class MatchingChildComponent implements MatchingComponent {
    //orientation for MCC
    public enum orientation {HORIZONTAL, VERTICAL}

    //if this valu is true, then all it's child component activate at it's parents event call except MCC child
    public boolean extendsEvents=false;

    @Override
    public View getView() {
        notifyView();
        return body;
    }

    @Override
    public String getName() {
        return "MatchingChildComponent";
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void viewSetting(LinearLayout.LayoutParams params) {
        body.setLayoutParams(params);
    }

    public int getOrientation() {
        return body.getOrientation();
    }


    /**
     * this method make it's all child implement them views
     * return false= no view of child has no view
     * retur true = has view
     * @param void
     * @return boolean
     */
    public boolean notifyView() {
        if (body == null) {
            Log.d(LOGT, "body null");
            return false;
        }
        for (int i = 0; i < childList.size(); i++) {
            if (childList.get(i) == null) {
                Log.d(LOGT, "Child " + i + " null");
                return false;
            }
            body.addView(childList.get(i).getView());
        }
        return true;
    }

    /**
     * swap the child and view
     *
     * @param int index1, int index2
     * @return boolean
     */
    public boolean swap(int in1, int in2) throws NullPointerException {
        if (childList.get(in1) != null && childList.get(in2) != null) {
            MatchingComponent temp = childList.get(in1);
            Collections.swap(childList, in1, in2);
            return notifyView();
        } else {
            throw new NullPointerException("null");
        }
    }

    /**
     * remove child using MC. no change view. to Change view, should call notifyview
     * @param MatchingComponent view
     * @return boolean
     */
    public boolean remove(MatchingComponent view) {
        return childList.remove(view);
    }


    /**
     * remove child using index. no change view. to Change view, should call notifyview
     * @param int index
     * @return boolean
     */
    public boolean remove(int index) {
        MatchingComponent t = childList.remove(index);
        if (t == null) return true;
        else return false;
    }


    /**
     * remove child using index then pull the priority. no change view. to Change view, should call notifyview
     * @param int index
     * @return boolean
     */
    public boolean removeAdjustPriority(int index) {
        MatchingComponent t = childList.get(index);
        if (t != null) {
            for (int i = index + 1; i < childList.size(); i++) {
                childList.get(i).setPriority(childList.get(i - 1).getPriority());
            }
            childList.remove(index);
            return true;
        } else return false;
    }

    /**
     * add MC
     * @param MatchingComponent
     * @return boolean
     */
    public boolean add(MatchingComponent view){
        view.setPriority(1 << childList.size());
        childList.add(view);
        return true;
    }

    /**
     * make param belonging to this with param's child extend this event
     * @param MatchingChildComponent View
     * @return boolean
     */
    public abstract boolean addComponent(MatchingChildComponent view) throws ClassCastException;

    public MatchingComponent get(int index) {
        return childList.get(index);
    }

    /**
     * change orientation this and this child
     * @param orientation
     * @return void
     */
    public abstract void changeOrientationAll(orientation a);

    /**
     * change orientation this not child
     * @param orientation
     * @return void
     */
    public abstract void changeOrientationOne(orientation a);


    /**
     * clean child event listener and listener list
     * after call this function, should call config again
     * @param void
     * @return void
     */
    public abstract void cleanChildListener();

    /**
     * the setting for specific each M.C.C
     * ex) setting all child having default event listener etc...
     * @param Object ... params
     * @return void
     */
    public abstract void config(Object... params);

    /**
     * constructor
     * @param int index
     * @return boolean
     */
    protected MatchingChildComponent(Context context, int orientation) {
        body = new LinearLayout(context);
        LinearLayout.LayoutParams params;
        if (orientation == LinearLayout.HORIZONTAL)
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        else
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        params.weight = 1;
        body.setLayoutParams(params);
        body.setOrientation(LinearLayout.HORIZONTAL);
        childList = new ArrayList<MatchingComponent>();
    }

    //eventee is the arrang for this MCC's event listener
    protected ArrayList<MatchingComponent> eventee;


    //listener list for eventee
    protected ArrayList listenerList;

    //childList is the list of this MCC's child
    protected ArrayList<MatchingComponent> childList;

    //body
    protected LinearLayout body;

    //priority
    protected int priority;


    private String LOGT = "MCC";
}
