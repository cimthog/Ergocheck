package com.example.ergocheck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewStockCustomAdapter extends BaseAdapter {

	Context context;
	ArrayList<ViewStockItems> arraylist;


	public ViewStockCustomAdapter(Context context, ArrayList<ViewStockItems> arraylist) {
		this.context = context;
		this.arraylist = arraylist;
	}

	@Override
	public int getCount() {
		return arraylist.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arraylist.get(arg0);
	}

	@Override
	public long getItemId(int pos) {
		// item id
		return pos;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		
		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (view == null) {
			view = inflater.inflate(R.layout.gridview_items, parent, false);

			holder = new ViewHolder();

			holder.titleTextView = (TextView) view.findViewById(R.id.title);
			holder.image = (ImageView) view.findViewById(R.id.image);
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();

		}
		
		final String title = arraylist.get(position).getTitle();
		byte[] image_ = arraylist.get(position).getImage();

		
		holder.titleTextView.setText(title);
		holder.image.setImageBitmap(AppUtilityClass.getImage(image_));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            	if(title.equalsIgnoreCase("Body Pains")){

					Intent intent = new Intent(context,ViewStock.class);
					intent.putExtra("number","4");
					intent.putExtra("from","adapter");
					intent.putExtra("machineUsed","Sealing machine");
					//intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(intent);

				}
				else{
					Intent intent = new Intent(context,recommendationDisplay.class);
					intent.putExtra("recommendationString",arraylist.get(position).getRecommendation());
					intent.putExtra("from","notAdapter");
					context.startActivity(intent);
				}

            }
        });

		return view;
	}

	
	public class ViewHolder {
		TextView titleTextView;
		ImageView image;

	}



}
