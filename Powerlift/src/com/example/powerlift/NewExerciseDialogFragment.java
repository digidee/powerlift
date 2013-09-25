package com.example.powerlift;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

//...
public class NewExerciseDialogFragment extends DialogFragment implements
		OnEditorActionListener {

	private ExerciseDataSource exeds;
	private ExerciseNameDataSource exnds;
	private WorkoutDataSource wods;

	private EditText wname, weight;
	private AutoCompleteTextView ename;

	private Button regButton, delButton;
	private CheckBox addAll;

	private static final String EXE_TAG = "new_exercise_dialog";

	private static final String[] COUNTRIES = new String[] { "Squat",
			"Pendlay Rows", "Overhead Press", "Bench Press", "Deadlift",
			"Biceps Curls" };

	private int currentPage;

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public NewExerciseDialogFragment() {
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.new_exercise_dialog, container);

		currentPage = getArguments().getInt("currentPage");

		exeds = new ExerciseDataSource(getActivity());
		exeds.open();
		exnds = new ExerciseNameDataSource(getActivity());
		exeds.open();
		wods = new WorkoutDataSource(getActivity());
		wods.open();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		ename = (AutoCompleteTextView) view
				.findViewById(R.id.ET_exercise_name_ac);
		ename.setAdapter(adapter);

		wname = (EditText) view.findViewById(R.id.ET_workout_name);
		weight = (EditText) view.findViewById(R.id.ET_start_weight);
		regButton = (Button) view.findViewById(R.id.button_register);
		delButton = (Button) view.findViewById(R.id.button_delete);
		addAll = (CheckBox) view.findViewById(R.id.CB_add_all);

		getDialog().setTitle("New Exercise");

		regButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				registerExercise();
			}
		});

		delButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismissDialog();

			}
		});

		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {

			EditNameDialogListener activity = (EditNameDialogListener) getActivity();
			activity.onFinishEditDialog(ename.getText().toString());

			this.dismiss();
			exeds.close();
			exnds.close();
			return true;
		}
		return false;
	}

	public void registerExercise() {
		Exercise exe = null;
		ExerciseName exn = null;
		Workout wo = null;

		// formatting date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();

		String date = dateFormat.format(cal.getTime());
		exeds.open();
		exnds.open();
		wods.open();

		long wid = 1;
		boolean createw = true;
		// check if there already exist a workout with equal name
		if (!wods.getAllWorkouts().isEmpty()) {
			List<Workout> allw = wods.getAllWorkouts();
			wid = 1 + wods.getAllWorkouts().size();

			for (int i = 0; i < allw.size(); i++) {
				if (allw.get(i).getName()
						.equalsIgnoreCase(wname.getText().toString())) {
					wid = allw.get(i).getID();
					createw = false;
				}
			}
		}

		long nid = 1;
		boolean createe = true;
		// check if there already exist an exercise with equal name
		if (!exnds.getAllExerciseNames().isEmpty()) {
			List<ExerciseName> alle = exnds.getAllExerciseNames();
			nid = 1 + exnds.getAllExerciseNames().size();

			for (int i = 0; i < alle.size(); i++) {
				if (alle.get(i).getName()
						.equalsIgnoreCase(ename.getText().toString())) {
					nid = alle.get(i).getID();
					createe = false;
				}
			}
		}

		if (addAll.isChecked()) {
			wid = 0;
			currentPage = 0;
		}

		exe = exeds.createExercise(wid, nid, Double.parseDouble(weight.getText()
				.toString()), date, ename.getText().toString(), false);

		if (createe) {
			exn = exnds.createExerciseName(ename.getText().toString(),
					currentPage, Double.parseDouble(weight.getText().toString()),
					false);

			((MainSlider) getActivity()).updateAdapter();
		}
		if (createw)
			wo = wods.createWorkout(wname.getText().toString());

		Log.d(EXE_TAG, "Clicked register");

		Toast.makeText(getActivity(),
				ename.getText().toString() + " registered!", Toast.LENGTH_SHORT)
				.show();
		wname.setText("");
		ename.setText("");
		weight.setText("");
		exeds.close();
		exnds.close();

	}

	public void dismissDialog() {
		EditNameDialogListener activity = (EditNameDialogListener) getActivity();
		activity.onFinishEditDialog("Done!");
		this.dismiss();
	}

}
