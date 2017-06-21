package de.svenkaestle.prapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.svenkaestle.prapp.ObjectClasses.PrEPObject;

/**
 * Created by ivan on 18.06.17.
 */

public class DataSource {

    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] prepColumns = {
            DbHelper.COLUMN_PREP_ID,
            DbHelper.COLUMN_PREP_DATETIME,
            DbHelper.COLUMN_PREP_TIMESTAMP
    };


    public DataSource(Context context) {
        Log.d("DataSource", "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new DbHelper(context);
    }

    public void open() {
        Log.d("DataSource", "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d("DataSource", "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d("DataSource", "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public PrEPObject createPrEPObject(String date, String time) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PREP_DATETIME, date);
        values.put(DbHelper.COLUMN_PREP_TIMESTAMP, time);

        long insertId = database.insert(DbHelper.TABLE_PREP, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_PREP,
                prepColumns, DbHelper.COLUMN_PREP_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        PrEPObject prEPObject = cursorToPrEPObject(cursor);
        cursor.close();

        return prEPObject;
    }

    private PrEPObject cursorToPrEPObject(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_PREP_ID);
        int idDate = cursor.getColumnIndex(DbHelper.COLUMN_PREP_DATETIME);
        int idTime = cursor.getColumnIndex(DbHelper.COLUMN_PREP_TIMESTAMP);

        String date = cursor.getString(idDate);
        String time = cursor.getString(idTime);
        int id = cursor.getInt(idIndex);

        PrEPObject prEPObject = new PrEPObject(id, date, time);

        return prEPObject;
    }

    public List<PrEPObject> getAllPrEPObjects() {
        List<PrEPObject> prEPObjectsList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_PREP,
                prepColumns, null, null, null, null, null);

        cursor.moveToFirst();
        PrEPObject prEPObject;

        while(!cursor.isAfterLast()) {
            prEPObject = cursorToPrEPObject(cursor);
            prEPObjectsList.add(prEPObject);
            Log.d("DataSource", "ID: " + prEPObject.getId() + ", Inhalt: " + prEPObject.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return prEPObjectsList;
    }




}