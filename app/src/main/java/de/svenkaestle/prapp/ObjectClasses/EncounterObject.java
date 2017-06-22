package de.svenkaestle.prapp.ObjectClasses;

/**
 * Created by ivan on 22.06.17.
 */

public class EncounterObject {

    /* variables */
    private int id;
    private String date;
    private String risk;
    private String partnerName;
    private String timestamp;

    /* constructor */
    public EncounterObject(int id, String date, String risk, String partnerName, String timestamp) {
        this.id = id;
        this.date = date;
        this.risk = risk;
        this.partnerName = partnerName;
        this.timestamp = timestamp;
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

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
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
                " | date: " + date +
                " | risk: " + risk +
                " | partnerName: " + partnerName +
                " | timestamp: " + timestamp;
    }

}
