package com.example.powerlift;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class WorkoutA extends ListFragment  {

	private ExerciseDataSource exeds;
	WorkoutCustomListViewAdapter adapter;
	List<ExerciseName> values;
	long wid = 1;
	private Button button_add_exe;
	private TextView wa;
	private static final double increment = 2.5;
	LinearLayout ll;
	

	

	private ExerciseNameDataSource exnds;

	private List<Exercise> evalues;

	ExerciseName rowItem;
	Exercise eItem;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_a,
				container, false);

		exnds = new ExerciseNameDataSource(getActivity());
		exnds.open();
		
		
		exeds = new ExerciseDataSource(getActivity());
		exeds.open();

		wid = getArguments().getInt("wid");
		wa = (TextView) rootView.findViewById(R.id.workout_a);
		wa.setText("Workout " + wid);

		Log.d("info", "wid: " + wid);

		values = exnds.getAllExerciseNamesWithID(wid);

		adapter = new WorkoutCustomListViewAdapter(getActivity(),
				R.layout.list_item, values, wid);
		setListAdapter(adapter);
		

		
		button_add_exe = (Button) rootView.findViewById(R.id.button_add_exe);


		button_add_exe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainSlider) getActivity()).startNewExerciseDialog();
			}
		});

		return rootView;
	}

	public void onViewCreated(View view, Bundle savedInstanceState) {
		Log.d("TAG", "onViewCreated");
		ListView list = getListView();
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long id) {
				// TODO Auto-generated method stub

				Log.v("long clicked", "pos: " + pos);
	
				
				int position = pos;
				
				
				exeds.open();
				exnds.open();

				// formatting date
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				// get current date time with Calendar()
				Calendar cal = Calendar.getInstance();

				String date = dateFormat.format(cal.getTime());

//				Log.d(EXE_TAG, "Clicked button position: " + pos);

//				Log.d(EXE_TAG, "Accomplished: "
//						+ values.get(pos).getAccomplished());

				ContentValues data = new ContentValues();

				data.put("accomplished", true);
				data.put("weight", values.get(position).getWeight() + increment);

				exnds.updateExercise(data, values.get(position).getID());

				exeds.createExercise(values.get(position).getWID(),
						values.get(position).getID(), values.get(position)
								.getWeight(), date,
						values.get(position).getName(), true);

//				Log.d(EXE_TAG, "new value: " + values.toString());
				adapter.notifyDataSetChanged();
				exnds.close();
				exeds.close();


				return true;
			}
		});
	}
	


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		
		//Toast.makeText(getActivity(), "meh", Toast.LENGTH_SHORT).show();
		((MainSlider) getActivity()).startExerciseDialog(position);
	}

}
