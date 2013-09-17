package com.example.powerlift;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.powerlift.NewExerciseDialogFragment.EditNameDialogListener;

public class MainSlider extends FragmentActivity implements
		EditNameDialogListener {

	// Declare ViewPager & Adapter variables.
	private MyAdapter mAdapter;
	private ViewPager mPager;
	int position;
	private ExerciseDataSource exeds;
	private static final String EXE_TAG = "MainSlider";
	private int currentItem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);

		exeds = new ExerciseDataSource(this);
		exeds.open();

		mAdapter = new MyAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mPager.setCurrentItem(2);

	}

	public static class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return 6;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new ExerciseNameList();
			case 1:
				return new WorkoutList();
			case 2:
				return new WorkoutRegister();
			case 3:
				return new WorkoutMain();
			case 4:
				return new WorkoutA();
			case 5:
				return new WorkoutB();

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
			currentItem = mPager.getCurrentItem();
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

		/** Instantiating the DialogFragment class */
		NewExerciseDialogFragment alert = new NewExerciseDialogFragment();

		/** Creating a bundle object to store the selected item's index */
		Bundle b = new Bundle();

		/** Storing the selected item's index in the bundle object */
		b.putInt("position", position);

		/** Setting the bundle object to the dialog fragment object */
		alert.setArguments(b);

		/**
		 * Creating the dialog fragment object, which will in turn open the
		 * alert dialog window
		 */
		alert.show(manager, "new_exercise");
	}

	public void deleteEcercises() {
		exeds.open();
		exeds.deleteAllExercises();
		exeds.resetIDExercises();
		exeds.close();

		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(currentItem);

	}

	// Called when dialog is finished
	@Override
	public void onFinishEditDialog(String inputText) {
		Log.d(EXE_TAG, "Called:	onFinishEditDialog");

		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(currentItem);

	}

}