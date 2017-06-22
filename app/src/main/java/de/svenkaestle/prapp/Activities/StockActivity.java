package de.svenkaestle.prapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.svenkaestle.prapp.Database.DataSource;
import de.svenkaestle.prapp.R;

public class StockActivity extends AppCompatActivity {

    EditText medicationAmount;
    Button decreaseMedicationAmount;
    Button increaseMedicationAmount;
    Button addMedicationButton;

    String medicationAmountOldValue;
    String medicationAmountNewValue;
    Integer newValue;

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        // DB
        dataSource = new DataSource(this);

        medicationAmount = (EditText) findViewById(R.id.medicationNumberEditView);
        medicationAmount.setText("0");

        decreaseMedicationAmount = (Button) findViewById(R.id.decreaseMedicationNumberButton);
        increaseMedicationAmount = (Button) findViewById(R.id.increaseMedicationNumberButton);

        decreaseMedicationAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicationAmountOldValue = medicationAmount.getText().toString();
                newValue = Integer.valueOf(medicationAmountOldValue) - 1;
                if (newValue <= 0) {
                    newValue = 0;
                }
                medicationAmountNewValue = newValue.toString();
                medicationAmount.setText(medicationAmountNewValue);
            }
        });

        increaseMedicationAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicationAmountOldValue = medicationAmount.getText().toString();
                newValue = Integer.valueOf(medicationAmountOldValue) + 1;
                medicationAmountNewValue = newValue.toString();
                medicationAmount.setText(medicationAmountNewValue);
            }
        });

        addMedicationButton = (Button) findViewById(R.id.addMedicationButton);

        addMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add entered data to database
                EditText name = (EditText) findViewById(R.id.medicationNameEditView);
                EditText number = (EditText) findViewById(R.id.medicationNumberEditView);
                EditText source = (EditText) findViewById(R.id.medicationSourceEditView);

                dataSource.insertMedicationObject(name.getText().toString(), Integer.parseInt(number.getText().toString()), source.getText().toString());

                finish();
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
}
