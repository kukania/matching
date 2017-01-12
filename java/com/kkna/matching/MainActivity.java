package com.kkna.matching;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kkna.matching.matching.Packet.PacketCallback;
import com.kkna.matching.matching.Packet.PacketSender;
import com.kkna.matching.matching.Packet.Packet;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.OptionFactory;
import com.kkna.matching.matching.Packet.Status;

public class MainActivity extends Activity {
    LinearLayout body;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tv);
        test();
    }

    private void test(){
        Log.d("test", "test start!!");

        MatchingComponent mc = null;
        Packet packet = new Packet(mc);
        packet.setCallback(new PacketCallback() {
            @Override
            public void callbackMethod() {
                textView.setText("Happy!!");
            }
        });
        packet.send();
    }
}
