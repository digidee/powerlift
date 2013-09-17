package com.example.powerlift;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExerciseDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_EXERCISE_ID,
			MySQLiteHelper.COLUMN_EXERCISE_WORKOUT_ID,
			MySQLiteHelper.COLUMN_EXERCISE_EXERCISE_NAME_ID,
			MySQLiteHelper.COLUMN_EXERCISE_WEIGHT,
			MySQLiteHelper.COLUMN_EXERCISE_DATE,
			MySQLiteHelper.COLUMN_EXERCISE_NAME,
			MySQLiteHelper.COLUMN_EXERCISE_ACCOMPLISHED };

	private static final String resetID = "delete from sqlite_sequence where name='"
			+ MySQLiteHelper.TABLE_EXERCISE + "';";

	public ExerciseDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Exercise createExercise(long wid, long nid, double weight, String date,
			String name, boolean accomplished) {

		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_EXERCISE_WORKOUT_ID, wid);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_EXERCISE_NAME_ID, nid);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_WEIGHT, weight);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_DATE, date);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_NAME, name);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_ACCOMPLISHED, accomplished);

		long insertId = database.insert(MySQLiteHelper.TABLE_EXERCISE, null,
				values);

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE,
				allColumns, MySQLiteHelper.COLUMN_EXERCISE_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		Exercise exe = cursorToExe(cursor);
		cursor.close();
		return exe;
	}

	public void deleteExercise(Exercise exe) {
		long id = exe.getID();

		database.delete(MySQLiteHelper.TABLE_EXERCISE,
				MySQLiteHelper.COLUMN_EXERCISE_ID + " = " + id, null);
	}

	public void deleteAllExercises() {

		database.delete(MySQLiteHelper.TABLE_EXERCISE, null, null);

	}
	
	public void resetIDExercises(){
		database.execSQL(resetID);
	}
	
	public void updateExercise(ContentValues data,  long id){	
		database.update(MySQLiteHelper.TABLE_EXERCISE, data, "_id="+id, null);
	}

	public List<Exercise> getAllExercises() {
		List<Exercise> exes = new ArrayList<Exercise>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Exercise exe = cursorToExe(cursor);
			exes.add(exe);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return exes;
	}
	
	public List<Exercise> getAllExercisesWithWID(long id) {
		List<Exercise> exes = new ArrayList<Exercise>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE,
				allColumns, "_wid="+id, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Exercise exe = cursorToExe(cursor);
			exes.add(exe);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return exes;
	}
	
	
	public List<Exercise> getAllExercisesWithNID(long id) {
		List<Exercise> exes = new ArrayList<Exercise>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE,
				allColumns, "_nid="+id, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Exercise exe = cursorToExe(cursor);
			exes.add(exe);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return exes;
	}

	private Exercise cursorToExe(Cursor cursor) {

		Exercise exe = new Exercise();
		exe.setID(cursor.getLong(0));
		exe.setWID(cursor.getLong(1));
		exe.setNID(cursor.getLong(2));
		exe.setWeight(cursor.getFloat(3));
		exe.setDate(cursor.getString(4));
		exe.setName(cursor.getString(5));
		exe.setAccomplished(cursor.getInt(6) > 0);

		return exe;
	}
}