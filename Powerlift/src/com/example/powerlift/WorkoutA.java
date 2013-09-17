package com.example.powerlift;

import java.util.List;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class WorkoutA extends ListFragment {

	private ExerciseNameDataSource exeds;
	WorkoutCustomListViewAdapter adapter;
	List<ExerciseName> values;
	long wid = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_a,
				container, false);

		exeds = new ExerciseNameDataSource(getActivity());
		exeds.open();

		values = exeds.getAllExerciseNames();

		adapter = new WorkoutCustomListViewAdapter(getActivity(),
				R.layout.list_item, values, wid);
		setListAdapter(adapter);


		return rootView;
	}




}
