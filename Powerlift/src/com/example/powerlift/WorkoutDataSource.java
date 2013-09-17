package com.example.powerlift;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class WorkoutDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_WORKOUT_ID,
			MySQLiteHelper.COLUMN_WORKOUT_NAME,
};

	private static final String resetID = "delete from sqlite_sequence where name='"
			+ MySQLiteHelper.TABLE_WORKOUT + "';";

	public WorkoutDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Workout createWorkout(
			String name) {

		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_WORKOUT_NAME, name);


		long insertId = database.insert(MySQLiteHelper.TABLE_WORKOUT, null,
				values);

		Cursor cursor = database.query(MySQLiteHelper.TABLE_WORKOUT,
				allColumns, MySQLiteHelper.COLUMN_WORKOUT_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		Workout wor = cursorToWor(cursor);
		cursor.close();
		return wor;
	}

	public void deleteWorkout(Workout wor) {
		long id = wor.getID();

		database.delete(MySQLiteHelper.TABLE_WORKOUT,
				MySQLiteHelper.COLUMN_WORKOUT_ID + " = " + id, null);
	}

	public void deleteAllExercises() {

		database.delete(MySQLiteHelper.TABLE_WORKOUT, null, null);

	}
	
	public void resetIDExercises(){
		database.execSQL(resetID);
	}
	
	public void updateExercise(ContentValues data,  long id){	
		database.update(MySQLiteHelper.TABLE_WORKOUT, data, "_id="+id, null);
	}

	public List<Workout> getAllWorkouts() {
		List<Workout> wors = new ArrayList<Workout>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_WORKOUT,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Workout wor = cursorToWor(cursor);
			wors.add(wor);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return wors;
	}
	


	private Workout cursorToWor(Cursor cursor) {

		Workout wor = new Workout();
		wor.setID(cursor.getLong(0));
		wor.setName(cursor.getString(1));

		return wor;
	}
}