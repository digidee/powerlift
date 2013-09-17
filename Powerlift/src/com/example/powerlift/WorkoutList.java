package com.example.powerlift;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WorkoutList extends ListFragment {

	private WorkoutDataSource exnds;

	private static final String EXE_TAG = "Exercise name list";


	CustomListViewAdapter3 adapter;
	List<Workout> values;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_r,
				container, false);

		exnds = new WorkoutDataSource(getActivity());
		exnds.open();


		values = exnds.getAllWorkouts();

		adapter = new CustomListViewAdapter3(getActivity(), R.layout.list_item3,
				values);
		setListAdapter(adapter);
		exnds.close();



		return rootView;
	}



}
