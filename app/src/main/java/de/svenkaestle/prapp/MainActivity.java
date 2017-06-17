package de.svenkaestle.prapp;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final int STOCK = 10;
    public static final Boolean safety = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeSafety();
        initializeCustomCalendar();
        initializeStock();

    } // end onCreate

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
                Toast.makeText(getApplicationContext(), "Date: " + date, Toast.LENGTH_SHORT).show();
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
            textViewStock.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        } else {
            textViewStock.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.safety_red));
        }
    } // end initializeStock



} // end class MainActivity
