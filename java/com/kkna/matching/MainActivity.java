package com.kkna.matching;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kkna.matching.matching.Communication;
import com.kkna.matching.matching.Packet;
import com.kkna.matching.matching.Status;
import com.kkna.matching.matching.StatusComplete;
import com.kkna.matching.matching.StatusFactory;

import java.util.HashMap;
=======
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.OptionFactory;
>>>>>>> 17bc712b16d85c5564c728e4c10065162b7981cc

public class MainActivity extends Activity {
    LinearLayout body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Main", "Push btn");
                Packet p4 = new Packet();
                p4.test = "p4";
                Communication.getInstance().pushPacket(p4);
            }
        });

        test();
    }

    private void test(){
        Log.d("test", "test start!!");
        Packet p1 = new Packet();
        p1.test = "p1";
        Packet p2 = new Packet();
        p2.test = "p2";
        Packet p3 = new Packet();
        p3.test = "p3";
        Communication.getInstance().pushPacket(p1);
        Communication.getInstance().pushPacket(p2);
        Communication.getInstance().pushPacket(p3);
=======
        body=(LinearLayout)findViewById(R.id.body);
        MatchingComponent test=OptionFactory.creator("Button","test",body,this.getApplicationContext());
>>>>>>> 17bc712b16d85c5564c728e4c10065162b7981cc
    }
}
