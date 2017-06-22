package de.svenkaestle.prapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.svenkaestle.prapp.ObjectClasses.EncounterObject;
import de.svenkaestle.prapp.ObjectClasses.MedicationObject;
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
    private String[] encounterColumns = {
            DbHelper.COLUMN_ENCOUNTER_ID,
            DbHelper.COLUMN_ENCOUNTER_DATE,
            DbHelper.COLUMN_ENCOUNTER_RISK,
            DbHelper.COLUMN_ENCOUNTER_PARTNERNAME,
            DbHelper.COLUMN_ENCOUNTER_TIMESTAMP
    };
    private String[] medicationColumns = {
            DbHelper.COLUMN_MEDICATION_ID,
            DbHelper.COLUMN_MEDICATION_NAME,
            DbHelper.COLUMN_MEDICATION_NUMBER,
            DbHelper.COLUMN_MEDICATION_SOURCE,
            DbHelper.COLUMN_MEDICATION_TIMESTAMP
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

    /* methods to insert data into the database */

    public PrEPObject insertPrEPObject(String dateTime) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PREP_DATETIME, stringToDateTime(dateTime));

        long insertId = database.insert(DbHelper.TABLE_PREP, null, values);

        Cursor cursor = database.query(DbHelper.TABLE_PREP,
                prepColumns, DbHelper.COLUMN_PREP_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        PrEPObject prEPObject = cursorToPrEPObject(cursor);
        cursor.close();

        return prEPObject;
    }

    public void insertEncounterObject(String date, String risk, String partnerName) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ENCOUNTER_DATE, stringToDate(date));
        values.put(DbHelper.COLUMN_ENCOUNTER_PARTNERNAME, partnerName);

        switch (risk) {
            case "No risk":
                risk = "n";
                break;
            case "Low risk":
                risk = "l";
                break;
            case "Medium risk":
                risk = "m";
                break;
            case "High risk":
                risk = "h";
                break;
        }
        values.put(DbHelper.COLUMN_ENCOUNTER_RISK, risk);

        database.insert(DbHelper.TABLE_ENCOUNTER, null, values);
    }

    public void insertMedicationObject(String name, int number, String source) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_MEDICATION_NAME, name);
        values.put(DbHelper.COLUMN_MEDICATION_NUMBER, number);
        values.put(DbHelper.COLUMN_MEDICATION_SOURCE, source);

        database.insert(DbHelper.TABLE_MEDICATION, null, values);
    }


    /* methods to get index positions */
    private PrEPObject cursorToPrEPObject(Cursor cursor) {

        int idIndex = cursor.getColumnIndex(DbHelper.COLUMN_PREP_ID);
        int idDateTime = cursor.getColumnIndex(DbHelper.COLUMN_PREP_DATETIME);
        int idTimestamp = cursor.getColumnIndex(DbHelper.COLUMN_PREP_TIMESTAMP);

        String dateTime = cursor.getString(idDateTime);
        String timestamp = cursor.getString(idTimestamp);
        int id = cursor.getInt(idIndex);

        return new PrEPObject(id, dateTime, timestamp);

    }

    private EncounterObject cursorToEncounterObject(Cursor cursor) {

        int idIndex = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ENCOUNTER_ID);
        int idDate = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ENCOUNTER_DATE);
        int idRisk = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ENCOUNTER_RISK);
        int idPartnerName = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ENCOUNTER_PARTNERNAME);
        int idTimestamp = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ENCOUNTER_TIMESTAMP);

        int id = cursor.getInt(idIndex);
        String date = cursor.getString(idDate);
        String risk = cursor.getString(idRisk);
        String partnerName = cursor.getString(idPartnerName);
        String timestamp = cursor.getString(idTimestamp);

        return new EncounterObject(id, date, risk, partnerName, timestamp);

    }

    private MedicationObject cursorToMedicationObject(Cursor cursor) {

        int idIndex = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_MEDICATION_ID);
        int idName = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_MEDICATION_NAME);
        int idNumber = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_MEDICATION_NUMBER);
        int idSource = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_MEDICATION_SOURCE);
        int idTimestamp = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_MEDICATION_TIMESTAMP);

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(idName);
        int number = cursor.getInt(idNumber);
        String source = cursor.getString(idSource);
        String timestamp = cursor.getString(idTimestamp);

        return new MedicationObject(id, name,number, source, timestamp);

    }

    /* methods to read data from the database */
    public List<PrEPObject> getAllPrEPObjects() {
        List<PrEPObject> prEPObjectList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_PREP,
                prepColumns, null, null, null, null, null);

        cursor.moveToFirst();
        PrEPObject prEPObject;

        while(!cursor.isAfterLast()) {
            prEPObject = cursorToPrEPObject(cursor);
            prEPObjectList.add(prEPObject);
            Log.d("DataSource", "ID: " + prEPObject.getId() + ", Inhalt: " + prEPObject.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return prEPObjectList;

    }

    public List<EncounterObject> getAllEncounterObjects() {
        List<EncounterObject> encounterObjectList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_ENCOUNTER,
                encounterColumns, null, null, null, null, null);

        EncounterObject encounterObject;
        while(cursor.moveToNext()) {
            encounterObject = cursorToEncounterObject(cursor);
            encounterObjectList.add(encounterObject);
            Log.d("DataSource", "ID: " + encounterObject.getId() + ", Inhalt: " + encounterObject.toString());
        }
        cursor.close();

        return encounterObjectList;

    }

    public List<MedicationObject> getAllMedicationObjects() {
        List<MedicationObject> medicationObjectList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_MEDICATION,
                medicationColumns, null, null, null, null, null);

        MedicationObject medicationObject;
        while(cursor.moveToNext()) {
            medicationObject = cursorToMedicationObject(cursor);
            medicationObjectList.add(medicationObject);
            Log.d("DataSource", "ID: " + medicationObject.getId() + ", Inhalt: " + medicationObject.toString());
        }
        cursor.close();

        return medicationObjectList;

    }

    private String stringToDateTime(String s) {

        String[] parts = s.split("\\.|\\s");
        return  parts[2] + "-" + parts[1] + "-" + parts[0] + " " + parts[3] + ":00";

    }

    private String stringToDate(String s) {

        String[] parts = s.split("\\.|\\s");
        return  parts[2] + "-" + parts[1] + "-" + parts[0] + " " + "00:00:00";

    }


}
