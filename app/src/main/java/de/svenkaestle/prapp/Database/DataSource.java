package de.svenkaestle.prapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.transition.Scene;
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
import de.svenkaestle.prapp.ObjectClasses.PlanObject;
import de.svenkaestle.prapp.ObjectClasses.PrEPObject;
import de.svenkaestle.prapp.ObjectClasses.ScreeningObject;

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
    private String[] planColumns = {
            DbHelper.COLUMN_PLAN_ID,
            DbHelper.COLUMN_PLAN_STARTDATE,
            DbHelper.COLUMN_PLAN_ENDDATE,
            DbHelper.COLUMN_PLAN_DESCRIPTION,
            DbHelper.COLUMN_PLAN_TIMESTAMP
    };
    private String[] screeningColumns = {
            DbHelper.COLUMN_SCREENING_ID,
            DbHelper.COLUMN_SCREENING_DATE,
            DbHelper.COLUMN_SCREENING_HIV,
            DbHelper.COLUMN_SCREENING_GONORRHEA,
            DbHelper.COLUMN_SCREENING_CHLAMYDIA,
            DbHelper.COLUMN_SCREENING_SYPHILIS,
            DbHelper.COLUMN_SCREENING_HEPATITISC,
            DbHelper.COLUMN_SCREENING_TIMESTAMP
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
        values.put(DbHelper.COLUMN_ENCOUNTER_RISK, switchLowNoMediumHeigh(risk));

        database.insert(DbHelper.TABLE_ENCOUNTER, null, values);
    }

    public void insertMedicationObject(String name, int number, String source) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_MEDICATION_NAME, name);
        values.put(DbHelper.COLUMN_MEDICATION_NUMBER, number);
        values.put(DbHelper.COLUMN_MEDICATION_SOURCE, source);

        database.insert(DbHelper.TABLE_MEDICATION, null, values);
    }

    public void insertPlanObject(String startDate, String endDate, String description) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_PLAN_STARTDATE, stringToDate(startDate));
        values.put(DbHelper.COLUMN_PLAN_ENDDATE, stringToDate(endDate));
        values.put(DbHelper.COLUMN_PLAN_DESCRIPTION, description);

        database.insert(DbHelper.TABLE_PLAN, null, values);
    }

    public void insertScreeningObject(String date, String hiv, String gonorrhea, String chlamydia, String syphilis, String hepatitisC) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_SCREENING_DATE, stringToDate(date));
        values.put(DbHelper.COLUMN_SCREENING_HIV, switchPosNegUnclear(hiv));
        values.put(DbHelper.COLUMN_SCREENING_GONORRHEA, switchPosNegUnclear(gonorrhea));
        values.put(DbHelper.COLUMN_SCREENING_CHLAMYDIA, switchPosNegUnclear(chlamydia));
        values.put(DbHelper.COLUMN_SCREENING_SYPHILIS, switchPosNegUnclear(syphilis));
        values.put(DbHelper.COLUMN_SCREENING_HEPATITISC,switchPosNegUnclear(hepatitisC));

        database.insert(DbHelper.TABLE_SCREENING, null, values);
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

    private PlanObject cursorToPlanObject(Cursor cursor) {

        int idIndex = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_PLAN_ID);
        int idStartDate = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_PLAN_STARTDATE);
        int idEndDate = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_PLAN_ENDDATE);
        int idDescription = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_PLAN_DESCRIPTION);
        int idTimestamp = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_PLAN_TIMESTAMP);

        int id = cursor.getInt(idIndex);
        String startDate = cursor.getString(idStartDate);
        String endDate = cursor.getString(idEndDate);
        String description = cursor.getString(idDescription);
        String timestamp = cursor.getString(idTimestamp);

        return new PlanObject(id, startDate, endDate, description, timestamp);
    }

    private ScreeningObject cursorToScreeningObject(Cursor cursor) {
        int idIndex = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_ID);
        int idDate = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_DATE);
        int idHiv = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_HIV);
        int idGonorrhea = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_GONORRHEA);
        int idChlamydia = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_CHLAMYDIA);
        int idSyphilis = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_SYPHILIS);
        int idHepatitisC = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_HEPATITISC);
        int idTimetamp = cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SCREENING_TIMESTAMP);

        int id = cursor.getInt(idIndex);
        String date = cursor.getString(idDate);
        String hiv = cursor.getString(idHiv);
        String gonorrhea = cursor.getString(idGonorrhea);
        String chlamydia = cursor.getString(idChlamydia);
        String syphilis = cursor.getString(idSyphilis);
        String hepatitisC = cursor.getString(idHepatitisC);
        String timestamp = cursor.getString(idTimetamp);

        return new ScreeningObject(id, date, hiv, gonorrhea, chlamydia, syphilis, hepatitisC, timestamp);
    }

    /* methods to read data from the database */
    public List<PrEPObject> getAllPrEPObjects() {
        List<PrEPObject> prEPObjectList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_PREP,
                prepColumns, null, null, null, null, null);

        cursor.moveToFirst();
        PrEPObject prEPObject;

        while (!cursor.isAfterLast()) {
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
        while (cursor.moveToNext()) {
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
        while (cursor.moveToNext()) {
            medicationObject = cursorToMedicationObject(cursor);
            medicationObjectList.add(medicationObject);
            Log.d("DataSource", "ID: " + medicationObject.getId() + ", Inhalt: " + medicationObject.toString());
        }
        cursor.close();

        return medicationObjectList;

    }

    public List<PlanObject> getAllPlanObjects() {
        List<PlanObject> planObjectList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_PLAN,
                planColumns, null, null, null, null, null);

        PlanObject planObject;
        while (cursor.moveToNext()) {
            planObject = cursorToPlanObject(cursor);
            planObjectList.add(planObject);
            Log.d("DataSource", "ID: " + planObject.getId() + ", Inhalt: " + planObject.toString());
        }
        cursor.close();

        return planObjectList;
    }

    public List<ScreeningObject> getAllScreeningObjects() {
        List<ScreeningObject> screeningObjectList = new ArrayList<>();

        Cursor cursor = database.query(DbHelper.TABLE_SCREENING,
                screeningColumns, null, null, null, null, null);

        ScreeningObject screeningObject;
        while (cursor.moveToNext()) {
            screeningObject = cursorToScreeningObject(cursor);
            screeningObjectList.add(screeningObject);
            Log.d("DataSource", "ID: " + screeningObject.getId() + ", Inhalt: " + screeningObject.toString());
        }
        cursor.close();

        return screeningObjectList;
    }

    private String stringToDateTime(String s) {

        String[] parts = s.split("\\.|\\s");
        return  parts[2] + "-" + parts[1] + "-" + parts[0] + " " + parts[3] + ":00";

    }

    private String stringToDate(String s) {

        String[] parts = s.split("\\.|\\s");
        return  parts[2] + "-" + parts[1] + "-" + parts[0] + " " + "00:00:00";

    }

    private String switchLowNoMediumHeigh(String s) {
        switch (s) {
            case "No risk":
                return "n";
            case "Low risk":
                return "l";
            case "Medium risk":
                return "m";
            case "High risk":
                return "h";
            default:
                return s;

        }
    }

    private String switchPosNegUnclear(String s) {
        switch (s) {
            case "Not done":
                return "0";
            case "Positive":
            case "Pos./Reactive":
                return "p";
            case "Unclear":
                return "u";
            case "Negative":
                return "n";
            default:
                return s;
        }
    }


}
