package com.example.planterria;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class plant {
    String name;
    String LightLevel;
    String waterAmount;

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

    public String getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(String waterAmount) {
        this.waterAmount = waterAmount;
    }

    public plant(String name, String lightLevel, String waterAmount) {
        this.name = name;
        LightLevel = lightLevel;
        this.waterAmount = waterAmount;
    }

    public plant(){

    }
}
