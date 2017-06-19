package de.svenkaestle.prapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Calendar;

public class PlansActivity extends AppCompatActivity {

    private Calendar startCalendar, endCalendar;
    private int startYear, startMonth, startDay, endYear, endMonth, endDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        EditText planningStartDateEditText = (EditText) findViewById(R.id.planningStartDateEditText);
        EditText planningEndDateEditText = (EditText) findViewById(R.id.planningEndDateEditText);

        EditTextCalendar startCalendarDialog = new EditTextCalendar(PlansActivity.this, planningStartDateEditText);
        EditTextCalendar endCalendarDialog = new EditTextCalendar(PlansActivity.this, planningEndDateEditText);
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
}
