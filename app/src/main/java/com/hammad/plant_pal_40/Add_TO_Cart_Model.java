package com.hammad.plant_pal_40;

public class Add_TO_Cart_Model {

    private String UserId, UserFullName,UserName,Useremail,CartID;

    private String plant_name, plant_characteristics, plant_care_instruction, plant_growth_habits,plant_stock,plant_price , Total_price , Total_quantity;


    public Add_TO_Cart_Model(String userId, String userFullName, String userName, String useremail, String cartID, String plant_name, String plant_characteristics, String plant_care_instruction, String plant_growth_habits, String plant_stock, String plant_price, String total_price, String total_quantity) {
        this.UserId = userId;
        this.UserFullName = userFullName;
        this.UserName = userName;
        this.Useremail = useremail;
        this.CartID = cartID;
        this.plant_name = plant_name;
        this.plant_characteristics = plant_characteristics;
        this.plant_care_instruction = plant_care_instruction;
        this.plant_growth_habits = plant_growth_habits;
        this.plant_stock = plant_stock;
        this.plant_price = plant_price;
        this.Total_price = total_price;
        this.Total_quantity = total_quantity;
    }

    public String getTotal_price() {
        return Total_price;
    }

    public void setTotal_price(String total_price) {
        Total_price = total_price;
    }

    public String getTotal_quantity() {
        return Total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        Total_quantity = total_quantity;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserFullName() {
        return UserFullName;
    }

    public void setUserFullName(String userFullName) {
        UserFullName = userFullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUseremail() {
        return Useremail;
    }

    public void setUseremail(String useremail) {
        Useremail = useremail;
    }

    public String getCartID() {
        return CartID;
    }

    public void setCartID(String cartID) {
        CartID = cartID;
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


}
