package com.example.powerlift;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewAdapter2 extends ArrayAdapter<ExerciseName> {

	Context context;

	public CustomListViewAdapter2(Context context, int resourceId,
			List<ExerciseName> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView li_exercise_name_id2;
		TextView li_exercise_name_name;
		TextView li_exercise_name_eid;
		TextView li_exercise_name_current_weight;
		TextView li_exercise_name_accomplished;
		


	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		ExerciseName rowItem = getItem(position);


		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item2, null);
			holder = new ViewHolder();

			holder.li_exercise_name_id2 = (TextView) convertView.findViewById(R.id.li_exercise_name_id2);
			holder.li_exercise_name_name = (TextView) convertView.findViewById(R.id.li_exercise_name_name);
			holder.li_exercise_name_eid = (TextView) convertView.findViewById(R.id.li_exercise_name_eid);
			holder.li_exercise_name_current_weight = (TextView) convertView.findViewById(R.id.li_exercise_name_current_weight);
			holder.li_exercise_name_accomplished = (TextView) convertView.findViewById(R.id.li_exercise_name_accomplished);


			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.li_exercise_name_id2.setText(""+rowItem.getID());
		holder.li_exercise_name_name.setText(""+rowItem.getName());
		holder.li_exercise_name_eid.setText(""+rowItem.getWID());
		holder.li_exercise_name_current_weight.setText(""+rowItem.getWeight());
		holder.li_exercise_name_accomplished.setText(""+rowItem.getAccomplished());

		return convertView;
	}
}