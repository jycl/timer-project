package etc.jyclapps.testproject.myapplication.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Build.VERSION;
import android.util.Log;

import etc.jyclapps.testproject.myapplication.Model.DBDate;
import etc.jyclapps.testproject.myapplication.Model.TimerDisplay;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Josh 2 on 1/21/2017.
 */

public class DatesDatabaseHelper extends SQLiteOpenHelper {

    //Database Info
    private static final String DATABASE_NAME = "datesDatabase";
    private static final int DATABASE_VERSION = 1;

    //Table Name
    private static final String TABLE_DATES = "dates";

    //Table Columns
    private static final String KEY_DATES_ID = "id";
    private static final String KEY_DATES_START_DATE = "start_date";
    private static final String KEY_DATES_END_DATE = "end_date";

    private static DatesDatabaseHelper sInstance;

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if(VERSION.SDK_INT > 15) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    //called when database is created for the first time, if DATABASE_NAME exists, it will not be called
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATES_TABLE = "CREATE TABLE " + TABLE_DATES +
                "(" +
                    KEY_DATES_ID + " INTEGER PRIMARY KEY," +
                    KEY_DATES_START_DATE + " TEXT," +
                    KEY_DATES_END_DATE + " TEXT" +
                ")";

        db.execSQL(CREATE_DATES_TABLE);
    }

    //called when database needs to be upgraded (if DATABASE VERSION is different than the database that exists on disk.)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATES);
            onCreate(db);
        }
    }

    //database will be used across application, best to use singleton pattern to avoid memory leaks or unnecessary reallocations
    //solution is to create make database instance a singleton instance
    //this getInstance() method ensures only one DatesDatabaseHelper will ever exist at any given time
    public static synchronized DatesDatabaseHelper getInstance(Context context) {
        //use application context to ensure you don't accidentally leak an Activity's context
        if(sInstance == null) {
            sInstance = new DatesDatabaseHelper(context.getApplicationContext());
        }
      return sInstance;
    }

    //constructor should be private to prevent direct instantiation
    //instead make a call to static method getInstance()
    private DatesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    CRUD OPERATIONS (Create, Read, Update, Delete)
     */

    //Insert a post into the database
    //public void addDate(DBDate date) {
    public void addDate(TimerDisplay date) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            //long id = addOrUpdateEntry(date.id);

            ContentValues values = new ContentValues();
            //values.put(KEY_DATES_ID, id);
            values.put(KEY_DATES_START_DATE, date.start_datetime);
            values.put(KEY_DATES_END_DATE, date.end_datetime);

            db.insertOrThrow(TABLE_DATES, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("", "Error encountered");
        } finally {
            db.endTransaction();
        }
    }

//    public long addOrUpdateEntry(int id) {
//
//    }

    //Querying data

    public ArrayList<TimerDisplay> getAllTimers() {
        ArrayList<TimerDisplay> list = new ArrayList<>();

        String DATES_SELECT_QUERY =
                String.format("SELECT * FROM %s",
                        TABLE_DATES);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DATES_SELECT_QUERY, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    TimerDisplay newTimer = new TimerDisplay();
                    newTimer.start_date = cursor.getString(cursor.getColumnIndex(KEY_DATES_START_DATE));
                    newTimer.end_date = cursor.getString(cursor.getColumnIndex(KEY_DATES_END_DATE));
                    list.add(newTimer);
                } while (cursor.moveToNext());
            }
            }catch (Exception e) {
            Log.d("", "error encountered during query");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return list;
    }

    public void deleteAllEntries() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // order of deletions is important if foreign key relationships exist
            db.delete(TABLE_DATES, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("", "Error encountered during deletion of entries");
        } finally {
            db.endTransaction();
        }
    }
}
