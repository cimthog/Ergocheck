package com.example.ergocheck;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import static com.example.ergocheck.workRecommendation.breakNum;
import static com.example.ergocheck.workRecommendation.brkPos;
import static com.example.ergocheck.workRecommendation.discomfortValue;
import static com.example.ergocheck.workRecommendation.leaveNum;
import static com.example.ergocheck.workRecommendation.levPos;
import static com.example.ergocheck.workRecommendation.painNum;
import static com.example.ergocheck.workRecommendation.postureNum;
import static com.example.ergocheck.workRecommendation.posturePos;

public class Assessment_popUp extends Activity {

    TextView levtxt,brktxt,postxt,paintxt,distxt,totattxt,remark;
    String f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.5));

        levtxt = (TextView) findViewById(R.id.levtxt);
        brktxt = (TextView) findViewById(R.id.brktxt);
        postxt = (TextView) findViewById(R.id.postxt);
        paintxt = (TextView) findViewById(R.id.paintxt);
        distxt = (TextView) findViewById(R.id.distxt);
        totattxt = (TextView) findViewById(R.id.totaltxt);
        remark = (TextView) findViewById(R.id.remark);

        String breakValue = String.valueOf(breakNum(brkPos));
        String leaveValue = String.valueOf(leaveNum(levPos));
       String postureValue = String.valueOf(postureNum(posturePos));
        String comValue = String.valueOf(discomfortValue());
        String painValue = String.valueOf(painNum());

        int a = Integer.valueOf(breakValue);
        int b = Integer.valueOf(leaveValue);
        int c = Integer.valueOf(postureValue);
        int d = Integer.valueOf(comValue);
        int e = Integer.valueOf(painValue);
        int total = a+b+c+d+e;
        String tot = String.valueOf(total);

        levtxt.setText(leaveValue);
        brktxt.setText(breakValue);
        postxt.setText(postureValue);
        paintxt.setText(painValue);
        distxt.setText(comValue);
        totattxt.setText(tot);

        if (total <= 1)
            f = "Negligible Risk";
        else if ((total == 2) || (total == 3) )
            f = "Low risk, change may be needed";
        else if ((total >= 4) && (total <= 7))
            f = "Medium risk, further investigation, change soon";
        else if ((total >= 8)&& (total <= 10))
            f = "High risk, investigate and implement change";
        else if ((total >= 11))
            f = "Very high risk, implement change";

        remark.setText(f);
    }
}
