package de.svenkaestle.prapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import de.svenkaestle.prapp.Helper.EditTextCalendar;
import de.svenkaestle.prapp.R;

public class EncounterActivity extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);


        // SPINNER

        final Spinner encounterRiskEstimateSpinner = (Spinner) findViewById(R.id.encounterRiskSpinner);

        ArrayAdapter<String> encounterRiskEstimateSpinnerAdapter = new ArrayAdapter<String>(EncounterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.encounterRiskEstimate));
        encounterRiskEstimateSpinner.setAdapter(encounterRiskEstimateSpinnerAdapter);


        // CALENDAR

        EditText encounterDateEditText = (EditText) findViewById(R.id.encounterDatePicker);

        EditTextCalendar editTextCalendar = new EditTextCalendar(EncounterActivity.this, encounterDateEditText);
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
