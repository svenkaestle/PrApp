package de.svenkaestle.prapp.Helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import de.svenkaestle.prapp.ObjectClasses.DatabaseEntryObject;
import de.svenkaestle.prapp.ObjectClasses.EncounterObject;
import de.svenkaestle.prapp.ObjectClasses.MedicationObject;
import de.svenkaestle.prapp.ObjectClasses.PrEPObject;
import de.svenkaestle.prapp.R;

/**
 * Created by magratheaner on 24.06.2017.
 */

public class DayViewEntryAdapter extends RecyclerView.Adapter<DayViewEntryAdapter.ViewHolder> {

    // Save all list data to be split up into adapters
    private ArrayList<DatabaseEntryObject> entries = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View listEntry;

        public ViewHolder(View v) {
            super(v);
            listEntry = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DayViewEntryAdapter(ArrayList<DatabaseEntryObject> entries) {
        this.entries = entries;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DayViewEntryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Create adapter
        Context context = parent.getContext();
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View listEntry = inflater.inflate(R.layout.adapter_day_view_entry, parent, false);
        DayViewEntryAdapter.ViewHolder vh = new DayViewEntryAdapter.ViewHolder(listEntry);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // TODO: Copy entry contents into adapter
        DatabaseEntryObject currentEntry = entries.get(position);

        // Check type of current entry and depending on that, change certain aspects like colour
        if (currentEntry instanceof EncounterObject) {
            TextView timestampTextView = (TextView) holder.listEntry.findViewById(R.id.timestampTextView);

            holder.listEntry.setBackgroundColor(Color.parseColor("#ff0000"));
            timestampTextView.setText(((EncounterObject) currentEntry).getTimestamp());
        }
        else if (currentEntry instanceof PrEPObject) {
            holder.listEntry.setBackgroundColor(Color.parseColor("#0000ff"));
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return entries.size();
    }
}
