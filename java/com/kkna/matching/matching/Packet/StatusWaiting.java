package com.kkna.matching.matching.Packet;

/**
 * Created by Kim on 2017-01-04.
 */
public class StatusWaiting implements Status {
    public String toString(Packet self){
        return new String("Waiting");
        //return self.getMatchingComponent().getPacketData();
    }
}
