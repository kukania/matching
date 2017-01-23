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

import java.util.ArrayList;

/**
 *
 *  Button MCC
 *
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */

public class ComponentButton extends MatchingChildComponent {
    public boolean isMultiSelect;

    /**
     * Constructor
     * param is
     * orienatation and context and number of buttons
     *
     * and set the defualt - horizontal and single select
     * @param orientation,context,ButtonNum
     * @return
     */
    public ComponentButton(int orientation, Context context, int ButtonNum) {
        super(context, orientation);
        myContext = context;
        super.listenerList=new ArrayList();
        super.eventee = new ArrayList<>();
        for (int i = 0; i < ButtonNum; i++) {
            eventee.add(OptionFactory.creator("Button", i + "", this.body, myContext));
            this.add(eventee.get(eventee.size() - 1));
        }
        config("");
    }

    /**
     *
     * setting event to child
     * if param is null,after child' listener list clean, setting all listener
     * @param onCLickListener
     * @return
     */
    public void handlerSetting(View.OnClickListener listener) {
        if(listener!=null) {
            for (Object a : listenerList) {
                if (a == listener)
                    return;
            }
            listenerList.add(listener);
            for (MatchingComponent mc : eventee) {
                ((OptionButton) mc).addListener(listener);
            }
        }
        else{
            for (MatchingComponent mc : eventee) {
                ((OptionButton) mc).cleanListener();
                for(Object a: listenerList){
                    ((OptionButton) mc).addListener((View.OnClickListener) a);
                }
            }
        }
    }


    /**
     * textsetting
     * @param child index,String
     * @return this class
     */
    public ComponentButton textSetting(int index, String text) {
        ((Button) this.get(index).getView()).setText(text);
        return this;
    }

    /**
     * setting img
     * @param  child index,normal img, clicked img
     * @return this class
     */
    public ComponentButton imgSetting(int index, Drawable normal, Drawable selected) {
        ((OptionButton) this.get(index)).normal = normal;
        ((OptionButton) this.get(index)).select = selected;
        ((Button) this.get(index).getView()).setBackground(normal);
        return this;
    }

    /**
     * setting data
     * @param child index, Matching data
     * @return this class
     */
    public ComponentButton valueSetting(int index, MatchingData data) {
        ((Option) this.get(index)).setData(data);
        ((OptionButton) this.get(index)).setUseData();
        return this;
    }

    /**
     * setting priority
     * @param child index, priority
     * @return this class
     */
    public ComponentButton prioritySetting(int index, int priority) {
        ((MatchingComponent) this.get(index)).setPriority(priority);
        return this;
    }


    //option 1> singleselect 2> multiselect
    @Override
    public void config(Object... params) {
        String input = (String) params[0];
        for (MatchingComponent mc : eventee) {
            ((OptionButton) mc).cleanListener();
        }

        if (input.equals("MultiSelect")) {
            listenerList.clear();
            listenerList.add(MultiSelect);
            isMultiSelect = true;
            for (MatchingComponent mc : eventee) {
                ((OptionButton) mc).addListener(MultiSelect);
            }
        } else {
            isMultiSelect = false;
            listenerList.clear();
            listenerList.add(SingleSelect);
            for (MatchingComponent mc : eventee) {
                ((OptionButton) mc).addListener(SingleSelect);
            }
        }
    }


    private Context myContext;

    //single select event
    private View.OnClickListener SingleSelect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (MatchingComponent mc : eventee) {
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

    //multi select event
    private View.OnClickListener MultiSelect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (MatchingComponent mc : eventee) {
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
    public String getPacketData() throws NullPointerException {
        boolean nullChecker = true;
        if (extendsEvents)
            return null;
        String res = getPriority() + ":[";
        //eventee processing
        for (MatchingComponent mc : eventee) {
            if (mc.getPacketData() != null) {
                res += "\"" + mc.getPacketData() + "\",";
                nullChecker = false;
            }
        }
        //child processing
        for (MatchingComponent mc : childList) {
            try {
                if (mc.getName() == "Option") continue;
                else if (mc.getPacketData() != null) {
                    res += "{";
                    res += mc.getPacketData();
                    res += "},";
                    nullChecker = false;
                }
            }catch (NullPointerException e){

            }
        }
        res = res.substring(0, res.length() - 1);
        res += "]";
        if (nullChecker)
            throw new NullPointerException();
        else
            return res;
    }

    @Override
    public boolean addComponent(MatchingChildComponent view) throws ClassCastException {
        super.add(view);
        ComponentButton cp = (ComponentButton) view;
        cp.extendsEvents = true;
        cp.cleanChildListener();
        for (MatchingComponent mc : view.eventee) {
            try {
                eventee.add(mc);
            } catch (ClassCastException e) {

            }
        }
        handlerSetting(null);
        return true;
    }

    @Override
    public void cleanChildListener() {
        listenerList.clear();
        for (MatchingComponent mc : eventee) {
            try {
                ((Option) mc).cleanListener();
            } catch (ClassCastException e) {
                ((MatchingChildComponent) mc).cleanChildListener();
            }
        }
    }

    @Override
    public void changeOrientationAll(orientation a) {
        LinearLayout.LayoutParams params = null;
        if (a == orientation.HORIZONTAL) {
            if (body.getOrientation() == LinearLayout.HORIZONTAL)
                return;
            else {
                params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                params.weight = 1;
                body.setOrientation(LinearLayout.HORIZONTAL);
            }
        } else if (a == orientation.VERTICAL) {
            if (body.getOrientation() == LinearLayout.VERTICAL)
                return;
            else {
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                params.weight = 1;
                body.setOrientation(LinearLayout.VERTICAL);
            }
        }

        for (MatchingComponent mc : childList) {
            if (mc.getName() == "Option") {
                ((Option) mc).viewSetting(params);
            } else {
                ((MatchingChildComponent) mc).viewSetting(params);
                ((MatchingChildComponent) mc).changeOrientationAll(a);
            }
        }
        return;
    }

    @Override
    public void changeOrientationOne(orientation a) {
        LinearLayout.LayoutParams params;
        if (a == orientation.HORIZONTAL) {
            params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            body.setOrientation(LinearLayout.HORIZONTAL);
        } else {
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            params.weight = 1;
            body.setOrientation(LinearLayout.VERTICAL);
        }
        for (MatchingComponent mc : childList)
            mc.viewSetting(params);
    }

    private String LOGT = "CPB";
}

/***/
/*
* separate eventee, child
* eventee랑 child 구분을 확실히 해서 바꿔야됨
*
*
* */
/***/