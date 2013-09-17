package com.example.powerlift;

import java.util.List;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class WorkoutMain extends ListFragment {

	private ExerciseNameDataSource exeds;
	WorkoutCustomListViewAdapter adapter;
	List<ExerciseName> values;
	long wid = 0;
	Button ba, bb;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_m,
				container, false);

		ba = (Button) rootView.findViewById(R.id.li_button_a);

		bb = (Button) rootView.findViewById(R.id.li_button_b);

		ba.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setAdapter(1);
			}
		});

		bb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setAdapter(2);
			}
		});

		return rootView;
	}

	public void setAdapter(long wid) {
		exeds = new ExerciseNameDataSource(getActivity());
		exeds.open();

		values = exeds.getAllExerciseNamesWithID(wid);

		adapter = new WorkoutCustomListViewAdapter(getActivity(),
				R.layout.list_item, values, wid);
		setListAdapter(adapter);
	}

}
