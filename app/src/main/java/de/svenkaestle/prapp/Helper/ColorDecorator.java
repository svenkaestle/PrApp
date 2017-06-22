package de.svenkaestle.prapp.Helper;

import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.util.Date;

import de.svenkaestle.prapp.Database.DataSource;

/**
 * Created by ivan on 18.06.17.
 */

public class ColorDecorator implements DayDecorator {

    DataSource dataSource;

    public ColorDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void decorate(DayView dayView) {
        Date data = dayView.getDate();

//        dayView.setTextColor(0xffff0000);
//        dayView.setBackgroundColor(0xffff0000);
    }

}
