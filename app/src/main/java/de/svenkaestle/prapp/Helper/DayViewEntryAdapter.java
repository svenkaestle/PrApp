package de.svenkaestle.prapp.Helper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.svenkaestle.prapp.ObjectClasses.DatabaseEntryObject;
import de.svenkaestle.prapp.ObjectClasses.EncounterObject;
import de.svenkaestle.prapp.ObjectClasses.MedicationObject;
import de.svenkaestle.prapp.ObjectClasses.PlanObject;
import de.svenkaestle.prapp.ObjectClasses.PrEPObject;
import de.svenkaestle.prapp.ObjectClasses.ScreeningObject;
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
        DatabaseEntryObject currentEntry = entries.get(position);

        TextView timestampTextView = (TextView) holder.listEntry.findViewById(R.id.dayViewEntryTime);
        TextView titleTextView = (TextView) holder.listEntry.findViewById(R.id.dayViewEntryTitle);
        TextView descriptionTextView = (TextView) holder.listEntry.findViewById(R.id.dayViewEntryDescription);

        // - replace the contents of the view with that element
        // Check type of current entry and depending on that, change certain aspects like colour
        if (currentEntry instanceof EncounterObject) {
            EncounterObject currentEncounterEntry = (EncounterObject) currentEntry;

            // holder.listEntry.setBackgroundColor(Color.parseColor("#ff0000"));
            timestampTextView.setText(currentEncounterEntry.getTimestamp());
            titleTextView.setText("Encounter");
            descriptionTextView.setText("Risk: ".concat(currentEncounterEntry.getRisk()).concat("\nPartner name: ").concat(currentEncounterEntry.getPartnerName()));

        }

        else if (currentEntry instanceof PrEPObject) {
            PrEPObject currentPrEPEntry = (PrEPObject) currentEntry;

            holder.listEntry.setBackgroundColor(Color.parseColor("#E60000"));

            timestampTextView.setText(currentPrEPEntry.getTimestamp());
            titleTextView.setText("Meds taken");
            descriptionTextView.setVisibility(View.GONE);
        }

        else if (currentEntry instanceof MedicationObject) {
            MedicationObject currentMedicationEntry = (MedicationObject) currentEntry;

            holder.listEntry.setBackgroundColor(Color.parseColor("#2196f3"));

            // timestampTextView.setText(currentMedicationEntry.getTimestamp());
            timestampTextView.setText("16:00");
            titleTextView.setText("Stock refreshed");
            descriptionTextView.setText("Type: ".concat(currentMedicationEntry.getName().concat("\nNumber added: ".concat(Integer.toString(currentMedicationEntry.getNumber())))));
        }

        else if (currentEntry instanceof PlanObject) {
            PlanObject currentPlanEntry = (PlanObject) currentEntry;

            holder.listEntry.setBackgroundColor(Color.parseColor("#ffc107"));

            // TODO: Change time format in database object
            // timestampTextView.setText(currentPlanEntry.getTimestamp());
            timestampTextView.setText("23:00");
            // TODO: Add event name to plan activity and database object
            titleTextView.setText("CSD Winter Edition");
            descriptionTextView.setText(currentPlanEntry.getDescription());
        }

        else if (currentEntry instanceof ScreeningObject) {
            ScreeningObject currentScreeningEntry = (ScreeningObject) currentEntry;

            holder.listEntry.setBackgroundColor(Color.parseColor("#ff5722"));

            // timestampTextView.setText(currentScreeningEntry.getTimestamp());
            timestampTextView.setText("11:00");
            titleTextView.setText("Screening");
            descriptionTextView.setText("Tests done: ");
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return entries.size();
    }
}
