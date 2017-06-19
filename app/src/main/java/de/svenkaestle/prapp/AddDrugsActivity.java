package de.svenkaestle.prapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDrugsActivity extends AppCompatActivity {
    EditText medicationAmount;
    Button minus;
    Button plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drugs);
        medicationAmount = (EditText) findViewById(R.id.medicationNumberEditView);
        medicationAmount.setText("0");

        minus = (Button) findViewById(R.id.decreaseMedicationNumberButton);
        plus = (Button) findViewById(R.id.increaseMedicationNumberButton);


        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicationAmountOldValue = medicationAmount.getText().toString();
                Integer newValue = Integer.valueOf(medicationAmountOldValue)-1;
                if(newValue <= 0){
                    newValue = 0;
                }
                String medicationAmountNewValue = newValue.toString();
                medicationAmount.setText(medicationAmountNewValue);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicationAmountOldValue = medicationAmount.getText().toString();
                Integer newValue = Integer.valueOf(medicationAmountOldValue)+1;
                String medicationAmountNewValue = newValue.toString();
                medicationAmount.setText(medicationAmountNewValue);
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
}
