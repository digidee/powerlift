package com.example.powerlift;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Table exercise
	public static final String TABLE_EXERCISE = "exercise";
	public static final String COLUMN_EXERCISE_ID = "_id";
	public static final String COLUMN_EXERCISE_WORKOUT_ID = "_wid";
	public static final String COLUMN_EXERCISE_EXERCISE_NAME_ID = "_nid";
	public static final String COLUMN_EXERCISE_DATE = "date";
	public static final String COLUMN_EXERCISE_NAME = "name";
	public static final String COLUMN_EXERCISE_WEIGHT = "weight";
	public static final String COLUMN_EXERCISE_ACCOMPLISHED = "accomplished";

	// Table workout
	public static final String TABLE_WORKOUT = "workout";
	public static final String COLUMN_WORKOUT_ID = "_id";
	public static final String COLUMN_WORKOUT_NAME = "name";
	
	// Table Exercise name
	public static final String TABLE_EXERCISE_NAME = "exercise_name";
	public static final String COLUMN_EXERCISE_NAME_ID = "_id";
	public static final String COLUMN_EXERCISE_NAME_NAME = "name";
	public static final String COLUMN_EXERCISE_NAME_WID = "wid";
	public static final String COLUMN_EXERCISE_NAME_CURRENT_WEIGHT = "weight";
	public static final String COLUMN_EXERCISE_NAME_ACCOMPLISHED = "accomplished";

	private static final String DATABASE_NAME = "powerlift34.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement table exercise
	private static final String CREATE_TABLE_EXERCISE = "create table "
			+ TABLE_EXERCISE 
			+ "(" + COLUMN_EXERCISE_ID + " integer primary key autoincrement, "
			+ COLUMN_EXERCISE_WORKOUT_ID + " integer not null, "
			+ COLUMN_EXERCISE_EXERCISE_NAME_ID + " integer not null, "
			+ COLUMN_EXERCISE_WEIGHT + " real not null, "
			+ COLUMN_EXERCISE_DATE + " text not null, " 
			+ COLUMN_EXERCISE_NAME + " text not null, " 
			+ COLUMN_EXERCISE_ACCOMPLISHED + " boolean default 'false');";

	// Database creation sql statement table exercise
	private static final String CREATE_TABLE_WORKOUT = "create table "
			+ TABLE_WORKOUT + "(" + COLUMN_WORKOUT_ID
			+ " integer primary key autoincrement, " + COLUMN_WORKOUT_NAME
			+ " text not null);";
	
	// Database creation sql statement table exercise
	private static final String CREATE_TABLE_EXERCISE_NAME = "create table "
			+ TABLE_EXERCISE_NAME + "(" 
			+ COLUMN_EXERCISE_NAME_ID+ " integer primary key autoincrement, "
			+ COLUMN_EXERCISE_NAME_NAME	+ " text not null, " 
			+ COLUMN_EXERCISE_NAME_WID+ " integer not null, "
			+ COLUMN_EXERCISE_NAME_CURRENT_WEIGHT+ " real not null, " 
			+ COLUMN_EXERCISE_NAME_ACCOMPLISHED + " boolean default 'false');";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_EXERCISE);
		database.execSQL(CREATE_TABLE_WORKOUT);
		database.execSQL(CREATE_TABLE_EXERCISE_NAME);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
		onCreate(db);
	}

}