package com.example.ergocheck;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by USER on 3/4/2018.
 */

public class popup_window extends Activity {

    private TextView textA, textB, textC, textR, r_score,actText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;



        getWindow().setLayout((int)(width*.9),(int)(height*.5));

        String a = String.valueOf(fragmentA.postureA());
        String b = String.valueOf(fragmentB.postureB());
        String c = String.valueOf(fragmentB.scoreC());
        String d = "";
        String activityScore = String.valueOf(fragmentB.checkNum());

        textA = (TextView) findViewById(R.id.txtA);
        textB = (TextView) findViewById(R.id.txtB);
        textC = (TextView) findViewById(R.id.txtC);
        textR = (TextView) findViewById(R.id.txtR);
        r_score = (TextView) findViewById(R.id.r_score);
        actText = (TextView) findViewById(R.id.actScore);

        int valueOfC = Integer.valueOf(c);
        int actyScore = Integer.valueOf(activityScore);
        int r = valueOfC + actyScore;
        String rr = String.valueOf(r);
        if (valueOfC <= 1)
            d = "Negligible Risk";
        else if ((r == 2) || (r == 3) )
            d = "Low risk, change may be needed";
        else if ((r >= 4) && (r <= 7))
            d = "Medium risk, further investigation, change soon";
        else if ((r >= 8)&& (r <= 10))
            d = "High risk, investigate and implement change";
        else if ((r >= 11))
            d = "Very high risk, implement change";




        textA.setText(a);
        textB.setText(b);
        textC.setText(c);
        textR.setText(d);
        r_score.setText(rr);
        actText.setText(activityScore);
    }

}
