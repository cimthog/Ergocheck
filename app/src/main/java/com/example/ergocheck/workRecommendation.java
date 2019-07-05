package com.example.ergocheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

public class workRecommendation extends AppCompatActivity {

    RadioGroup brkgrp, levgrp, posgrp;
    private static CheckBox nilCom, headCom, fatCom, musCom, necBtn, shldrBtn, lowArmBtn, uprArmBtn, backBtn, waistBtn,wristBtn, nilBtn;
    Button submit;
    public static int brkPos, levPos, posturePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_recommendation_button);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Workers Assessment");
        actionBar.setDisplayHomeAsUpEnabled(true);

        brkgrp = (RadioGroup) findViewById(R.id.brkgrp);
        levgrp = (RadioGroup) findViewById(R.id.levgrp);
        posgrp = (RadioGroup) findViewById(R.id.posgrp);

        nilCom = (CheckBox) findViewById(R.id.nilCom);
        headCom = (CheckBox) findViewById(R.id.headCom);
        fatCom = (CheckBox) findViewById(R.id.fatCom);
        musCom = (CheckBox) findViewById(R.id.musCom);
        necBtn = (CheckBox) findViewById(R.id.necBtn);
        shldrBtn = (CheckBox) findViewById(R.id.shldrBtn);
        lowArmBtn = (CheckBox) findViewById(R.id.LowArmBtn);
        uprArmBtn = (CheckBox) findViewById(R.id.uprArmBtn);
        backBtn = (CheckBox) findViewById(R.id.backBtn);
        waistBtn = (CheckBox) findViewById(R.id.waistBtn);
        wristBtn = (CheckBox) findViewById(R.id.wristBtn);
        nilBtn = (CheckBox) findViewById(R.id.nilBtn);

        submit = (Button) findViewById(R.id.submitBtn);

        brkgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (i ==  R.id.brkYes)
                        workRecommendation.brkPos = 0;
                    else if (i == R.id.brkNo)
                        workRecommendation.brkPos = 1;
            }
        });

        levgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i ==  R.id.levYes)
                    workRecommendation.levPos = 0;
                else if (i == R.id.levNo)
                    workRecommendation.levPos = 1;

                }
        });


        posgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i ==  R.id.posSit)
                    workRecommendation.posturePos = 0;
                else if (i == R.id.posStand)
                    workRecommendation.posturePos = 1;
                else if (i == R.id.posBend)
                    workRecommendation.posturePos = 2;
                }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Assessment_popUp.class));

            }
        });
        nilCom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (nilCom.isChecked()) {
                    fatCom.setEnabled(false);
                    musCom.setEnabled(false);
                    headCom.setEnabled(false);
                }
                else {
                    fatCom.setEnabled(true);
                    musCom.setEnabled(true);
                    headCom.setEnabled(true);
                }
            }
        });
        nilBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if  (nilBtn.isChecked()) {
                    necBtn.setEnabled(false);
                    shldrBtn.setEnabled(false);
                    lowArmBtn.setEnabled(false);
                    uprArmBtn.setEnabled(false);
                    backBtn.setEnabled(false);
                    waistBtn.setEnabled(false);
                    wristBtn.setEnabled(false);
                }
                else {
                    necBtn.setEnabled(true);
                    shldrBtn.setEnabled(true);
                    lowArmBtn.setEnabled(true);
                    uprArmBtn.setEnabled(true);
                    backBtn.setEnabled(true);
                    waistBtn.setEnabled(true);
                    wristBtn.setEnabled(true);
                }
            }
        });
    }

    public static int breakNum(int i) {
        int breakValue = 0;
        if (i == 0)
            breakValue = 1;
        else if (i == 1)
            breakValue = 2;
        return breakValue;
    }

    public static int leaveNum(int i){
        int leaveValue = 0;
        if (i == 0)
            leaveValue = 0;
        else if (i == 1)
            leaveValue = 2;
        return leaveValue;
    }
    public static int postureNum(int i) {
      int postureValue = 0;
     if (i == 0)
         postureValue = 1;
     else if (i == 1)
         postureValue = 2;
     else if (i == 2)
         postureValue = 3;
    return postureValue;
    }


    public static int discomfortValue () {
        int disVal = 0;
        if (headCom.isChecked())
            disVal = 1;
        if (fatCom.isChecked())
            disVal = 2;
        if (musCom.isChecked())
            disVal = 2;
        if (headCom.isChecked() && fatCom.isChecked())
            disVal = 3;
        if (headCom.isChecked() && musCom.isChecked())
            disVal = 3;
        if (fatCom.isChecked() && musCom.isChecked())
            disVal = 4;
        if (headCom.isChecked() && fatCom.isChecked() && musCom.isChecked())
            disVal = 5;

        return disVal;
    }
    public static int painNum (){
        int painValue = 0;
        if (necBtn.isChecked())
            painValue = 1;
        if (shldrBtn.isChecked())
            painValue = 1;
        if (lowArmBtn.isChecked())
            painValue = 1;
        if (uprArmBtn.isChecked())
            painValue = 1;
        if (backBtn.isChecked())
            painValue = 2;
        if (waistBtn.isChecked())
            painValue = 2;
        if (wristBtn.isChecked())
            painValue = 1;
        if (necBtn.isChecked() && shldrBtn.isChecked())
            painValue = 2;
        if (necBtn.isChecked() && lowArmBtn.isChecked())
            painValue = 2;
        if (necBtn.isChecked() && uprArmBtn.isChecked())
            painValue = 2;
        if (necBtn.isChecked() && backBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && waistBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && wristBtn.isChecked())
            painValue = 2;
        if (shldrBtn.isChecked() && lowArmBtn.isChecked())
            painValue = 2;
        if (shldrBtn.isChecked() && uprArmBtn.isChecked())
            painValue = 2;
        if (shldrBtn.isChecked() && backBtn.isChecked())
            painValue = 3;
        if (shldrBtn.isChecked() && waistBtn.isChecked())
            painValue = 3;
        if (shldrBtn.isChecked() && wristBtn.isChecked())
            painValue = 2;
        if (lowArmBtn.isChecked() && uprArmBtn.isChecked())
            painValue = 2;
        if (lowArmBtn.isChecked() && backBtn.isChecked())
            painValue = 3;
        if (lowArmBtn.isChecked() && waistBtn.isChecked())
            painValue = 3;
        if (lowArmBtn.isChecked() && wristBtn.isChecked())
            painValue = 2;
        if (uprArmBtn.isChecked() && backBtn.isChecked())
            painValue = 3;
        if (uprArmBtn.isChecked() && waistBtn.isChecked())
            painValue = 3;
        if (uprArmBtn.isChecked() && wristBtn.isChecked())
            painValue = 2;
        if (backBtn.isChecked() && waistBtn.isChecked())
            painValue = 4;
        if (backBtn.isChecked() && wristBtn.isChecked())
            painValue = 4;
            if (waistBtn.isChecked() && wristBtn.isChecked())
                painValue = 3;
        if (necBtn.isChecked() && shldrBtn.isChecked() && lowArmBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && shldrBtn.isChecked() && uprArmBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && shldrBtn.isChecked() && backBtn.isChecked() )
            painValue = 4;
        if (necBtn.isChecked() && shldrBtn.isChecked() && waistBtn.isChecked())
            painValue = 4;
        if (necBtn.isChecked() && shldrBtn.isChecked() && wristBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && lowArmBtn.isChecked() && uprArmBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && lowArmBtn.isChecked() && backBtn.isChecked())
            painValue = 4;
        if (necBtn.isChecked() && lowArmBtn.isChecked() && waistBtn.isChecked() )
            painValue = 4;
        if (necBtn.isChecked() && lowArmBtn.isChecked() && wristBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && uprArmBtn.isChecked() && backBtn.isChecked())
            painValue = 4;
        if (necBtn.isChecked() && uprArmBtn.isChecked() && backBtn.isChecked())
            painValue = 4;
        if (necBtn.isChecked() && uprArmBtn.isChecked() && waistBtn.isChecked())
            painValue = 4;
        if (necBtn.isChecked() && uprArmBtn.isChecked() && backBtn.isChecked() )
            painValue = 4;
        if (necBtn.isChecked() && shldrBtn.isChecked() && waistBtn.isChecked())
            painValue = 4;
        if (necBtn.isChecked() && shldrBtn.isChecked() && wristBtn.isChecked())
            painValue = 3;
        if (necBtn.isChecked() && shldrBtn.isChecked() && lowArmBtn.isChecked() && uprArmBtn.isChecked() && backBtn.isChecked() && waistBtn.isChecked() && wristBtn.isChecked())
            painValue = 9;



        return painValue;
    }

}


