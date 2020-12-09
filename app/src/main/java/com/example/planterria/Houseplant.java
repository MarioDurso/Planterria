package com.example.planterria;

public class Houseplant extends plant{
    public String location;
    public String identifier;
    public plantStatus currentStatus = plantStatus.Fine;

    Houseplant(){
        super();
    }

    public Houseplant(String location, String identifier) {
        this.location = location;
        this.identifier = identifier;
    }

    public Houseplant(plant plant, String location, String identifier) {
        super(plant.getName(), plant.getLightLevel(), plant.getWaterAmount());
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

    @Override

    public String toString(){

        return this.getName() + "\n" +
                this.getCurrentStatus()+ "\n" +
                this.getIdentifier()+ " \n" +
                this.getLocation();

    }
}
