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

public class WorkoutRegister extends ListFragment {

	private ExerciseDataSource exeds;

	private static final String EXE_TAG = "Workout Register";

	private EditText ename, wid, weight;
	private CheckBox accomplished;
	private Button regButton, delButton;

	CustomListViewAdapter adapter;
	List<Exercise> values;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_r,
				container, false);

		exeds = new ExerciseDataSource(getActivity());
		exeds.open();

		ename = (EditText) rootView.findViewById(R.id.ET_exercise_name);
		wid = (EditText) rootView.findViewById(R.id.ET_workout_id);
		weight = (EditText) rootView.findViewById(R.id.ET_start_weight);
		accomplished = (CheckBox) rootView
				.findViewById(R.id.CB_set_accomplished);

		values = exeds.getAllExercises();

		adapter = new CustomListViewAdapter(getActivity(), R.layout.list_item,
				values);
		setListAdapter(adapter);
		exeds.close();

		regButton = (Button) rootView.findViewById(R.id.button_register);
		regButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				registerExercise();
			}
		});

		delButton = (Button) rootView.findViewById(R.id.button_delete);
		delButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				deleteEcercises(adapter);
			}
		});

		return rootView;
	}

	public void registerExercise() {
		Exercise exe = null;

		// formatting date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();

		String date = dateFormat.format(cal.getTime());
		exeds.open();
		exe = exeds.createExercise(Integer.parseInt(wid.getText().toString()),
				exeds.getAllExercises().size(),
		Integer.parseInt(weight.getText().toString()), date, ename.getText()
				.toString(), accomplished.isChecked());
		Log.d(EXE_TAG, "Clicked register");

		values.add(exe);
		adapter.notifyDataSetChanged();
		exeds.close();

	}

	public void deleteEcercises(CustomListViewAdapter adapter) {
		exeds.open();
		exeds.deleteAllExercises();
		exeds.resetIDExercises();
		if (adapter != null) {
			adapter.clear();
		}
		exeds.close();
	}

}
