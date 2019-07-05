package com.example.ergocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by stephen on 1/25/2016.
 */

public class splash extends Activity {


    private static int SPLASH_TIME_OUT = 2500;
    LinearLayout iconLayout;
    //DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()) {
            finish();
            return;
        }

//        db = new DbHelper(this);
//        db.deleteTable();
//        addQuestions();




        setContentView(R.layout.splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }








        iconLayout = (LinearLayout) findViewById(R.id.iconLayout);
        iconLayout.clearAnimation();



        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
         iconLayout.setVisibility(View.VISIBLE);

    new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {


                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
            }
        }, SPLASH_TIME_OUT);
    }


//    void addQuestions(){
//        db.addQuestion(new Question("What discomfort do you normally experience as a result of production operation?",
//                "Body Pains",
//                "Muscle Pains",
//                "Fatigue",
//                "Headache",
//                "",
//                "",
//                "",
//                "",
//
//                "Pains may be as a result of working posture and performance of series of operations task at a time.\n\n" +
//                        "Recommendation:\n" +
//                        " If need be, see a doctor.\n"+
//                        " Ensure you maintain a good and proper working posture.",
//
//                "Pain may be as a result of work posture and awkward movement may cause muscle strain\n\n" +
//                        "Recommendation:\n" +
//                        " Stretching and exercise is needed.",
//
//                "it is caused by long shifts without adequate rest or break resulting into weariness and decreasing the efficiency of performance of individual.\n\n" +
//                        "Recommendation:\n" +
//                        " Avoid working for a longer hour without break.\n" +
//                        " Adequate rest is required.",
//
//                "It is as a result of fatigue.\n\n" +
//                        "Recommendation:\n" +
//                        " Adequate and proper resting is necessary.\n" +
//                        " If need be, see a doctor.\n",
//
//                "", "","", "", "4"), "QUESTIONS");
//
//
//        db.addQuestion(new Question(" Which part of the body do you frequently experience pain?",
//                "Neck",
//                "Wrist",
//                "Lower Arm",
//                "Upper Arm",
//                "Back pain",
//                "Shoulder pain",
//                "Waist",
//                "",
//
//                "Pain may be as a result of neck position while performing task.\n\n " +
//                        "Recommendation:\n" +
//                        " If work operation require twisting or bending of neck.\n" +
//                        " a minute of stretching is necessary before performing the next task.",
//
//                "Pain may be as a result of weight of the product carried.\n\n" +
//                        "Recommendation:\n" +
//                        " Weight above 20kg must be avoided.\n" +
//                        " Proper and gentle twist of the wrist in to and fro movement.",
//
//                "it may be as a result of muscle strain, poor posture e.t.c\n\n" +
//                        "Recommendation:\n" +
//                        " stretching and exercise is required by turning of arm and the wrist to where the palm is faced downward.",
//
//                "Pain is caused by position of the shoulder and also when the body is leaning too much on it.\n\n"+
//                        "Recommendation:\n" +
//                        " Exercise and stretching may be necessary and also use of arm rest is needed.",
//
//
//                "Pain maybe due to too much bending for a longer period of time and also as a result of not using back rest.\n\n" +
//                        "Recommendation:\n" +
//                        " Observe a minute stretch for every three (3) minutes of bending.\n" +
//                        " Avoid bending for a longer period.\n" +
//                        " If need be see your doctor.",
//
//                " Pain may be as a result of overload i.e carrying load more than 20kg and also if the shoulder is raised for a longer period while performing task.\n\n" +
//                        "Recommendation:\n" +
//                        " Avoid overweight or overload.\n" +
//                        " Stretching the shoulder is necessary.",
//
//                "Sitting too long while performing task without break or rest usually cause waist pains.\n\n" +
//                        "Recommendation:\n" +
//                        " Avoid sitting for a longer period without rest.\n" +
//                        " If need be, see a doctor.",
//                "", "7"), "QUESTIONS");
//    }



}


