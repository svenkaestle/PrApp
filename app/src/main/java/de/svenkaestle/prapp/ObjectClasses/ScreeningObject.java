package de.svenkaestle.prapp.ObjectClasses;

/**
 * Created by ivan on 22.06.17.
 */

public class ScreeningObject {

    /* variables */
    private int id;
    private String date;
    private String hiv;
    private String gonorrhea;
    private String chlamydia;
    private String syphilis;
    private String hepatitisC;
    private String timestamp;

    /* constructor */
    public ScreeningObject(int id, String date, String hiv, String gonorrhea, String chlamydia, String syphilis, String hepatitisC, String timestamp) {
        this.id = id;
        this.date = date;
        this.hiv = hiv;
        this.gonorrhea = gonorrhea;
        this.chlamydia = chlamydia;
        this.syphilis = syphilis;
        this.hepatitisC = hepatitisC;
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

    public String getHiv() {
        return hiv;
    }

    public void setHiv(String hiv) {
        this.hiv = hiv;
    }

    public String getGonorrhea() {
        return gonorrhea;
    }

    public void setGonorrhea(String gonorrhea) {
        this.gonorrhea = gonorrhea;
    }

    public String getChlamydia() {
        return chlamydia;
    }

    public void setChlamydia(String chlamydia) {
        this.chlamydia = chlamydia;
    }

    public String getSyphilis() {
        return syphilis;
    }

    public void setSyphilis(String syphilis) {
        this.syphilis = syphilis;
    }

    public String getHepatitisC() {
        return hepatitisC;
    }

    public void setHepatitisC(String hepatitisC) {
        this.hepatitisC = hepatitisC;
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
                " | hiv: " + hiv +
                " | gonorrhea: " + gonorrhea +
                " | chlamydia: " + chlamydia +
                " | syphilis: " + syphilis +
                " | hepatitisC: " + hepatitisC +
                " | timestamp: " + timestamp;
    }

}
