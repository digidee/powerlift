package com.example.powerlift;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorkoutB extends ListFragment {

	private ExerciseNameDataSource exeds;
	WorkoutCustomListViewAdapter adapter;
	List<ExerciseName> values;
	long wid = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_b,
				container, false);

		exeds = new ExerciseNameDataSource(getActivity());
		exeds.open();

		values = exeds.getAllExerciseNamesWithID(wid);

		adapter = new WorkoutCustomListViewAdapter(getActivity(),
				R.layout.list_item, values, wid);
		setListAdapter(adapter);


		return rootView;
	}
    
}
