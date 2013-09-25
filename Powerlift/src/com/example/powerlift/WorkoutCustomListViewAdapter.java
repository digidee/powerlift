package com.example.powerlift;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutCustomListViewAdapter extends ArrayAdapter<ExerciseName> {

	Context context;

	private ExerciseDataSource exeds;
	private ExerciseNameDataSource exnds;
	private List<ExerciseName> values;
	private List<Exercise> evalues;
	ViewHolder holder = null;
	ExerciseName rowItem;
	Exercise eItem;
	long wid;

	private static final double increment = 2.5;

	private static final String EXE_TAG = "Workout Register";

	public WorkoutCustomListViewAdapter(Context context, int resourceId,
			List<ExerciseName> items, long wid) {
		super(context, resourceId, items);
		this.context = context;
		this.wid = wid;
	}

	/* private view holder class */
	private class ViewHolder {
		TextView li_exercise_name;
		TextView li_exercise_weight;
		Button li_button;
		CheckBox li_checkbox;
		LinearLayout ll;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		exeds = new ExerciseDataSource(context);
		exeds.open();

		exnds = new ExerciseNameDataSource(context);
		exnds.open();

		// rowItem = getItem(position);
		evalues = exeds.getAllExercises();
		if (!evalues.isEmpty())
			eItem = evalues.get(position);

		// rowItem = getItem(position);
		values = exnds.getAllExerciseNamesWithID(wid);
		if (!values.isEmpty())
			rowItem = values.get(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.workout_list, null);
			holder = new ViewHolder();

			holder.li_exercise_name = (TextView) convertView
					.findViewById(R.id.li_exercise_name);

			holder.li_exercise_weight = (TextView) convertView
					.findViewById(R.id.li_exercise_weight);
			holder.li_checkbox = (CheckBox) convertView
					.findViewById(R.id.li_checkbox);

			holder.li_button = (Button) convertView
					.findViewById(R.id.li_button);

			holder.ll = (LinearLayout) convertView
					.findViewById(R.id.li_ll_header);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.li_exercise_name.setText("" + rowItem.getName());
		holder.li_exercise_weight.setText(rowItem.getWeight() + "kg");

		holder.li_checkbox.setChecked(rowItem.getAccomplished());

//		holder.ll.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(context, "cliick", Toast.LENGTH_SHORT).show();
//
//			}
//		});

		holder.li_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				exeds.open();
				exnds.open();

				// formatting date
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				// get current date time with Calendar()
				Calendar cal = Calendar.getInstance();

				String date = dateFormat.format(cal.getTime());

				Log.d(EXE_TAG, "Clicked button position: " + position);

				Log.d(EXE_TAG, "Accomplished: "
						+ values.get(position).getAccomplished());

				ContentValues data = new ContentValues();

				data.put("accomplished", true);
				data.put("weight", values.get(position).getWeight() + increment);

				exnds.updateExercise(data, values.get(position).getID());

				exeds.createExercise(values.get(position).getWID(),
						values.get(position).getID(), values.get(position)
								.getWeight(), date,
						values.get(position).getName(), true);

				Log.d(EXE_TAG, "new value: " + values.toString());
				notifyDataSetChanged();
				exnds.close();
				exeds.close();
			}
		});

		exeds.close();

		return convertView;
	}

}