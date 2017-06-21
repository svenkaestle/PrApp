package de.svenkaestle.prapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ivan on 18.06.17.
 */

class DbHelper extends SQLiteOpenHelper {

    /* database information */
    static final String DB_NAME = "prapp.db";
    static final int DB_VERSION =  1;

    /* encounter table */
    static final String TABLE_ENCOUNTER = "encounter";

    static final String COLUMN_ENCOUNTER_ID = "_id_encounter";
    static final String COLUMN_ENCOUNTER_DATE = "date";
    static final String COLUMN_ENCOUNTER_RISK = "risk";
    static final String COLUMN_ENCOUNTER_PARTNERNAME = "partnerName";
    static final String COLUMN_ENCOUNTER_TIMESTAMP = "timestamp";

    /* medication table */
    static final String TABLE_MEDICATION = "medication";

    static final String COLUMN_MEDICATION_ID = "_id_medication";
    static final String COLUMN_MEDICATION_NAME = "name";
    static final String COLUMN_MEDICATION_NUMBER = "number";
    static final String COLUMN_MEDICATION_SOURCE = "source";
    static final String COLUMN_MEDICATION_TIMESTAMP = "timestamp";

    /* plan table */
    static final String TABLE_PLAN = "plan";

    static final String COLUMN_PLAN_ID = "_id_plan";
    static final String COLUMN_PLAN_STARTDATE = "startDate";
    static final String COLUMN_PLAN_ENDDATE = "endDate";
    static final String COLUMN_PLAN_DESCRIPTION = "description";
    static final String COLUMN_PLAN_TIMESTAMP = "timestamp";

    /* prep table */
    static final String TABLE_PREP = "prep";

    static final String COLUMN_PREP_ID = "_id_prep";
    static final String COLUMN_PREP_DATETIME = "dateTime";
    static final String COLUMN_PREP_TIMESTAMP = "timestamp";

    /* screening table */
    static final String TABLE_SCREENING = "screening";

    static final String COLUMN_SCREENING_ID = "_id_screening";
    static final String COLUMN_SCREENING_DATE = "date";
    static final String COLUMN_SCREENING_HIV = "hiv";
    static final String COLUMN_SCREENING_GONORRHEA = "gonorrhea";
    static final String COLUMN_SCREENING_CHLAMYDIA = "chlamydia";
    static final String COLUMN_SCREENING_SYPHILIS = "syphilis";
    static final String COLUMN_SCREENING_HEPATITISC = "hepatitisC";
    static final String COLUMN_SCREENING_TIMESTAMP = "timestamp";

    /* create the encounter tables */
    private static final String SQL_CREATE_ENCOUNTER =
            "CREATE TABLE " + TABLE_ENCOUNTER +
                    "(" + COLUMN_ENCOUNTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ENCOUNTER_DATE + " TEXT NOT NULL, " +
                    COLUMN_ENCOUNTER_RISK + " TEXT CHECK(" + COLUMN_ENCOUNTER_RISK + " IN ('n', 'l', 'm', 'h')) NOT NULL, " +
                    COLUMN_ENCOUNTER_PARTNERNAME + " TEXT NULL DEFAULT NULL, " +
                    COLUMN_ENCOUNTER_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    /* create the medication tables */
    private static final String SQL_CREATE_MEDICATION =
            "CREATE TABLE " + TABLE_MEDICATION +
                    "(" + COLUMN_MEDICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MEDICATION_NAME + " TEXT NOT NULL, " +
                    COLUMN_MEDICATION_NUMBER + " INTEGER NOT NULL, " +
                    COLUMN_MEDICATION_SOURCE + " TEXT NULL DEFAULT NULL, " +
                    COLUMN_MEDICATION_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    /* create the plan tables */
    private static final String SQL_CREATE_PLAN =
            "CREATE TABLE " + TABLE_PLAN +
                    "(" + COLUMN_PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PLAN_STARTDATE + " DATETIME NOT NULL, " +
                    COLUMN_PLAN_ENDDATE + " DATETIME NOT NULL, " +
                    COLUMN_PLAN_DESCRIPTION + " TEXT NULL DEFAULT NULL, " +
                    COLUMN_PLAN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    /* create the prep tables */
    private static final String SQL_CREATE_PREP =
            "CREATE TABLE " + TABLE_PREP +
                    "(" + COLUMN_PREP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PREP_DATETIME + " DATETIME NOT NULL, " +
                    COLUMN_PREP_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

    /* create the screening tables */
    private static final String SQL_CREATE_SCREENING =
            "CREATE TABLE " + TABLE_SCREENING +
                    "(" + COLUMN_SCREENING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SCREENING_DATE + " DATETIME NOT NULL, " +
                    COLUMN_SCREENING_HIV + " TEXT CHECK(" + COLUMN_SCREENING_HIV + " IN ('0', 'p', 'n')) NOT NULL, " +
                    COLUMN_SCREENING_GONORRHEA + " TEXT CHECK(" + COLUMN_SCREENING_GONORRHEA + " IN ('0', 'p', 'n')) NOT NULL, " +
                    COLUMN_SCREENING_CHLAMYDIA + " TEXT CHECK(" + COLUMN_SCREENING_CHLAMYDIA + " IN ('0', 'p', 'n')) NOT NULL, " +
                    COLUMN_SCREENING_SYPHILIS + " TEXT CHECK(" + COLUMN_SCREENING_SYPHILIS + " IN ('0', 'p', 'n', 'u')) NOT NULL, " +
                    COLUMN_SCREENING_HEPATITISC + " TEXT CHECK(" + COLUMN_SCREENING_HEPATITISC + " IN ('0', 'p', 'n')) NOT NULL, " +
                    COLUMN_SCREENING_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP);";


    DbHelper(Context context) {
//        super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("DbHelper", "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d("DbHelper", "Die Tabelle " + TABLE_ENCOUNTER + " wird mit SQL-Befehl: " + SQL_CREATE_ENCOUNTER + " angelegt.");
            db.execSQL(SQL_CREATE_ENCOUNTER);
            Log.d("DbHelper", "Done: " + TABLE_ENCOUNTER);

            Log.d("DbHelper", "Die Tabelle " + TABLE_MEDICATION + " wird mit SQL-Befehl: " + SQL_CREATE_MEDICATION + " angelegt.");
            db.execSQL(SQL_CREATE_MEDICATION);
            Log.d("DbHelper", "Done: " + TABLE_MEDICATION);

            Log.d("DbHelper", "Die Tabelle " + TABLE_PLAN + " wird mit SQL-Befehl: " + SQL_CREATE_PLAN + " angelegt.");
            db.execSQL(SQL_CREATE_PLAN);
            Log.d("DbHelper", "Done: " + TABLE_PLAN);

            Log.d("DbHelper", "Die Tabelle " + TABLE_PREP + " wird mit SQL-Befehl: " + SQL_CREATE_PREP + " angelegt.");
            db.execSQL(SQL_CREATE_PREP);
            Log.d("DbHelper", "Done: " + TABLE_PREP);

            Log.d("DbHelper", "Die Tabelle " + TABLE_SCREENING + " wird mit SQL-Befehl: " + SQL_CREATE_SCREENING + " angelegt.");
            db.execSQL(SQL_CREATE_SCREENING);
            Log.d("DbHelper", "Done: " + TABLE_SCREENING);
        }
        catch (Exception ex) {
            Log.e("DbHelper", "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
