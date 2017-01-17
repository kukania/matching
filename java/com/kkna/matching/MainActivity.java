package com.kkna.matching;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkna.matching.matching.MatchingChildComponent.ComponentButton;
import com.kkna.matching.matching.MatchingChildComponent.ComponentFactory;
import com.kkna.matching.matching.MatchingChildComponent.MatchingChildComponent;

public class MainActivity extends Activity {
    String LOGT="MAINACTIVITY";
    LinearLayout body;
    Button testBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        body=(LinearLayout)findViewById(R.id.body);
        testBtn=(Button)findViewById(R.id.testBtn);

        final ComponentButton cpb=(ComponentButton) ComponentFactory.creator("Button",body.getOrientation(),this,3);
        for(int i=0; i<3; i++) cpb.imgSetting(i, ContextCompat.getDrawable(this, R.drawable.nbtn),ContextCompat.getDrawable(this, R.drawable.sbtn));

        cpb.textSetting(0,"hello").textSetting(1,"world").textSetting(2,"fucking");
        cpb.valueSetting(0,new Test()).valueSetting(1,new Test()).valueSetting(2,new Test());
        cpb.changeOrientationOne(MatchingChildComponent.orientation.HORIZONTAL);

        ComponentButton cpb2=(ComponentButton)ComponentFactory.creator("Button",cpb.getOrientation(),this,2);
        for(int i=0; i<2; i++)cpb2.imgSetting(i, ContextCompat.getDrawable(this, R.drawable.nbtn),ContextCompat.getDrawable(this, R.drawable.sbtn));
        cpb2.textSetting(0,"t").textSetting(1,"e");
        cpb2.changeOrientationOne(MatchingChildComponent.orientation.HORIZONTAL);
        cpb.addComponent(cpb2);

        cpb.changeOrientationAll(MatchingChildComponent.orientation.VERTICAL);
        body.addView(cpb.getView());

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGT,cpb.getPacketData());
            }
        });
    }
}
