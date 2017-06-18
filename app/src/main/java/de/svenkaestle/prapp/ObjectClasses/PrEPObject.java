package de.svenkaestle.prapp.ObjectClasses;

/**
 * Created by ivan on 18.06.17.
 */

public class PrEPObject {

    /* variables */
    private int id;
    private String date;
    private String time;

    /* constructor */
    public PrEPObject(int id, String date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    /* methods */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String output = date + " - " + time;
        return output;
    }
}
