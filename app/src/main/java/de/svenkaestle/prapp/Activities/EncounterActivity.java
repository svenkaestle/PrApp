package de.svenkaestle.prapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import de.svenkaestle.prapp.Database.DataSource;
import de.svenkaestle.prapp.Helper.EditTextCalendar;
import de.svenkaestle.prapp.R;

public class EncounterActivity extends AppCompatActivity {

    private Calendar calendar;
    private int year, month, day;
    private Button btnSave;

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        // DB
        Log.d("EncounterActivity", "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource(this);

        // Button
        initializeButtons();


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
    protected void onResume() {
        super.onResume();

        Log.d("EncounterActivity", "Die Datenquelle wird geöffnet.");
        dataSource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("EncounterActivity", "Die Datenquelle wird geschlossen.");
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

    private void initializeButtons() {
        btnSave = (Button) findViewById(R.id.addMedicationButton);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = (EditText) findViewById(R.id.encounterDatePicker);
                Spinner risk = (Spinner) findViewById(R.id.encounterRiskSpinner);
                EditText partnerName = (EditText) findViewById(R.id.encounterPartnerNameEditView);

                dataSource.insertEncounterObject(date.getText().toString(), risk.getSelectedItem().toString(), partnerName.getText().toString());

                finish();
            }
        });
    }
}
