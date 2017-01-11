package com.kkna.matching;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kkna.matching.matching.MatchingComponent;
import com.kkna.matching.matching.Option.OptionFactory;

public class MainActivity extends Activity {
    LinearLayout body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        body=(LinearLayout)findViewById(R.id.body);
        MatchingComponent test=OptionFactory.creator("Button","test",body,this.getApplicationContext());
    }
}
