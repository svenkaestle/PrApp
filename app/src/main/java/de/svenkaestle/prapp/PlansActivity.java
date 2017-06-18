package de.svenkaestle.prapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class PlansActivity extends AppCompatActivity {

    private Calendar startCalendar, endCalendar;
    private int startYear, startMonth, startDay, endYear, endMonth, endDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        startCalendar = Calendar.getInstance();

        startYear = startCalendar.get(Calendar.YEAR);
        startMonth = startCalendar.get(Calendar.MONTH);
        startDay = startCalendar.get(Calendar.DAY_OF_MONTH);

        final EditText planningStartDateEditText = (EditText) findViewById(R.id.planningStartDateEditText );

        final DatePickerDialog datePickerDialog = new DatePickerDialog(PlansActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                showDate(planningStartDateEditText, year, month+1, day);
            }
        }, startYear, startMonth, startDay);

        showDate(planningStartDateEditText, startYear, startMonth+1, startDay);

        planningStartDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        // TODO: Relocate into own class to avoid boilerplate

        endCalendar = Calendar.getInstance();

        endYear = startCalendar.get(Calendar.YEAR);
        endMonth = startCalendar.get(Calendar.MONTH);
        endDay = startCalendar.get(Calendar.DAY_OF_MONTH);

        final EditText planningEndDateEditText = (EditText) findViewById(R.id.planningEndDateEditText );

        final DatePickerDialog endDatePickerDialog = new DatePickerDialog(PlansActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                showDate(planningEndDateEditText, year, month+1, day);
            }
        }, endYear, endMonth, endDay);

        showDate(planningEndDateEditText, endYear, endMonth+1, endDay);

        planningEndDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
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
                finish();
                return true;
        }
    }

    private void showDate(EditText editText, int year, int month, int day) {
        editText.setText(new StringBuilder().append(day).append(".")
                .append(month).append(".").append(year));
    }
}
