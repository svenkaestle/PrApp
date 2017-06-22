package de.svenkaestle.prapp.ObjectClasses;

/**
 * Created by ivan on 22.06.17.
 */

public class MedicationObject {

    /* variables */
    private int id;
    private String name;
    private int number;
    private String source;
    private String timestamp;

    /* constructor */
    public MedicationObject(int id, String name, int number, String source, String timestamp) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.source = source;
        this.timestamp = timestamp;
    }

    /* methods */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "id: " + Integer.toString(id) +
                " | name: " + name +
                " | number: " + Integer.toString(number) +
                " | source: " + source +
                " | timestamp: " + timestamp;
    }

}
