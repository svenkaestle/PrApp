package de.svenkaestle.prapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

import de.svenkaestle.prapp.Helper.DataSource;

public class PrEPActivity extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day, hour, minute;
    private Button btnCancel;
    private Button btnOk;

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr_ep);

        initializeButtons();


        // CALENDAR
        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        final EditText prepDateEditText = (EditText) findViewById(R.id.prepDatePicker);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(PrEPActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                showDate(prepDateEditText, year, month+1, day);
            }
        }, year, month, day);

        showDate(prepDateEditText, year, month+1, day);

        prepDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        // TIME
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        final EditText prepTimeEditText = (EditText) findViewById(R.id.prepTimePicker);

        final TimePickerDialog timePickerDialog = new TimePickerDialog(PrEPActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                showTime(prepTimeEditText, hourOfDay, minute);
            }
        }, hour, minute, true);

        showTime(prepTimeEditText, hour, minute);

        prepTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("PrEPActivity", "Die Datenquelle wird geöffnet.");
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("PrEPActivity", "Die Datenquelle wird geschlossen.");
        dataSource.close();
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
                finish();
                return true;
        }
    }

    private void showDate(EditText editText, int year, int month, int day) {
        editText.setText(new StringBuilder().append(day).append(".")
                .append(month).append(".").append(year));
    }

    private void showTime(EditText editText, int hour, int minute) {
        Formatter formatter = new Formatter(new StringBuilder(), Locale.GERMAN);
        formatter.format("%02d:%02d", hour, minute);
        editText.setText(formatter.toString());
    }

    private void initializeButtons() {
        dataSource = new DataSource(this);
        btnCancel = (Button) findViewById(R.id.prepNO);
        btnOk = (Button) findViewById(R.id.prepOK);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = (EditText) findViewById(R.id.prepDatePicker);
                EditText time = (EditText) findViewById(R.id.prepTimePicker);

                dataSource.createPrEPObject(date.getText().toString(), time.getText().toString());

                finish();
            }
        });

    }
}
