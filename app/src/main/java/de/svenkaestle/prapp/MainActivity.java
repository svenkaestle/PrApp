package de.svenkaestle.prapp;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.svenkaestle.prapp.Helper.DataSource;
import de.svenkaestle.prapp.ObjectClasses.PrEPObject;

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

        PrEPObject prep = new PrEPObject(1, "2/3/2012", "12:23");
        Log.d("MainActivity", prep.toString());

        Log.d("MainActivity", "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource(this);

//        PrEPObject prEPObject = dataSource.createPrEPObject("DATE!", "TIME!2");




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
        List<PrEPObject> prEPObjectList = dataSource.getAllPrEPObjects();

//        Vorlage
//        ArrayAdapter<PrEPObject> shoppingMemoArrayAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_multiple_choice,
//                shoppingMemoList);
//
//        ListView shoppingMemosListView = (ListView) findViewById(R.id.listview_shopping_memos);
//        shoppingMemosListView.setAdapter(shoppingMemoArrayAdapter);

        for (PrEPObject prep : prEPObjectList) {
            Log.d("ITEM", prep.toString());
        }

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
            case R.id.menu_disclaimer:
                startActivity(new Intent(getApplicationContext(), DisclaimerActivity.class));
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
        } else {
            textViewSafety.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.safety_red));
        }
    } // end initializeSafety

    private void initializeCustomCalendar() {
        CustomCalendarView calendarView = (CustomCalendarView) findViewById(R.id.calendarView);
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
//        decorators.add(new );
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
                        startActivity(new Intent(getApplicationContext(), AddDrugsActivity.class));
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

        Button btnEncounters = (Button) dialog.findViewById(R.id.encounters);
        btnEncounters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EncounterActivity.class));
                dialog.dismiss();
            }
        });

        Button btnPlanning = (Button) dialog.findViewById(R.id.planning);
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




} // end class MainActivity
