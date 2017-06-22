package de.svenkaestle.prapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import de.svenkaestle.prapp.Database.DataSource;
import de.svenkaestle.prapp.Helper.EditTextCalendar;
import de.svenkaestle.prapp.R;

public class PlansActivity extends AppCompatActivity {

    private Calendar startCalendar, endCalendar;
    private int startYear, startMonth, startDay, endYear, endMonth, endDay;
    private Button btnSave;

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        // DB
        Log.d("PlansActivity", "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource(this);

        // Buttons
        initializeButtons();

        EditText planningStartDateEditText = (EditText) findViewById(R.id.planningStartDateEditText);
        EditText planningEndDateEditText = (EditText) findViewById(R.id.planningEndDateEditText);

        EditTextCalendar startCalendarDialog = new EditTextCalendar(PlansActivity.this, planningStartDateEditText);
        EditTextCalendar endCalendarDialog = new EditTextCalendar(PlansActivity.this, planningEndDateEditText);
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
        btnSave = (Button) findViewById(R.id.addPlanButton);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText startDate = (EditText) findViewById(R.id.planningStartDateEditText);
                EditText endDate = (EditText) findViewById(R.id.planningEndDateEditText);
                EditText description = (EditText) findViewById(R.id.planningDescriptionEditView);

                dataSource.insertPlanObject(startDate.getText().toString(), endDate.getText().toString(), description.getText().toString());

                finish();
            }
        });
    }
}
