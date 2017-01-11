package com.kkna.matching.matching;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by Kim on 2017-01-04.
 */
public class StatusFactory {
    private static HashMap<Character, Status> map = new HashMap<Character, Status>();

    public static Status getStatus(char key){
        if(map.get(key) == null){
            switch(key){
                case 'a':map.put('a', new StatusAJAX());
                    break;
                case 'c':map.put('c', new StatusComplete());
                    break;
                case 'w':map.put('w',new StatusWating());
                    break;
                default:
                    Log.e("StatusFactory", "invalid key");
                    break;
            }
        }
        return map.get(key);
    }
}
