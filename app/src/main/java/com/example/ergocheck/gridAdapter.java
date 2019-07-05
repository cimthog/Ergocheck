package com.example.ergocheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by USER on 2/19/2018.
 */

public class gridAdapter extends BaseAdapter {
    private int selectedPosition = -1;
    private Context context;
    int img[];
    private LayoutInflater inflater;
    public gridAdapter (Context applicationContext, int[] img) {
        this.context = applicationContext;
        this.img = img;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
    public  void getSelectedPosition (int position) {
       int positionSelected = position+1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.reba_items,null);
        ImageView image = (ImageView) view.findViewById(R.id.imagebtn);
        ImageView imager = (ImageView) view.findViewById(R.id.imageCover) ;
        image.setImageResource(img[i]);

        if (i == selectedPosition) {
           imager.setVisibility(View.VISIBLE);
        } else {
            imager.setVisibility(View.INVISIBLE);
        }


        return view;
    }
}
