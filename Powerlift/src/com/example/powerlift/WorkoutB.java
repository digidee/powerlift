package com.example.powerlift;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutB extends ListFragment {

	private ExerciseNameDataSource exeds;
	WorkoutCustomListViewAdapter adapter;
	List<ExerciseName> values;
	long wid = 2;
	TextView tv;
	private Button button_add_exe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_a,
				container, false);

		tv = (TextView) rootView.findViewById(R.id.workout_a);
		tv.setText(getResources().getString(R.string.workout_b));
		SpannableString content = new SpannableString(tv.getText().toString());
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		tv.setText(content);

		exeds = new ExerciseNameDataSource(getActivity());
		exeds.open();

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

}
