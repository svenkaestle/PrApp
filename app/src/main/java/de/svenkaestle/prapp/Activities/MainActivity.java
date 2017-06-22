package de.svenkaestle.prapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.svenkaestle.prapp.Database.DataSource;
import de.svenkaestle.prapp.ObjectClasses.EncounterObject;
import de.svenkaestle.prapp.ObjectClasses.MedicationObject;
import de.svenkaestle.prapp.ObjectClasses.PrEPObject;
import de.svenkaestle.prapp.R;

public class MainActivity extends AppCompatActivity {

    public static final int STOCK = 10;
    public static final Boolean safety = true;

    private DataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeSafety();
        initializeCustomCalendar();
        initializeStock();
        initializeBottomBar();

        Log.d("MainActivity", "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource(this);

    } // end onCreate

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("MainActivity", "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d("MainActivity", "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("MainActivity", "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }


    private void showAllListEntries () {

        Log.d("ITEM", "--- ENCOUNTER TABLE ---");
        List<EncounterObject> encounterObjectList = dataSource.getAllEncounterObjects();
        for (EncounterObject eo : encounterObjectList) {
            Log.d("ITEM", eo.toString());
        }

        Log.d("ITEM", "--- MEDICATION TABLE ---");
        List<MedicationObject> medicationObjectList = dataSource.getAllMedicationObjects();
        for (MedicationObject med : medicationObjectList) {
            Log.d("ITEM", med.toString());
        }

        Log.d("ITEM", "--- PREP TABLE ---");
        List<PrEPObject> prEPObjectList = dataSource.getAllPrEPObjects();
        for (PrEPObject prep : prEPObjectList) {
            Log.d("ITEM", prep.toString());
        }

//        Vorlage
//        ArrayAdapter<PrEPObject> shoppingMemoArrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_multiple_choice,
//                shoppingMemoList);
//
//        ListView shoppingMemosListView = (ListView) findViewById(R.id.listview_shopping_memos);
//        shoppingMemosListView.setAdapter(shoppingMemoArrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_links:
                startActivity(new Intent(getApplicationContext(), LinksActivity.class));
                return true;
            case R.id.menu_contact:
                startActivity(new Intent(getApplicationContext(), ContactActivity.class));
                return true;
            default:
                return true;
        }
    }

    private void initializeSafety() {
        TextView textViewSafety = (TextView) findViewById(R.id.safety);
        if (safety) {
            textViewSafety.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.safety_green));
            textViewSafety.setText("Have fun");
        } else {
            textViewSafety.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.safety_red));
            textViewSafety.setText("Not safe");
        }
    } // end initializeSafety

    private void initializeCustomCalendar() {
        CustomCalendarView calendarView = (CustomCalendarView) findViewById(R.id.calendarView);

        //Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                showDialog();
            }

            @Override
            public void onMonthChanged(Date date) {
            }
        });

        // TODO DECORATOR!!
//        List decorators = new ArrayList<>();
//        decorators.add(new ColorDecorator(this.dataSource));
//        calendarView.setDecorators(decorators);
//        calendarView.refreshCalendar(currentCalendar);

    } // end initializeCustomCalendar

    private void initializeStock() {
        TextView textViewStock = (TextView) findViewById(R.id.stock);
        textViewStock.setText("Stock: " + STOCK);
        if (STOCK > 5) {
            textViewStock.setVisibility(View.INVISIBLE);
        } else {
            textViewStock.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.safety_red));
            textViewStock.setVisibility(View.VISIBLE);
        }
    } // end initializeStock

    private void initializeBottomBar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itemPrep:
                        startActivity(new Intent(getApplicationContext(), PrEPActivity.class));
                        return true;
                    case R.id.itemStock:
                        startActivity(new Intent(getApplicationContext(), StockActivity.class));
                        return true;
                    case R.id.itemScreening:
                        startActivity(new Intent(getApplicationContext(), ScreeningActivity.class));
                        return true;
//                    case R.id.itemLinks:
//                        startActivity(new Intent(getApplicationContext(), LinksActivity.class));
//                        break;
                    default:
                        return true;
                }
            }
        });


    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Add Something.");

        Button btnEncounters = (Button) dialog.findViewById(R.id.encounter);
        btnEncounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EncounterActivity.class));
                dialog.dismiss();
            }
        });

        Button btnPlanning = (Button) dialog.findViewById(R.id.plan);
        btnPlanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PlansActivity.class));
                dialog.dismiss();
            }
        });

        Button btnScreening = (Button) dialog.findViewById(R.id.screening);
        btnScreening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScreeningActivity.class));
                dialog.dismiss();
            }
        });

        dialog.show();
    } // end showDialog

    private void decorateCalendar() {

    }




} // end class MainActivity
