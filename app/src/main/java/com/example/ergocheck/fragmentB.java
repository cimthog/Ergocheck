package com.example.ergocheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by USER on 2/19/2018.
 */

public class fragmentB extends Fragment {

    private GridView upper_grid, lower_grid, wrist_grid;
    int[] Image = {R.mipmap.upper1, R.mipmap.upper2, R.mipmap.upper3, R.mipmap.upper4, R.mipmap.upper5};
    int[] image = {R.mipmap.lowerarm1, R.mipmap.lower2};
    int[] img = {R.mipmap.w2, R.mipmap.w1};
    Button subBtn;
    public static CheckBox checkBox1, checkBox2, checkBox3;
    public static int upperPosition, lowerPosition, wristPosition, scorePosition;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frament_b, container, false);


        upper_grid = (GridView) rootView.findViewById(R.id.upper_grid);
        lower_grid = (GridView) rootView.findViewById(R.id.lower_grid);
        wrist_grid = (GridView) rootView.findViewById(R.id.wrist_grid);
        subBtn = (Button) rootView.findViewById(R.id.subBtn);
        checkBox1 = (CheckBox) rootView.findViewById(R.id.check1);
        checkBox2 = (CheckBox) rootView.findViewById(R.id.check2);
        checkBox3 = (CheckBox) rootView.findViewById(R.id.check3);

        final gridAdapter myAdapter = new gridAdapter(getContext(), image);
        final gridAdapter adapter = new gridAdapter(getContext(), Image);
        final gridAdapter gridAdapter = new gridAdapter(getContext(), img);
        upper_grid.setAdapter(adapter);
        lower_grid.setAdapter(myAdapter);
        wrist_grid.setAdapter(gridAdapter);

        upper_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetChanged();
                fragmentB.upperPosition = i;
            }
        });
        lower_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myAdapter.setSelectedPosition(i);
                myAdapter.notifyDataSetChanged();
                fragmentB.lowerPosition = i;
            }
        });
        wrist_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gridAdapter.setSelectedPosition(i);
                gridAdapter.notifyDataSetChanged();
                fragmentB.wristPosition = i;
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int loadValue = fragmentA.loadNum();
                int scoreA = fragmentA.postureA();
                if ((loadValue == 100) || (scoreA == 0)) {
                    Toast.makeText(getContext(), "Please Ensure you fill all fields", Toast.LENGTH_SHORT).show();
                } else
                    startActivity(new Intent(getContext(), popup_window.class));
            }
        });


        return rootView;

    }

    public static int checkNum() {
        int checkValue = 0;
        if ((checkBox1.isChecked()))
            checkValue = 1;
        if (checkBox2.isChecked())
            checkValue = 1;
        if (checkBox3.isChecked())
            checkValue = 1;
          if ((checkBox1.isChecked()) && (checkBox2.isChecked())) {
            checkValue = 2;
        }  if ((checkBox1.isChecked()) && (checkBox3.isChecked())) {
            checkValue = 2;
        }  if ((checkBox2.isChecked()) && (checkBox3.isChecked()))
            checkValue = 2;
        if ((checkBox1.isChecked()) && (checkBox2.isChecked()) && (checkBox3.isChecked()))
            checkValue = 3;
        else if ((!checkBox1.isChecked()) && (!checkBox2.isChecked()) && (!checkBox3.isChecked()))
            checkValue = 0;
        return checkValue;
    }

    public static int upperValue(int i) {
        int b = i+1;
        int numUpper = 0;
        if (b == 1)
            numUpper = 1;
        else if (b == 2)
            numUpper = 2;
        else if (b == 3)
            numUpper = 2;
        else if (b == 4)
            numUpper = 3;
        else if (b == 5)
            numUpper = 4;

        return numUpper;

    }

    public static int lowerValue(int i) {
        int numLow = 0;
        if (i == 0)
            numLow = 1;
        else if (i == 1)
            numLow = 2;

        return numLow;

    }

    public static int wristNum(int i) {
        int numWrist = 0;
        if (i == 0)
            numWrist = 1;
        else if (i == 1)
            numWrist = 2;

        return numWrist;

    }

    public static int postureB() {
        int postureValue = 0;
        //get values of upper arm
        int upperNum = upperValue(upperPosition);

        // get values of lower arm
        int lowerNum = lowerValue(lowerPosition);

        // get values of wrist
        int wristNum = wristNum(wristPosition);

        //conditional statements to check through the values

        if ((lowerNum == 1) && (wristNum == 1) && (upperNum == 1))
            postureValue = 1;
        else if ((lowerNum == 1) && (wristNum == 1) && (upperNum == 2))
            postureValue = 1;
        else if ((lowerNum == 1) && (wristNum == 1) && (upperNum == 3))
            postureValue = 3;
        else if ((lowerNum == 1) && (wristNum == 1) && (upperNum == 4))
            postureValue = 4;
        else if ((lowerNum == 1) && (wristNum == 1) && (upperNum == 5))
            postureValue = 6;
        else if ((lowerNum == 1) && (wristNum == 1) && (upperNum == 6))
            postureValue = 7;
        else if ((lowerNum == 1) && (wristNum == 2) && (upperNum == 1))
            postureValue = 2;
        else if ((lowerNum == 1) && (wristNum == 2) && (upperNum == 2))
            postureValue = 2;
        else if ((lowerNum == 1) && (wristNum == 2) && (upperNum == 3))
            postureValue = 4;
        else if ((lowerNum == 1) && (wristNum == 2) && (upperNum == 4))
            postureValue = 5;
        else if ((lowerNum == 1) && (wristNum == 2) && (upperNum == 5))
            postureValue = 7;
        else if ((lowerNum == 1) && (wristNum == 2) && (upperNum == 6))
            postureValue = 8;
        else if ((lowerNum == 1) && (wristNum == 3) && (upperNum == 1))
            postureValue = 2;
        else if ((lowerNum == 1) && (wristNum == 3) && (upperNum == 2))
            postureValue = 3;
        else if ((lowerNum == 1) && (wristNum == 3) && (upperNum == 3))
            postureValue = 5;
        else if ((lowerNum == 1) && (wristNum == 3) && (upperNum == 4))
            postureValue = 5;
        else if ((lowerNum == 1) && (wristNum == 3) && (upperNum == 5))
            postureValue = 8;
        else if ((lowerNum == 1) && (wristNum == 3) && (upperNum == 6))
            postureValue = 8;
        else if ((lowerNum == 2) && (wristNum == 1) && (upperNum == 1))
            postureValue = 1;
        else if ((lowerNum == 2) && (wristNum == 1) && (upperNum == 2))
            postureValue = 2;
        else if ((lowerNum == 2) && (wristNum == 1) && (upperNum == 3))
            postureValue = 4;
        else if ((lowerNum == 2) && (wristNum == 1) && (upperNum == 4))
            postureValue = 5;
        else if ((lowerNum == 2) && (wristNum == 1) && (upperNum == 5))
            postureValue = 7;
        else if ((lowerNum == 2) && (wristNum == 1) && (upperNum == 6))
            postureValue = 8;
        else if ((lowerNum == 2) && (wristNum == 2) && (upperNum == 1))
            postureValue = 2;
        else if ((lowerNum == 2) && (wristNum == 2) && (upperNum == 2))
            postureValue = 3;
        else if ((lowerNum == 2) && (wristNum == 2) && (upperNum == 3))
            postureValue = 5;
        else if ((lowerNum == 2) && (wristNum == 2) && (upperNum == 4))
            postureValue = 6;
        else if ((lowerNum == 2) && (wristNum == 2) && (upperNum == 5))
            postureValue = 8;
        else if ((lowerNum == 2) && (wristNum == 2) && (upperNum == 6))
            postureValue = 9;
        else if ((lowerNum == 2) && (wristNum == 3) && (upperNum == 1))
            postureValue = 3;
        else if ((lowerNum == 2) && (wristNum == 3) && (upperNum == 2))
            postureValue = 4;
        else if ((lowerNum == 2) && (wristNum == 3) && (upperNum == 3))
            postureValue = 5;
        else if ((lowerNum == 2) && (wristNum == 3) && (upperNum == 4))
            postureValue = 7;
        else if ((lowerNum == 2) && (wristNum == 3) && (upperNum == 5))
            postureValue = 8;
        else if ((lowerNum == 2) && (wristNum == 3) && (upperNum == 6))
            postureValue = 9;
        else
            postureValue = 0;

        return postureValue;
    }

    public static int scoreC() {
        int scoreA = fragmentA.postureA();
        int scoreC = 0;
        int scoreB = postureB();
        if ((scoreB == 1) && (scoreA == 1))
            scoreC = 1;
        else if ((scoreB == 1) && (scoreA == 2))
            scoreC = 1;
        else if ((scoreB == 1) && (scoreA == 3))
            scoreC = 2;
        else if ((scoreB == 1) && (scoreA == 4))
            scoreC = 3;
        else if ((scoreB == 1) && (scoreA == 5))
            scoreC = 4;
        else if ((scoreB == 1) && (scoreA == 6))
            scoreC = 6;
        else if ((scoreB == 1) && (scoreA == 7))
            scoreC = 7;
        else if ((scoreB == 1) && (scoreA == 8))
            scoreC = 8;
        else if ((scoreB == 1) && (scoreA == 9))
            scoreC = 9;
        else if ((scoreB == 1) && (scoreA == 10))
            scoreC = 10;
        else if ((scoreB == 1) && (scoreA == 11))
            scoreC = 11;
        else if ((scoreB == 1) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 2) && (scoreA == 1))
            scoreC = 1;
        else if ((scoreB == 2) && (scoreA == 2))
            scoreC = 2;
        else if ((scoreB == 2) && (scoreA == 3))
            scoreC = 3;
        else if ((scoreB == 2) && (scoreA == 4))
            scoreC = 4;
        else if ((scoreB == 2) && (scoreA == 5))
            scoreC = 4;
        else if ((scoreB == 2) && (scoreA == 6))
            scoreC = 6;
        else if ((scoreB == 2) && (scoreA == 7))
            scoreC = 7;
        else if ((scoreB == 2) && (scoreA == 8))
            scoreC = 8;
        else if ((scoreB == 2) && (scoreA == 9))
            scoreC = 9;
        else if ((scoreB == 2) && (scoreA == 10))
            scoreC = 10;
        else if ((scoreB == 2) && (scoreA == 11))
            scoreC = 11;
        else if ((scoreB == 2) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 3) && (scoreA == 1))
            scoreC = 1;
        else if ((scoreB == 3) && (scoreA == 2))
            scoreC = 2;
        else if ((scoreB == 3) && (scoreA == 3))
            scoreC = 3;
        else if ((scoreB == 3) && (scoreA == 4))
            scoreC = 4;
        else if ((scoreB == 3) && (scoreA == 5))
            scoreC = 4;
        else if ((scoreB == 3) && (scoreA == 6))
            scoreC = 6;
        else if ((scoreB == 3) && (scoreA == 7))
            scoreC = 7;
        else if ((scoreB == 3) && (scoreA == 8))
            scoreC = 8;
        else if ((scoreB == 3) && (scoreA == 9))
            scoreC = 9;
        else if ((scoreB == 3) && (scoreA == 10))
            scoreC = 10;
        else if ((scoreB == 3) && (scoreA == 11))
            scoreC = 11;
        else if ((scoreB == 3) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 4) && (scoreA == 1))
            scoreC = 2;
        else if ((scoreB == 4) && (scoreA == 2))
            scoreC = 3;
        else if ((scoreB == 4) && (scoreA == 3))
            scoreC = 3;
        else if ((scoreB == 4) && (scoreA == 4))
            scoreC = 4;
        else if ((scoreB == 4) && (scoreA == 5))
            scoreC = 5;
        else if ((scoreB == 4) && (scoreA == 6))
            scoreC = 7;
        else if ((scoreB == 4) && (scoreA == 7))
            scoreC = 8;
        else if ((scoreB == 4) && (scoreA == 8))
            scoreC = 9;
        else if ((scoreB == 4) && (scoreA == 9))
            scoreC = 10;
        else if ((scoreB == 4) && (scoreA == 10))
            scoreC = 11;
        else if ((scoreB == 4) && (scoreA == 11))
            scoreC = 11;
        else if ((scoreB == 4) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 5) && (scoreA == 1))
            scoreC = 3;
        else if ((scoreB == 5) && (scoreA == 2))
            scoreC = 4;
        else if ((scoreB == 5) && (scoreA == 3))
            scoreC = 4;
        else if ((scoreB == 5) && (scoreA == 4))
            scoreC = 5;
        else if ((scoreB == 5) && (scoreA == 5))
            scoreC = 6;
        else if ((scoreB == 5) && (scoreA == 6))
            scoreC = 8;
        else if ((scoreB == 5) && (scoreA == 7))
            scoreC = 9;
        else if ((scoreB == 5) && (scoreA == 8))
            scoreC = 10;
        else if ((scoreB == 5) && (scoreA == 9))
            scoreC = 10;
        else if ((scoreB == 5) && (scoreA == 10))
            scoreC = 11;
        else if ((scoreB == 5) && (scoreA == 11))
            scoreC = 11;
        else if ((scoreB == 5) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 6) && (scoreA == 1))
            scoreC = 3;
        else if ((scoreB == 6) && (scoreA == 2))
            scoreC = 4;
        else if ((scoreB == 6) && (scoreA == 3))
            scoreC = 5;
        else if ((scoreB == 6) && (scoreA == 4))
            scoreC = 6;
        else if ((scoreB == 6) && (scoreA == 5))
            scoreC = 7;
        else if ((scoreB == 6) && (scoreA == 6))
            scoreC = 8;
        else if ((scoreB == 6) && (scoreA == 7))
            scoreC = 9;
        else if ((scoreB == 6) && (scoreA == 8))
            scoreC = 10;
        else if ((scoreB == 6) && (scoreA == 9))
            scoreC = 10;
        else if ((scoreB == 6) && (scoreA == 10))
            scoreC = 11;
        else if ((scoreB == 6) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 6) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 7) && (scoreA == 1))
            scoreC = 4;
        else if ((scoreB == 7) && (scoreA == 2))
            scoreC = 5;
        else if ((scoreB == 7) && (scoreA == 3))
            scoreC = 6;
        else if ((scoreB == 7) && (scoreA == 4))
            scoreC = 7;
        else if ((scoreB == 7) && (scoreA == 5))
            scoreC = 8;
        else if ((scoreB == 7) && (scoreA == 6))
            scoreC = 9;
        else if ((scoreB == 7) && (scoreA == 7))
            scoreC = 9;
        else if ((scoreB == 7) && (scoreA == 8))
            scoreC = 10;
        else if ((scoreB == 7) && (scoreA == 9))
            scoreC = 11;
        else if ((scoreB == 7) && (scoreA == 10))
            scoreC = 11;
        else if ((scoreB == 7) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 7) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 8) && (scoreA == 1))
            scoreC = 5;
        else if ((scoreB == 8) && (scoreA == 2))
            scoreC = 6;
        else if ((scoreB == 8) && (scoreA == 3))
            scoreC = 7;
        else if ((scoreB == 8) && (scoreA == 4))
            scoreC = 8;
        else if ((scoreB == 8) && (scoreA == 5))
            scoreC = 8;
        else if ((scoreB == 8) && (scoreA == 6))
            scoreC = 9;
        else if ((scoreB == 8) && (scoreA == 7))
            scoreC = 10;
        else if ((scoreB == 8) && (scoreA == 8))
            scoreC = 10;
        else if ((scoreB == 8) && (scoreA == 9))
            scoreC = 11;
        else if ((scoreB == 8) && (scoreA == 10))
            scoreC = 12;
        else if ((scoreB == 8) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 8) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 9) && (scoreA == 1))
            scoreC = 6;
        else if ((scoreB == 9) && (scoreA == 2))
            scoreC = 6;
        else if ((scoreB == 9) && (scoreA == 3))
            scoreC = 7;
        else if ((scoreB == 9) && (scoreA == 4))
            scoreC = 8;
        else if ((scoreB == 9) && (scoreA == 5))
            scoreC = 9;
        else if ((scoreB == 9) && (scoreA == 6))
            scoreC = 10;
        else if ((scoreB == 9) && (scoreA == 7))
            scoreC = 10;
        else if ((scoreB == 9) && (scoreA == 8))
            scoreC = 10;
        else if ((scoreB == 9) && (scoreA == 9))
            scoreC = 11;
        else if ((scoreB == 9) && (scoreA == 10))
            scoreC = 12;
        else if ((scoreB == 9) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 9) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 10) && (scoreA == 1))
            scoreC = 7;
        else if ((scoreB == 10) && (scoreA == 2))
            scoreC = 7;
        else if ((scoreB == 10) && (scoreA == 3))
            scoreC = 8;
        else if ((scoreB == 10) && (scoreA == 4))
            scoreC = 9;
        else if ((scoreB == 10) && (scoreA == 5))
            scoreC = 9;
        else if ((scoreB == 10) && (scoreA == 6))
            scoreC = 10;
        else if ((scoreB == 10) && (scoreA == 7))
            scoreC = 11;
        else if ((scoreB == 10) && (scoreA == 8))
            scoreC = 11;
        else if ((scoreB == 10) && (scoreA == 9))
            scoreC = 12;
        else if ((scoreB == 10) && (scoreA == 10))
            scoreC = 12;
        else if ((scoreB == 10) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 10) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 11) && (scoreA == 1))
            scoreC = 7;
        else if ((scoreB == 11) && (scoreA == 2))
            scoreC = 7;
        else if ((scoreB == 11) && (scoreA == 3))
            scoreC = 8;
        else if ((scoreB == 11) && (scoreA == 4))
            scoreC = 9;
        else if ((scoreB == 11) && (scoreA == 5))
            scoreC = 9;
        else if ((scoreB == 11) && (scoreA == 6))
            scoreC = 10;
        else if ((scoreB == 11) && (scoreA == 7))
            scoreC = 11;
        else if ((scoreB == 11) && (scoreA == 8))
            scoreC = 11;
        else if ((scoreB == 11) && (scoreA == 9))
            scoreC = 12;
        else if ((scoreB == 11) && (scoreA == 10))
            scoreC = 12;
        else if ((scoreB == 11) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 11) && (scoreA == 12))
            scoreC = 12;
        else if ((scoreB == 12) && (scoreA == 1))
            scoreC = 7;
        else if ((scoreB == 12) && (scoreA == 2))
            scoreC = 8;
        else if ((scoreB == 12) && (scoreA == 3))
            scoreC = 8;
        else if ((scoreB == 12) && (scoreA == 4))
            scoreC = 9;
        else if ((scoreB == 12) && (scoreA == 5))
            scoreC = 9;
        else if ((scoreB == 12) && (scoreA == 6))
            scoreC = 10;
        else if ((scoreB == 12) && (scoreA == 7))
            scoreC = 11;
        else if ((scoreB == 12) && (scoreA == 8))
            scoreC = 11;
        else if ((scoreB == 12) && (scoreA == 9))
            scoreC = 12;
        else if ((scoreB == 12) && (scoreA == 10))
            scoreC = 12;
        else if ((scoreB == 12) && (scoreA == 11))
            scoreC = 12;
        else if ((scoreB == 12) && (scoreA == 12))
            scoreC = 12;
        else
            scoreC = 0;

        return scoreC;
    }
}
