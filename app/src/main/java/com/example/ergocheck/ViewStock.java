package com.example.ergocheck;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewStock extends Activity {

    TabHost th;
    TextView tv, tv1, tv2;
    Spinner productModelSpinner;
    ListView ViewStockListView;
    GridView ViewStockGridview;
    ArrayList<ViewStockItems> arraylist11,arraylist12,arraylist13,arraylist14,arraylist15,arraylist31,arraylist32;
    ViewStockCustomAdapter adapter;
    SQLiteHandler db;
    String machineUsed,from;
    int number;
    TextView questionTextView;
    Button previousButton,nextButton;
    List<byte[]> productImage;
    public static String[] title11,title12,title13,title14,title15,title31,title32,recommendation11,recommendation12,recommendation13,recommendation14,recommendation15,recommendation31,recommendation32;
    List<byte[]> productPic = new ArrayList<>();
    public static String[] modelArray,brandArray,number_availableArray,priceArray;


    int[] image11 = {
            R.drawable.body_pain,
            R.drawable.muscle_pain,
            R.drawable.fatigue,
            R.drawable.headache
    };


    int[] image12 = {
            R.drawable.hour_5,
            R.drawable.hour_7,
            R.drawable.hour_9
    };

    int[] image13 = {
            R.drawable.sitting,
            R.drawable.standing,
            R.mipmap.bending
    };

    int[] image14 = {

            R.drawable.neck_pain,
            R.drawable.wrist_pain,
            R.mipmap.lower_hand,
            R.mipmap.upper_arm,
            R.mipmap.back_pain,
            R.drawable.shoulder_pain,
            R.drawable.waist_pain
    };

    int[] image15 = {
            R.drawable.yes,
            R.drawable.no,
    };


    int[] image31 = {
            R.drawable.kg20,
            R.drawable.kg40,
    };


    int[] image32 = {
            R.drawable.slip,
            R.mipmap.fall
    };
//    int[] image21 = {
//            R.drawable.neck_pain,
//            R.drawable.body_pain,
//            R.drawable.back_pain,
//    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stock);

        questionTextView = (TextView)findViewById(R.id.questionTextView);
        title11 = getResources().getStringArray(R.array.title11);
        title12 = getResources().getStringArray(R.array.title12);
        title13 = getResources().getStringArray(R.array.title13);
        title14 = getResources().getStringArray(R.array.title14);
        title15 = getResources().getStringArray(R.array.title15);

        title31 = getResources().getStringArray(R.array.title31);
        title32 = getResources().getStringArray(R.array.title32);

        recommendation11 = getResources().getStringArray(R.array.recommendation11);
        recommendation12 = getResources().getStringArray(R.array.recommendation12);
        recommendation13 = getResources().getStringArray(R.array.recommendation13);
        recommendation14 = getResources().getStringArray(R.array.recommendation14);
        recommendation15 = getResources().getStringArray(R.array.recommendation15);
        recommendation31 = getResources().getStringArray(R.array.recommendation31);
        recommendation32 = getResources().getStringArray(R.array.recommendation32);

        db = new SQLiteHandler(ViewStock.this);


        init();

        Intent intent = getIntent();
        machineUsed = intent.getStringExtra("machineUsed");
        from = intent.getStringExtra("from");
        number = Integer.parseInt(intent.getStringExtra("number"));

        if(number == 4 && from.equalsIgnoreCase("adapter")){
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
        }



        if(number == 5 && machineUsed.equalsIgnoreCase("Sealing machine")){
            nextButton.setVisibility(View.GONE);
        }
        else if(number == 1 && machineUsed.equalsIgnoreCase("Sealing machine")){
            previousButton.setVisibility(View.GONE);
        }


        if(number == 2 && machineUsed.equalsIgnoreCase("Loading")){
            nextButton.setVisibility(View.GONE);
        }
        else if(number == 1 && machineUsed.equalsIgnoreCase("Loading")){
            previousButton.setVisibility(View.GONE);
        }




        if(machineUsed.equalsIgnoreCase("Sealing machine")){
            if(number == 1){
                questionTextView.setText("What discomfort do you normally experience as a result of production operation?");
                PopulateStockListView11();
            }
            else if(number == 2){
                questionTextView.setText("What is the duration of operation per day?");
                PopulateStockListView12();
            }

            else if(number == 3){
                questionTextView.setText("What is the operation posture?");
                PopulateStockListView13();
            }

            else if(number == 4){
                questionTextView.setText("Which part of the body do you frequently experience pain?");
                PopulateStockListView14();
            }

            else if(number == 5){
                questionTextView.setText("Do you use drugs or analgesic before operation?");
                PopulateStockListView15();
            }
        }
//        else if(machineUsed.equalsIgnoreCase("Bottling machine")){
//            if(number == 1){
//                questionTextView.setText("Which part of the body do you frequently experience pain?");
//                PopulateStockListView21();
//            }


//            else if(number ==2){
//                PopulateStockListView21();
//            }
//        }

        else if(machineUsed.equalsIgnoreCase("Loading")) {
            if (number == 1) {
                questionTextView.setText("What is the weight of load you ofter carry?");
                PopulateStockListView31();
            }

            if (number == 2) {
                questionTextView.setText("What are the most accident encountered ?");
                PopulateStockListView32();
            }



        }


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               if(number == 1 && machineUsed.equalsIgnoreCase("Bottling machine")){
//
//                   Toast.makeText(ViewStock.this,"End of Question",Toast.LENGTH_SHORT).show();
//               }
//               else
//                if(number == 4 && !from.equalsIgnoreCase("adapter") ){
//                    number = 5;
//                }
                   if(number < 5 & machineUsed.equalsIgnoreCase("Sealing machine")){

                   Intent intent = new Intent(ViewStock.this,ViewStock.class);
                   intent.putExtra("machineUsed",machineUsed);

//                    && !from.equalsIgnoreCase("adapter")
                       if(number == 3 ){
//                           number = 5;
                           intent.putExtra("number",String.valueOf(5));
                       }
                       else  if(number != 3 ){
                           intent.putExtra("number",String.valueOf(number+1));
                       }

                   startActivity(intent);
                   finish();
               }

                else if(number == 5 && machineUsed.equalsIgnoreCase("Sealing machine")){

                   Toast.makeText(ViewStock.this,"End of Question",Toast.LENGTH_SHORT).show();

               }

               if(number < 2 && machineUsed.equalsIgnoreCase("Loading")){

                   Intent intent = new Intent(ViewStock.this,ViewStock.class);
                   intent.putExtra("machineUsed",machineUsed);
                   intent.putExtra("number",String.valueOf(number+1));
                   startActivity(intent);
                   finish();

               }
               else if(number == 3 && machineUsed.equalsIgnoreCase("Sealing machine")){

                   Toast.makeText(ViewStock.this,"End of Question",Toast.LENGTH_SHORT).show();

               }


            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(number <= 1){

                    Toast.makeText(ViewStock.this,"End of Question",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(ViewStock.this,ViewStock.class);
                    intent.putExtra("machineUsed",machineUsed);

//                    && !from.equalsIgnoreCase("adapter")
                    if(number == 5  ){
                        intent.putExtra("number",String.valueOf(3));
                    }
                    else{
                        intent.putExtra("number",String.valueOf(number-1));
                    }

                   // intent.putExtra("number",String.valueOf(number-1));
                    startActivity(intent);
                    finish();

                }

            }
        });
    }

    void PopulateStockListView11(){
        arraylist11 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title11.length ;i++){
            arraylist11.add(new ViewStockItems(title11[i],recommendation11[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image11[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist11);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    void PopulateStockListView12(){

        arraylist12 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title12.length ;i++){
            arraylist12.add(new ViewStockItems(title12[i],recommendation12[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image12[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist12);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    void PopulateStockListView13(){

        arraylist13 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title13.length ;i++){
            arraylist13.add(new ViewStockItems(title13[i],recommendation13[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image13[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist13);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    void PopulateStockListView14(){

        arraylist14 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title14.length ;i++){
            arraylist14.add(new ViewStockItems(title14[i],recommendation14[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image14[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist14);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    void PopulateStockListView15(){

        arraylist15 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title15.length ;i++){
            arraylist15.add(new ViewStockItems(title15[i],recommendation15[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image15[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist15);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }




    void PopulateStockListView31(){

        arraylist31 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title31.length ;i++){
            arraylist31.add(new ViewStockItems(title31[i],recommendation31[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image31[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist31);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    void PopulateStockListView32(){

        arraylist32 = new ArrayList<ViewStockItems>();
        for(int i=0; i<title32.length ;i++){
            arraylist32.add(new ViewStockItems(title32[i],recommendation32[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image32[i]))));//RepRegistration.productImages[i]
        }
        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist32);
        ViewStockGridview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

//    void PopulateStockListView12(){
//
//        arraylist12 = new ArrayList<ViewStockItems>();
//        for(int i=0; i<title12.length ;i++){
//            arraylist12.add(new ViewStockItems(title12[i],recommendation12[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image12[i]))));//RepRegistration.productImages[i]
//        }
//        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist12);
//        ViewStockGridview.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

//    void PopulateStockListView21(){
//
//        arraylist21 = new ArrayList<ViewStockItems>();
//        for(int i=0; i< title21.length ;i++){
//
//            arraylist21.add(new ViewStockItems(title21[i],recommendation21[i], AppUtilityClass.getBytes(BitmapFactory.decodeResource(getResources(),image21[i]))));
//
//        }
//
//        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist21);
//        ViewStockGridview.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

//    void PopulateStockListView22(){
//
//        arraylist22 = new ArrayList<ViewStockItems>();
//        for(int i=0; i<2 ;i++){
//            arraylist11.add(new ViewStockItems(modelArray[i],priceArray[i], productImage.get(i)));
//        }
//        adapter = new ViewStockCustomAdapter(ViewStock.this, arraylist22);
//        ViewStockListView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }
    void init(){
        ViewStockGridview = (GridView) findViewById(R.id.ViewStockGridview);
        previousButton = (Button) findViewById(R.id.previousButton);
        nextButton = (Button) findViewById(R.id.nextButton);
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.view_stock, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.mainPage) {
//            Intent intent = new Intent(ViewStock.this,MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            return true;
//        }
//
//        else  if (id == R.id.ViewSales) {
//            Intent intent = new Intent(ViewStock.this,ViewSales.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            return true;
//        }
//
//        else  if (id == R.id.addToStock) {
//            Intent intent = new Intent(ViewStock.this,AddStock.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            return true;
//        }
//
//        else  if (id == R.id.changeProductPrice) {
//            Intent intent = new Intent(ViewStock.this,ChangeProductPrice.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
