package com.example.powerlift;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.powerlift.NewExerciseDialogFragment.EditNameDialogListener;
import com.viewpagerindicator.CirclePageIndicator;

public class MainSlider extends FragmentActivity implements
		EditNameDialogListener {

	// Declare ViewPager & Adapter variables.
	private MyAdapter mAdapter;
	private ViewPager mPager;

	private ActionBar actionBar;
	private ExerciseDataSource exeds;
	private ExerciseNameDataSource exnds;
	private WorkoutDataSource wods;
	private static final String EXE_TAG = "MainSlider";
	private int currentItem, position, currentPage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);

		exeds = new ExerciseDataSource(this);
		exeds.open();

		mAdapter = new MyAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		// Bind the title indicator to the adapter
		CirclePageIndicator circle = (CirclePageIndicator) findViewById(R.id.indicator);
		circle.setStrokeColor(Color.BLACK);
		circle.setViewPager(mPager);

		mPager.setCurrentItem(1);

	}

	public static class MyAdapter extends FragmentPagerAdapter {

		int pos;

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 5;
		}

		@Override
		public Fragment getItem(int position) {

			WorkoutA wa = new WorkoutA();
			Bundle arguments = new Bundle();
			arguments.putInt("wid", position);
			wa.setArguments(arguments);

			Log.d("info", "wid: " + position);

			switch (position) {

			case 0:

				return new WorkoutMain();
			case 1:

				return wa;
			case 2:

				return wa;
			case 3:

				return new WorkoutB();
				// case 4:
				// return new WorkoutList();
			case 4:

				return new WorkoutRegister();

			default:
				return null;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				// Yes button clicked
				deleteEcercises();

				break;

			case DialogInterface.BUTTON_NEGATIVE:
				// No button clicked
				break;
			}
		}
	};

	@Override
	protected void onResume() {

		super.onResume();
		// this.onCreate(null);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.menu_new_exercise:
			startNewExerciseDialog();

			return true;

		case R.id.menu_reset:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure?")
					.setPositiveButton("Yes", dialogClickListener)
					.setNegativeButton("No", dialogClickListener).show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void startNewExerciseDialog() {
		currentItem = mPager.getCurrentItem();
		FragmentManager manager = getSupportFragmentManager();
		NewExerciseDialogFragment newexe = new NewExerciseDialogFragment();
		Bundle b = new Bundle();
		b.putInt("position", position);
		b.putInt("currentPage", mPager.getCurrentItem());
		newexe.setArguments(b);
		newexe.show(manager, "new_exercise");
	}

	public void startExerciseDialog(int id) {
		currentItem = mPager.getCurrentItem();
		FragmentManager manager = getSupportFragmentManager();
		ExerciseInfoDialogFragment exeinfo = new ExerciseInfoDialogFragment();
		Bundle b = new Bundle();
		b.putInt("id", id);
		b.putInt("currentPage", mPager.getCurrentItem());
		exeinfo.setArguments(b);
		exeinfo.show(manager, "exercise_info");
	}

	public void deleteEcercises() {
		exeds.open();

		exnds = new ExerciseNameDataSource(this);
		wods = new WorkoutDataSource(this);

		exnds.open();
		wods.open();

		exnds.deleteAllExercises();
		exnds.resetIDExercises();

		wods.deleteAllExercises();
		wods.resetIDExercises();

		exeds.deleteAllExercises();
		exeds.resetIDExercises();

		exeds.close();
		exnds.close();
		wods.close();

		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(currentItem);

	}

	// Called when dialog is finished
	@Override
	public void onFinishEditDialog(String inputText) {
		Log.d(EXE_TAG, "Called:	onFinishEditDialog");

		// mPager.setAdapter(mAdapter);
		// mPager.setCurrentItem(currentItem);

	}

	public void updateAdapter() {
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(currentItem);
	}

}