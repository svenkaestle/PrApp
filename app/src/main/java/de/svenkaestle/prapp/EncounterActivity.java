package de.svenkaestle.prapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EncounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        final Spinner encounterRiskEstimateSpinner = (Spinner) findViewById(R.id.encounterRiskSpinner);

        ArrayAdapter<String> encounterRiskEstimateSpinnerAdapter = new ArrayAdapter<String>(EncounterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.encounterRiskEstimate));
        encounterRiskEstimateSpinner.setAdapter(encounterRiskEstimateSpinnerAdapter);


        /*
        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

        };

        edittext.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    new DatePickerDialog(classname.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
        });

        private void updateLabel() {

            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            edittext.setText(sdf.format(myCalendar.getTime()));
        }
        */
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


}
