package com.example.powerlift;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class WorkoutA extends ListFragment {

	private ExerciseNameDataSource exeds;
	WorkoutCustomListViewAdapter adapter;
	List<ExerciseName> values;
	long wid = 1;
	private Button button_add_exe;
	private TextView wa;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_a,
				container, false);

		exeds = new ExerciseNameDataSource(getActivity());
		exeds.open();

		wid = getArguments().getInt("wid");
		wa = (TextView) rootView.findViewById(R.id.workout_a);
		wa.setText("Workout "+wid);
		
		Log.d("info", "wid: " + wid);
		
		values = exeds.getAllExerciseNamesWithID(wid);

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

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		// Toast.makeText(getActivity(), "meh", Toast.LENGTH_SHORT).show();
		((MainSlider) getActivity()).startExerciseDialog(position);
	}

}
