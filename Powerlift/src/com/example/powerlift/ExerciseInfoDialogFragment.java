package com.example.powerlift;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.example.powerlift.NewExerciseDialogFragment.EditNameDialogListener;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

//...
public class ExerciseInfoDialogFragment extends DialogFragment implements
		OnEditorActionListener {

	private Button delButton, updateButton, closeButton;
	private int currentExe, currentPage;
	private TextView exerciseName, lastDate;
	private EditText currentWeight;
	private ExerciseDataSource exeds;
	private ExerciseNameDataSource exnds;

	private List<ExerciseName> values;
	private List<Exercise> evalues;

	private static final String EXE_TAG = "Update Exercise Dialog";

	public interface EditNameDialogListener {
		void onFinishEditDialog(String inputText);
	}

	public ExerciseInfoDialogFragment() {
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.exercise_info, container);

		currentExe = getArguments().getInt("id");
		currentPage = getArguments().getInt("currentPage");

		exnds = new ExerciseNameDataSource(getActivity());
		exnds.open();

		values = exnds.getAllExerciseNamesWithID(currentPage);

		// exerciseName = (TextView) view.findViewById(R.id.TV_current_weight);
		currentWeight = (EditText) view.findViewById(R.id.ET_current_weight);
		lastDate = (TextView) view.findViewById(R.id.TV_last_date);

		// ET_exercisename.setText("" + values.get(currentExe).getName());
		getDialog().setTitle(values.get(currentExe).getName());

		currentWeight.setText("" + values.get(currentExe).getWeight());
		// ET_last_date.setText("" + values.get(currentExe).getID());

		closeButton = (Button) view.findViewById(R.id.button_close);

		closeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismissDialog();

			}
		});

		updateButton = (Button) view.findViewById(R.id.button_update);

		updateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				updateExercise();

			}

		});

		delButton = (Button) view.findViewById(R.id.button_delete);

		delButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				delExercise();

			}

		});

		Log.d("info", "id: " + currentExe);

		return view;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (EditorInfo.IME_ACTION_DONE == actionId) {

			this.dismiss();
			exeds.close();
			exnds.close();
			return true;

		}
		return false;
	}

	public void dismissDialog() {

		this.dismiss();
	}

	private void updateExercise() {
		exnds.open();

		ContentValues data = new ContentValues();

		data.put("weight", currentWeight.getText().toString());

		exnds.updateExercise(data, values.get(currentExe).getID());

		Log.d(EXE_TAG, "Updated " + values.get(currentExe).getID() + ": "
				+ currentWeight.getText().toString());

		Toast.makeText(
				getActivity(),
				"Updated " + values.get(currentExe).getName() + ": "
						+ currentWeight.getText().toString(),
				Toast.LENGTH_SHORT).show();

		((MainSlider) getActivity()).updateAdapter();

		exnds.close();
	}

	private void delExercise() {

		exnds.open();

		exnds.deleteExercise(values.get(currentExe).getID());

		Log.d(EXE_TAG, "Deleted " + values.get(currentExe).getID());

		Toast.makeText(getActivity(),
				"Deleted " + values.get(currentExe).getName(),
				Toast.LENGTH_SHORT).show();

		((MainSlider) getActivity()).updateAdapter();
		
		this.dismiss();

	}

}
