package com.hammad.plant_pal_40;

public class Upload_Products_Module {

    private String userID,plant_name, plant_characteristics, plant_care_instruction, plant_growth_habits,plant_stock,plant_price,imageAid;

    public Upload_Products_Module() {
    }

    public Upload_Products_Module(String userID, String plant_name, String plant_characteristics, String plant_care_instruction, String plant_growth_habits, String plant_stock, String plant_price, String imageAid) {
        this.userID = userID;
        this.plant_name = plant_name;
        this.plant_characteristics = plant_characteristics;
        this.plant_care_instruction = plant_care_instruction;
        this.plant_growth_habits = plant_growth_habits;
        this.plant_stock = plant_stock;
        this.plant_price = plant_price;
        this.imageAid = imageAid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getPlant_characteristics() {
        return plant_characteristics;
    }

    public void setPlant_characteristics(String plant_characteristics) {
        this.plant_characteristics = plant_characteristics;
    }

    public String getPlant_care_instruction() {
        return plant_care_instruction;
    }

    public void setPlant_care_instruction(String plant_care_instruction) {
        this.plant_care_instruction = plant_care_instruction;
    }

    public String getPlant_growth_habits() {
        return plant_growth_habits;
    }

    public void setPlant_growth_habits(String plant_growth_habits) {
        this.plant_growth_habits = plant_growth_habits;
    }

    public String getPlant_stock() {
        return plant_stock;
    }

    public void setPlant_stock(String plant_stock) {
        this.plant_stock = plant_stock;
    }

    public String getPlant_price() {
        return plant_price;
    }

    public void setPlant_price(String plant_price) {
        this.plant_price = plant_price;
    }

    public String getImageAid() {
        return imageAid;
    }

    public void setImageAid(String imageAid) {
        this.imageAid = imageAid;
    }
}
