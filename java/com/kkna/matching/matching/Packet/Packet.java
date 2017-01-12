package com.kkna.matching.matching.Packet;

import android.content.Context;
import android.util.Log;

import com.kkna.matching.matching.MatchingComponent;

/**
 * Created by Kim on 2017-01-04.
 */

public class Packet {
    private Status PacketStatus;
    private MatchingComponent matchingComponent;
    private PacketCallback callback;

    public MatchingComponent getMatchingComponent(){
        return matchingComponent;
    }

    public Packet(MatchingComponent mc){
        PacketStatus = StatusFactory.getStatus('w');
        this.matchingComponent = mc;
        callback = null;
        if(mc == null){
            Log.e("Matching error", "Packet Constructor's parameter is null.");
            return;
        }


    }

    public void send(){
        PacketSender sender = PacketSender.getInstance();
        sender.sendPacket(this);
    }

    public String toString(){
        return PacketStatus.toString(this);
    }

    public void setCallback(PacketCallback packetCallback){
        callback = packetCallback;
    }

    public void callback(){
        if(PacketStatus == StatusFactory.getStatus('w') && callback != null){
            callback.callbackMethod();
        }
    }
}
