package cf.presentsir.www.presentsir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Cursor cursor;
    public static final String DATABASE_NAME = "local_student_db";
    public static final int DATABASE_VERSION = 25;

    public static final String TABLE_OUTLET = "timetable";
    public static final String CREATE_TABLE_OUTLET = "CREATE TABLE IF NOT EXISTS " + TABLE_OUTLET + "(period_no INTEGER  , mon TEXT , tue TEXT,wed TEXT,thu TEXT, fri TEXT,sat TEXT )";
    public static final String DELETE_TABLE_OUTLET = "DROP TABLE IF EXISTS " + TABLE_OUTLET;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_OUTLET);

    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE_OUTLET);
        //Create tables again
        onCreate(db);
    }
    public String[] getPeriodsForDay(String d) {


        String selectQuery = "SELECT "+ d +" FROM  " + TABLE_OUTLET+" ;";

        System.out.println("Wut Wut"+selectQuery);
        SQLiteDatabase db  = this.getReadableDatabase();
        cursor      = db.rawQuery(selectQuery, null);
        String data      = null;
        String []ohmy=new String[10];
        cursor.moveToFirst();
        int i=0;
        if (cursor.moveToFirst()) {
            do {
               data=cursor.getString(cursor.getColumnIndex(d)); // get the data into array, or class variable
                System.out.println("table "+data);
                ohmy[i]=data;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ohmy;
    }

    public void insertData(String pno, String mon,String tue, String wed, String thu , String fri , String sat) {

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put("period_no", pno);
            values.put("mon", mon);
            values.put("tue", tue);
            values.put("wed", wed);
            values.put("thu", thu);
            values.put("fri", fri);
            values.put("sat", sat);

            // Insert Row
            long i = db.insert(TABLE_OUTLET, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();
            System.out.println("\n\n\n\nData inserted  successfully for\n\n\n\n");

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }
}