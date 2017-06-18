package de.svenkaestle.prapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ScreeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening);

        EditText screeningDateEditText = (EditText) findViewById(R.id.screeningDateEditView);

        EditTextCalendar editTextCalendar = new EditTextCalendar(ScreeningActivity.this, screeningDateEditText);
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
