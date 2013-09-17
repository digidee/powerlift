package com.example.powerlift;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ExerciseNameList extends ListFragment {

	private ExerciseNameDataSource exnds;

	private static final String EXE_TAG = "Exercise name list";


	CustomListViewAdapter2 adapter;
	List<ExerciseName> values;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_r,
				container, false);

		exnds = new ExerciseNameDataSource(getActivity());
		exnds.open();


		values = exnds.getAllExerciseNames();

		adapter = new CustomListViewAdapter2(getActivity(), R.layout.list_item2,
				values);
		setListAdapter(adapter);
		exnds.close();



		return rootView;
	}



}
