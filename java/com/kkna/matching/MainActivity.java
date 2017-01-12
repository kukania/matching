package com.kkna.matching;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

<<<<<<< HEAD
import com.kkna.matching.matching.Packet.PacketCallback;
import com.kkna.matching.matching.Packet.PacketSender;
import com.kkna.matching.matching.Packet.Packet;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.OptionFactory;
import com.kkna.matching.matching.Packet.Status;
=======
import com.kkna.matching.matching.Communication;
import com.kkna.matching.matching.MatchingChildComponent.ComponentButton;
import com.kkna.matching.matching.MatchingChildComponent.ComponentFactory;
import com.kkna.matching.matching.MatchingChildComponent.MatchingChildComponent;
import com.kkna.matching.matching.Packet;
import com.kkna.matching.matching.Status;
import com.kkna.matching.matching.StatusComplete;
import com.kkna.matching.matching.StatusFactory;

import java.util.HashMap;
>>>>>>> 46ed576e3ecf82fde802e2a01aac6df00c29f03c

public class MainActivity extends Activity {
    String LOGT="MAINACTIVITY";
    LinearLayout body;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

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
=======
        body=(LinearLayout)findViewById(R.id.body);

        ComponentButton cpb=(ComponentButton) ComponentFactory.creator("Button",this,3);
        cpb.config("MultiSelect");
        cpb.handlerSetting(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test","hello");
            }
        });
        cpb.textSetting(0,"hello").textSetting(1,"world").textSetting(2,"fucking");
        body.addView(cpb.getView());
>>>>>>> 46ed576e3ecf82fde802e2a01aac6df00c29f03c
    }
}
