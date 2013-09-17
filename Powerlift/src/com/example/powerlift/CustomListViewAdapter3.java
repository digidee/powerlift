package com.example.powerlift;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewAdapter3 extends ArrayAdapter<Workout> {

	Context context;

	public CustomListViewAdapter3(Context context, int resourceId,
			List<Workout> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView li_workout_id;
		TextView li_workout_name;


	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Workout rowItem = getItem(position);


		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item3, null);
			holder = new ViewHolder();

			holder.li_workout_id = (TextView) convertView.findViewById(R.id.li_workout_id);
			holder.li_workout_name = (TextView) convertView.findViewById(R.id.li_workout_name);



			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.li_workout_id.setText(""+rowItem.getID());
		holder.li_workout_name.setText(""+rowItem.getName());

		return convertView;
	}
}