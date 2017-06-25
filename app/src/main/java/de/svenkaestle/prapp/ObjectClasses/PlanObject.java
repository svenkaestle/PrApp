package de.svenkaestle.prapp.ObjectClasses;

/**
 * Created by ivan on 22.06.17.
 */

public class PlanObject extends DatabaseEntryObject {

    /* variables */
    private int id;
    private String startDate;
    private String endDate;
    private String description;
    private String timestamp;

    /* constructor */
    public PlanObject(int id, String startDate, String endDate, String description, String timestamp) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.timestamp = timestamp;
    }

    /* methods */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                " | startDate: " + startDate +
                " | endDate: " + endDate +
                " | description: " + description +
                " | timestamp: " + timestamp;
    }
}
