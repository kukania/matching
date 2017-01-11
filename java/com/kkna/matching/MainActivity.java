package com.kkna.matching;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.Communication;
import com.kkna.matching.matching.MatchingChildComponent.ComponentButton;
import com.kkna.matching.matching.MatchingChildComponent.ComponentFactory;
import com.kkna.matching.matching.MatchingChildComponent.MatchingChildComponent;
import com.kkna.matching.matching.Packet;
import com.kkna.matching.matching.Status;
import com.kkna.matching.matching.StatusComplete;
import com.kkna.matching.matching.StatusFactory;

import java.util.HashMap;

public class MainActivity extends Activity {
    String LOGT="MAINACTIVITY";
    LinearLayout body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
