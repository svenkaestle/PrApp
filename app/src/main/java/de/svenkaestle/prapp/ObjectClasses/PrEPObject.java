package de.svenkaestle.prapp.ObjectClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ivan on 18.06.17.
 */

public class PrEPObject extends DatabaseEntryObject {

    /* variables */
    private int id;
    private String dateTime;
    private String timestamp;

    /* constructor */
    public PrEPObject(int id, String dateTime, String timestamp) {
        this.id = id;
        this.dateTime = dateTime;
        this.timestamp = timestamp;
    }

    /* methods */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "id: " + Integer.toString(id) + " | dateTime: " + dateTime + " | timestamp: " + timestamp;
    }
}
