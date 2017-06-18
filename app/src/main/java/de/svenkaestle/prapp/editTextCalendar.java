package de.svenkaestle.prapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Lukas Golombek on 6/18/17.
 */

public class editTextCalendar {

    private Calendar calendar = null;
    private DatePickerDialog datePickerDialog = null;
    private int year = 1987, month = 01, day = 01;

    editTextCalendar(Context context, final EditText editText) {

        calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                showDate(editText, year, month+1, day);
            }
        }, year, month, day);

        showDate(editText, year, month+1, day);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }

    private void showDate(EditText editText, int year, int month, int day) {
        editText.setText(new StringBuilder().append(day).append(".")
                .append(month).append(".").append(year));
    }
}
