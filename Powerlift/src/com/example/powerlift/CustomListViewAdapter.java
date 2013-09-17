package com.example.powerlift;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends ArrayAdapter<Exercise> {

	Context context;

	public CustomListViewAdapter(Context context, int resourceId,
			List<Exercise> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView li_exercise_id;
		TextView li_e_workout_id;
		TextView li_exercise_name_id;
		TextView li_exercise_date;
		TextView li_exercise_name;
		TextView li_exercise_accomplished;
		TextView li_exercise_weight;

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Exercise rowItem = getItem(position);


		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();

			holder.li_exercise_id = (TextView) convertView.findViewById(R.id.li_exercise_id);
			holder.li_e_workout_id = (TextView) convertView.findViewById(R.id.li_e_workout_id);
			holder.li_exercise_name_id = (TextView) convertView.findViewById(R.id.li_exercise_name_id);
			holder.li_exercise_date = (TextView) convertView.findViewById(R.id.li_exercise_date);
			holder.li_exercise_name = (TextView) convertView.findViewById(R.id.li_exercise_name);
			holder.li_exercise_accomplished = (TextView) convertView.findViewById(R.id.li_exercise_accomplished);
			holder.li_exercise_weight = (TextView) convertView.findViewById(R.id.li_exercise_weight);


			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.li_exercise_id.setText(""+rowItem.getID());
		holder.li_e_workout_id.setText(""+rowItem.getWID());
		holder.li_exercise_name_id.setText(""+rowItem.getNID());
		holder.li_exercise_date.setText(""+rowItem.getDate());
		holder.li_exercise_name.setText(""+rowItem.getName());
		holder.li_exercise_accomplished.setText(""+rowItem.getAccomplished());
		holder.li_exercise_weight.setText(""+rowItem.getWeight());

		return convertView;
	}
}