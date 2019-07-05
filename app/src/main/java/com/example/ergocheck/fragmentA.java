package com.example.ergocheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

/**
 * Created by USER on 2/18/2018.
 */

public class fragmentA extends Fragment {

    private GridView neck_grid, trunk_grid, leg_grid;
    int[] Image = {R.mipmap.trunk1, R.mipmap.trunk2, R.mipmap.trunk3, R.mipmap.trunk4, R.mipmap.trunk5};
    int[] image = {R.mipmap.img3, R.mipmap.im2, R.mipmap.im1};
    int[] img = {R.mipmap.leg4, R.mipmap.leggy2};
    public static int neckNum, trunkNum, legNum, postureScore;
    public static int trunkPosition, neckPosition, legPosition;
    public static View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frament_a, container, false);
        neck_grid = (GridView) rootView.findViewById(R.id.neck_grid);
        trunk_grid = (GridView) rootView.findViewById(R.id.trunk_grid);
        leg_grid = (GridView) rootView.findViewById(R.id.leg_grid);

        fragmentA.view = rootView;


        final gridAdapter myAdapter = new gridAdapter(getContext(), image);
        final gridAdapter adapter = new gridAdapter(getContext(), Image);
        final gridAdapter gridAdapt = new gridAdapter(getContext(), img);
        neck_grid.setAdapter(myAdapter);
        trunk_grid.setAdapter(adapter);
        leg_grid.setAdapter(gridAdapt);
        neck_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myAdapter.setSelectedPosition(i);
                myAdapter.notifyDataSetChanged();
                fragmentA.neckPosition = i;
            }
        });
        trunk_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetChanged();
                fragmentA.trunkPosition = i;
            }
        });
        leg_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gridAdapt.setSelectedPosition(i);
                gridAdapt.notifyDataSetChanged();
                fragmentA.legPosition = i;
            }
        });

        return rootView;
    }

    public static int loadNum() {

        EditText loadText = (EditText) view.findViewById(R.id.load);
        String txtLoad = loadText.getText().toString();
        int loadValue = 0;
        double load = 0;

        if (txtLoad.isEmpty()) {
            loadValue = 100;
            loadText.setError("This field cannot be blank");
        }
         else if (!txtLoad.isEmpty()) {
            load = Double.valueOf(txtLoad);
            if (load < 5.0)
                loadValue = 0;
            else if ((load >= 5.0) && (load <= 10.0))
                loadValue = 1;
            else if ((load > 10.0))
                loadValue = 2;
        }
        return loadValue;
    }

    public static int neckValue(int i) {
        int value = 0;
        if (i == 0)
            value = 1;
        else if (i == 1)
            value = 2;
        else if (i == 2)
            value = 2;
        return value;
    }

    public static int trunkValue(int i) {
        int value = 0;
        if (i == 0)
            value = 1;
        else if (i == 1)
            value = 2;
        else if (i == 2)
            value = 2;
        else if (i == 3)
            value = 3;
        else if (i == 4)
            value = 4;
        return value;
    }

    public static int legValue(int i) {
        int value = 0;
        if (i == 0)
            value = 1;
        else if (i == 1)
            value = 2;
        return value;
    }

    public static int postureA() {
        //get values of neck images
        neckNum = neckValue(neckPosition);

        //get values of trunk images
        trunkNum = trunkValue(trunkPosition);

        //get values for leg images
        legNum = legValue(legPosition);

        int loadValue = fragmentA.loadNum();
        int scoreA = 0;

        //  conditional statements to check through the values
        if ((neckNum == 0) || (legNum == 0) || (trunkNum == 0))
            postureScore = 0;
        if ((neckNum == 1) && (legNum == 1) && (trunkNum == 1))
            postureScore = 2;
        else if ((neckNum == 1) && (trunkNum == 1) && (legNum == 2))
            postureScore = 2;
        else if ((neckNum == 1) && (trunkNum == 1) && (legNum == 3))
            postureScore = 2;
        else if ((neckNum == 1) && (trunkNum == 1) && (legNum == 4))
            postureScore = 3;
        else if ((neckNum == 1) && (trunkNum == 1) && (legNum == 5))
            postureScore = 4;
        else if ((neckNum == 1) && (trunkNum == 2) && (legNum == 1))
            postureScore = 2;
        else if ((neckNum == 1) && (trunkNum == 2) && (legNum == 2))
            postureScore = 3;
        else if ((neckNum == 1) && (trunkNum == 2) && (legNum == 3))
            postureScore = 4;
        else if ((neckNum == 1) && (trunkNum == 2) && (legNum == 4))
            postureScore = 5;
        else if ((neckNum == 1) && (trunkNum == 2) && (legNum == 5))
            postureScore = 6;
        else if ((neckNum == 1) && (trunkNum == 3) && (legNum == 1))
            postureScore = 3;
        else if ((neckNum == 1) && (trunkNum == 3) && (legNum == 2))
            postureScore = 4;
        else if ((neckNum == 1) && (trunkNum == 3) && (legNum == 3))
            postureScore = 5;
        else if ((neckNum == 1) && (trunkNum == 3) && (legNum == 4))
            postureScore = 6;
        else if ((neckNum == 1) && (trunkNum == 3) && (legNum == 5))
            postureScore = 7;
        else if ((neckNum == 1) && (trunkNum == 4) && (legNum == 1))
            postureScore = 4;
        else if ((neckNum == 1) && (trunkNum == 4) && (legNum == 2))
            postureScore = 5;
        else if ((neckNum == 1) && (trunkNum == 4) && (legNum == 3))
            postureScore = 6;
        else if ((neckNum == 1) && (trunkNum == 4) && (legNum == 4))
            postureScore = 7;
        else if ((neckNum == 1) && (trunkNum == 4) && (legNum == 5))
            postureScore = 8;
        else if ((neckNum == 2) && (trunkNum == 1) && (legNum == 1))
            postureScore = 1;
        else if ((neckNum == 2) && (trunkNum == 1) && (legNum == 2))
            postureScore = 3;
        else if ((neckNum == 2) && (trunkNum == 1) && (legNum == 3))
            postureScore = 4;
        else if ((neckNum == 2) && (trunkNum == 1) && (legNum == 4))
            postureScore = 5;
        else if ((neckNum == 2) && (trunkNum == 1) && (legNum == 5))
            postureScore = 6;
        else if ((neckNum == 2) && (trunkNum == 2) && (legNum == 1))
            postureScore = 2;
        else if ((neckNum == 2) && (trunkNum == 2) && (legNum == 2))
            postureScore = 4;
        else if ((neckNum == 2) && (trunkNum == 2) && (legNum == 3))
            postureScore = 5;
        else if ((neckNum == 2) && (trunkNum == 2) && (legNum == 4))
            postureScore = 6;
        else if ((neckNum == 2) && (trunkNum == 2) && (legNum == 5))
            postureScore = 7;
        else if ((neckNum == 2) && (trunkNum == 3) && (legNum == 1))
            postureScore = 3;
        else if ((neckNum == 2) && (trunkNum == 3) && (legNum == 2))
            postureScore = 5;
        else if ((neckNum == 2) && (trunkNum == 3) && (legNum == 3))
            postureScore = 6;
        else if ((neckNum == 2) && (trunkNum == 3) && (legNum == 4))
            postureScore = 7;
        else if ((neckNum == 2) && (trunkNum == 3) && (legNum == 5))
            postureScore = 8;
        else if ((neckNum == 2) && (trunkNum == 4) && (legNum == 1))
            postureScore = 5;
        else if ((neckNum == 2) && (trunkNum == 4) && (legNum == 2))
            postureScore = 6;
        else if ((neckNum == 2) && (trunkNum == 4) && (legNum == 3))
            postureScore = 7;
        else if ((neckNum == 2) && (trunkNum == 4) && (legNum == 4))
            postureScore = 8;
        else if ((neckNum == 2) && (trunkNum == 4) && (legNum == 5))
            postureScore = 9;
        else if ((neckNum == 3) && (trunkNum == 1) && (legNum == 1))
            postureScore = 3;
        else if ((neckNum == 3) && (trunkNum == 1) && (legNum == 2))
            postureScore = 4;
        else if ((neckNum == 3) && (trunkNum == 1) && (legNum == 3))
            postureScore = 5;
        else if ((neckNum == 3) && (trunkNum == 1) && (legNum == 4))
            postureScore = 6;
        else if ((neckNum == 3) && (trunkNum == 1) && (legNum == 5))
            postureScore = 7;
        else if ((neckNum == 3) && (trunkNum == 2) && (legNum == 1))
            postureScore = 3;
        else if ((neckNum == 3) && (trunkNum == 2) && (legNum == 2))
            postureScore = 5;
        else if ((neckNum == 3) && (trunkNum == 2) && (legNum == 3))
            postureScore = 6;
        else if ((neckNum == 3) && (trunkNum == 2) && (legNum == 4))
            postureScore = 7;
        else if ((neckNum == 3) && (trunkNum == 2) && (legNum == 5))
            postureScore = 8;
        else if ((neckNum == 3) && (trunkNum == 3) && (legNum == 1))
            postureScore = 5;
        else if ((neckNum == 3) && (trunkNum == 3) && (legNum == 2))
            postureScore = 6;
        else if ((neckNum == 3) && (trunkNum == 3) && (legNum == 3))
            postureScore = 7;
        else if ((neckNum == 3) && (trunkNum == 3) && (legNum == 4))
            postureScore = 8;
        else if ((neckNum == 3) && (trunkNum == 3) && (legNum == 5))
            postureScore = 9;
        else if ((neckNum == 3) && (trunkNum == 4) && (legNum == 1))
            postureScore = 6;
        else if ((neckNum == 3) && (trunkNum == 4) && (legNum == 2))
            postureScore = 7;
        else if ((neckNum == 3) && (trunkNum == 4) && (legNum == 3))
            postureScore = 8;
        else if ((neckNum == 3) && (trunkNum == 4) && (legNum == 4))
            postureScore = 9;
        else if ((neckNum == 3) && (trunkNum == 4) && (legNum == 5))
            postureScore = 9;


        scoreA = postureScore + loadValue;

        return scoreA;
    }

}
