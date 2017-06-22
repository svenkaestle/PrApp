package de.svenkaestle.prapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import de.svenkaestle.prapp.Database.DataSource;
import de.svenkaestle.prapp.Helper.EditTextCalendar;
import de.svenkaestle.prapp.R;

public class ScreeningActivity extends AppCompatActivity {

    private Button btnSave;

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening);

        // DB
        Log.d("ScreeningActivity", "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DataSource(this);

        // Button
        initializeButtons();

        EditText screeningDateEditText = (EditText) findViewById(R.id.screeningDateEditView);

        EditTextCalendar editTextCalendar = new EditTextCalendar(ScreeningActivity.this, screeningDateEditText);

        final Spinner hivSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> hivSpinnerAdapter = new ArrayAdapter<String>(ScreeningActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.HIV));
        hivSpinner.setAdapter(hivSpinnerAdapter);

        final Spinner goSpinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> goSpinnerAdapter = new ArrayAdapter<String>(ScreeningActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.GO));
        goSpinner.setAdapter(goSpinnerAdapter);

        final Spinner ctSpinner = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<String> ctSpinnerAdapter = new ArrayAdapter<String>(ScreeningActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.CT));
        ctSpinner.setAdapter(ctSpinnerAdapter);

        final Spinner sySpinner = (Spinner) findViewById(R.id.spinner6);
        ArrayAdapter<String> sySpinnerAdapter = new ArrayAdapter<String>(ScreeningActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.SY));
        sySpinner.setAdapter(sySpinnerAdapter);

        final Spinner hcvSpinner = (Spinner) findViewById(R.id.spinner7);
        ArrayAdapter<String> hcvSpinnerAdapter = new ArrayAdapter<String>(ScreeningActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.HCV));
        hcvSpinner.setAdapter(hcvSpinnerAdapter);
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
        btnSave = (Button) findViewById(R.id.addScreeningButton);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = (EditText) findViewById(R.id.screeningDateEditView);
                Spinner hiv = (Spinner) findViewById(R.id.spinner2);
                Spinner gonorrhea = (Spinner) findViewById(R.id.spinner4);
                Spinner chlamydia = (Spinner) findViewById(R.id.spinner5);
                Spinner syphilis = (Spinner) findViewById(R.id.spinner6);
                Spinner hepatitisC = (Spinner) findViewById(R.id.spinner7);

                dataSource.insertScreeningObject(date.getText().toString(), hiv.getSelectedItem().toString(), gonorrhea.getSelectedItem().toString(), chlamydia.getSelectedItem().toString(), syphilis.getSelectedItem().toString(), hepatitisC.getSelectedItem().toString());

                finish();
            }
        });
    }
}
