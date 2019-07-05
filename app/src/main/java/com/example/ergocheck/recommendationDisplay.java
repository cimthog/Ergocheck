package com.example.ergocheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;


public class recommendationDisplay extends Activity {


    public recommendationDisplay() {
    }

    TextView recommendationTextView;
    Button callDoctor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation_display);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar);

        init();

        callDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDoctorDialog();
            }
        });


    }

    void init(){
        recommendationTextView = (TextView)findViewById(R.id.recommendationTextView);
        callDoctor = (Button)findViewById(R.id.callDoctor);

        Intent intent = getIntent();
        String recommendationString = intent.getStringExtra("recommendationString");

        recommendationTextView.setText(recommendationString);

    }
    public void callDoctorDialog(){

        AlertDialog.Builder al =new AlertDialog.Builder(this);
        al.setMessage("Would you like to call our Doctor now ?");


        al.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();




            }
        });
        al.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Intent intent = new Intent (Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + "09024766294"));
                startActivity(intent);


            }
        });
        AlertDialog alyes=al.create();
        alyes.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Window view = ((AlertDialog) dialog).getWindow();
                // view.setBackgroundDrawableResource(R.drawable.rounded_button);

                Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setTypeface(Typeface.DEFAULT_BOLD);
                positiveButton.invalidate();

                Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setTypeface(Typeface.DEFAULT_BOLD);
                negativeButton.invalidate();
            }
        });
        alyes.show();



    }

}
















