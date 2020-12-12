package com.example.planterria;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Houseplant extends plant{
    public String location;
    public String identifier;
    public long lastWater = System.currentTimeMillis();
    public plantStatus currentStatus = plantStatus.Watered;

    Houseplant(){
        super();
    }

    public Houseplant(String location, String identifier) {
        this.location = location;
        this.identifier = identifier;
    }

    public Houseplant(plant plant, String location, String identifier) {
        super(plant.getName(), plant.getLightLevel(), plant.getPlantWaterFrequency());
        this.location = location;
        this.identifier = identifier;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public plantStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(plantStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public long getLastWater() {
        return lastWater;
    }

    public void setLastWater(long lastWater) {
        this.lastWater = lastWater;
    }

    public String getLastWaterString(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.getLastWater());

        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String stringDate = df.format(calendar.getTime());

        return stringDate;
    }

    @Override

    public String toString(){

        return "Plant name: "+ this.getName() + "\n" +
                "Plant status: "+this.getCurrentStatus()+ "\n" +
                "Last water at: " + this.getLastWaterString() + "\n" +
                "Plant identifier: "+this.getIdentifier()+ " \n" +
                "Plant location: "+this.getLocation();

    }
}
