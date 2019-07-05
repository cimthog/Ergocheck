package com.example.ergocheck;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Spinner workNatureSpinner,othersSpinner;
    Button workRecommendationButton,btn_reba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialisationAndSetUp();



    }

    void initialisationAndSetUp(){

        workRecommendationButton = (Button) findViewById(R.id.workRecommendationButton);
        btn_reba= (Button) findViewById(R.id.btn_reba);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        workNatureSpinner = (Spinner)findViewById(R.id.machineUsedSpinner);
        othersSpinner = (Spinner)findViewById(R.id.othersSpinner);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDoctorDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        machineSpinnerMethod();
        othersSpinnerMethod();

        workNatureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String workNature = workNatureSpinner.getSelectedItem().toString();

            if(!workNature.equalsIgnoreCase("-Select JOB/WORK nature-")){

                if(workNature.equalsIgnoreCase("Packing")){
                    Intent intent = new Intent(MainActivity.this, ViewStock.class);
                    intent.putExtra("machineUsed","Sealing machine");
                    intent.putExtra("number","1");
                    startActivity(intent);

                }
                else if(workNature.equalsIgnoreCase("Loading")){

                    Intent intent = new Intent(MainActivity.this, ViewStock.class);
                    intent.putExtra("machineUsed",workNature);
                    intent.putExtra("number","1");
                    startActivity(intent);
                }


            }






        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });


        othersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String othersString = othersSpinner.getSelectedItem().toString();


                    if(othersString.equalsIgnoreCase("REBA Assessment System")){
                        startActivity(new Intent(getApplicationContext(),reba.class));
                    }
                    else if(othersString.equalsIgnoreCase("Workers Assessment")){

                        Intent intent = new Intent(MainActivity.this, workRecommendation.class);
                        startActivity(intent);
                    }









            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        workRecommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, workRecommendation.class);
                startActivity(intent);
            }
        });

        btn_reba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),reba.class));
            }
        });
    }

    @Override
    protected void onResume() {

        workNatureSpinner.setSelection(0);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            exitApplicationMethod();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {

            Intent i = new Intent(MainActivity.this, about.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void machineSpinnerMethod() {


        List<String> categories = new ArrayList<String>();
        categories.add("--Select JOB/WORK nature--");
        categories.add("Packing");
        categories.add("Loading");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.customized_list_items, R.id.name,categories);
        dataAdapter.setDropDownViewResource(R.layout.customized_list_items);
        workNatureSpinner.setAdapter(dataAdapter);

    }

    private void othersSpinnerMethod() {


        List<String> categories = new ArrayList<String>();
        categories.add("--Select--");
        categories.add("REBA Assessment System");
        categories.add("Workers Assessment");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.customized_list_items, R.id.name,categories);
        dataAdapter.setDropDownViewResource(R.layout.customized_list_items);
        othersSpinner.setAdapter(dataAdapter);

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



    public void exitApplicationMethod(){

        AlertDialog.Builder al =new AlertDialog.Builder(this);
        al.setMessage("Would you like to exit ErgoCheck ?");


        al.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();




            }
        });
        al.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();


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
