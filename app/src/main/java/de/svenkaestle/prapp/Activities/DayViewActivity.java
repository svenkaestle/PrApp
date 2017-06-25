package de.svenkaestle.prapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import de.svenkaestle.prapp.Helper.DayViewEntryAdapter;
import de.svenkaestle.prapp.ObjectClasses.DatabaseEntryObject;
import de.svenkaestle.prapp.R;

public class DayViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewEntryAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    // TODO: Check if cast to parent class breaks functionality of each specific object
    private DatabaseEntryObject recyclerViewDataSet[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        // TODO: Get database entries for clicked day from database

        recyclerView = (RecyclerView) findViewById(R.id.day_view_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        // specify an adapter (see also next example)
        recyclerViewEntryAdapter = new DayViewEntryAdapter(recyclerViewDataSet);
        recyclerView.setAdapter(recyclerViewEntryAdapter);

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
