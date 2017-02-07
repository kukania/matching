package com.kkna.matching.matching.Option;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.MatchingData;

/**
 *
 * implements M.C and show option to user as view and to developer as data
 * <pre>
 * <b>History:</b>
 *    kkna, 01.03.2017 make class first
 * </pre>
 *
 * @author KKNA
 * @version 01.23.2017 make method comment
 * @see    None
 */
public abstract class Option implements MatchingComponent {
    //check for usage
    protected boolean check;

    //if this class has not data, priority is used as data
    protected int priority;
    public void setPriority(int p){priority=p;}
    public int getPriority(){return priority;}
    public String getName(){
        return "Option";
    }

    /**
     *  clean all eventlistener for itself
     * @param void
     * @return void
     */
    public abstract void cleanListener();


    public abstract void setData(MatchingData p);
    public abstract MatchingData getData();
}
