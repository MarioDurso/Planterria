package com.example.planterria;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class plant {
    String name;
    String LightLevel;
    waterFrequency plantWaterFrequency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLightLevel() {
        return LightLevel;
    }

    public void setLightLevel(String lightLevel) {
        LightLevel = lightLevel;
    }

    public waterFrequency getPlantWaterFrequency() {
        return plantWaterFrequency;
    }

    public void setPlantWaterFrequency(waterFrequency plantWaterFrequency) {
        this.plantWaterFrequency = plantWaterFrequency;
    }

    public plant(String name, String lightLevel, waterFrequency waterAmount) {
        this.name = name;
        LightLevel = lightLevel;
        this.plantWaterFrequency = waterAmount;
    }

    public plant(){



    }

    @Override
    public String toString(){
        return "Plant name: "+ this.getName() + "\n" +
                "Plant Light level: "+this.getLightLevel()+ "\n" +
                "Plant water Frequency: "+this.getPlantWaterFrequency();
    }
}
