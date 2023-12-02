package com.code.model;

/**
 *
 * @author Gian
 */
public class Vehicle {

    private int ID;
    private String licensePlate;
    private String licensePlatePhoto;
    private String ownerName;
    private Date date;
    private String state;

    public Vehicle(
            int ID,
            String licensePlate,
            String licensePlatePhoto,
            String ownerName,
            Date date,
            String state
    ) {
        this.ID = ID;
        this.licensePlate = licensePlate;
        this.licensePlatePhoto = licensePlatePhoto;
        this.ownerName = ownerName;
        this.date = date;
        this.state = state;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlatePhoto() {
        return licensePlatePhoto;
    }

    public void setLicensePlatePhoto(String licensePlatePhoto) {
        this.licensePlatePhoto = licensePlatePhoto;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setName(String name) {
        this.ownerName = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "ID=" + ID
                + ", licensePlate=" + licensePlate
                + ", licensePlatePhoto=" + licensePlatePhoto
                + ", ownerName=" + ownerName
                + ", date=" + date
                + ", state=" + state + '}';
    }

}
