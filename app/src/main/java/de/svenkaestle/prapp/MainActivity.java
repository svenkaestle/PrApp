package de.svenkaestle.prapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int STOCK = 10;
    public static final Boolean safety = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeSafety();
        initializeCustomCalendar();
        initializeStock();
        initializeBottomBar();

    } // end onCreate

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
            textViewSafety.setText("Have fun");
        } else {
            textViewSafety.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.safety_red));
            textViewSafety.setText("Not safe");
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
