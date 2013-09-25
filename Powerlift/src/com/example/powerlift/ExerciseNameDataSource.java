package com.example.powerlift;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExerciseNameDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_EXERCISE_NAME_ID,
			MySQLiteHelper.COLUMN_EXERCISE_NAME_NAME,
			MySQLiteHelper.COLUMN_EXERCISE_NAME_WID,
			MySQLiteHelper.COLUMN_EXERCISE_NAME_CURRENT_WEIGHT, 
			MySQLiteHelper.COLUMN_EXERCISE_NAME_ACCOMPLISHED};

	private static final String resetID = "delete from sqlite_sequence where name='"
			+ MySQLiteHelper.TABLE_EXERCISE_NAME + "';";

	public ExerciseNameDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public ExerciseName createExerciseName(
			String name, long wid, double weight, boolean accomplished) {

		ContentValues values = new ContentValues();

		values.put(MySQLiteHelper.COLUMN_EXERCISE_NAME_NAME, name);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_NAME_WID, wid);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_NAME_CURRENT_WEIGHT, weight);
		values.put(MySQLiteHelper.COLUMN_EXERCISE_NAME_ACCOMPLISHED, accomplished);


		long insertId = database.insert(MySQLiteHelper.TABLE_EXERCISE_NAME, null,
				values);

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE_NAME,
				allColumns, MySQLiteHelper.COLUMN_EXERCISE_NAME_ID + " = "
						+ insertId, null, null, null, null);

		cursor.moveToFirst();
		ExerciseName exe = cursorToExe(cursor);
		cursor.close();
		return exe;
	}

	public void deleteExercise(long id) {


		database.delete(MySQLiteHelper.TABLE_EXERCISE_NAME,
				MySQLiteHelper.COLUMN_EXERCISE_NAME_ID + " = " + id, null);
	}

	public void deleteAllExercises() {

		database.delete(MySQLiteHelper.TABLE_EXERCISE_NAME, null, null);

	}
	
	public void resetIDExercises(){
		database.execSQL(resetID);
	}
	
	public void updateExercise(ContentValues data,  long id){	
		database.update(MySQLiteHelper.TABLE_EXERCISE_NAME, data, "_id="+id, null);
	}

	public List<ExerciseName> getAllExerciseNames() {
		List<ExerciseName> exes = new ArrayList<ExerciseName>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE_NAME,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ExerciseName exe = cursorToExe(cursor);
			exes.add(exe);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return exes;
	}
	
	public List<ExerciseName> getAllExerciseNamesWithID(long id) {
		List<ExerciseName> exes = new ArrayList<ExerciseName>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_EXERCISE_NAME,
				allColumns, "wid="+id+" or wid=0", null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ExerciseName exe = cursorToExe(cursor);
			exes.add(exe);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return exes;
	}
	


	private ExerciseName cursorToExe(Cursor cursor) {

		ExerciseName exe = new ExerciseName();
		exe.setID(cursor.getLong(0));
		exe.setName(cursor.getString(1));
		exe.setWID(cursor.getLong(2));
		exe.setWeight(cursor.getDouble(3));
		exe.setAccomplished(cursor.getInt(4)>0);

		return exe;
	}
}