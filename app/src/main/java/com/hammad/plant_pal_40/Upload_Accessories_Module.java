package com.hammad.plant_pal_40;

public class Upload_Accessories_Module {
    private String A_name, A_usageinstru, A_desc, A_stock,A_price,imageAid;

    public Upload_Accessories_Module() {
    }

    public Upload_Accessories_Module(String a_name, String a_usageinstru, String a_desc, String a_stock, String a_price, String imageAid) {
        A_name = a_name;
        A_usageinstru = a_usageinstru;
        A_desc = a_desc;
        A_stock = a_stock;
        A_price = a_price;
        this.imageAid = imageAid;
    }

    public String getA_name() {
        return A_name;
    }

    public void setA_name(String a_name) {
        A_name = a_name;
    }

    public String getA_usageinstru() {
        return A_usageinstru;
    }

    public void setA_usageinstru(String a_usageinstru) {
        A_usageinstru = a_usageinstru;
    }

    public String getA_desc() {
        return A_desc;
    }

    public void setA_desc(String a_desc) {
        A_desc = a_desc;
    }

    public String getA_stock() {
        return A_stock;
    }

    public void setA_stock(String a_stock) {
        A_stock = a_stock;
    }

    public String getA_price() {
        return A_price;
    }

    public void setA_price(String a_price) {
        A_price = a_price;
    }

    public String getImageAid() {
        return imageAid;
    }

    public void setImageAid(String imageAid) {
        this.imageAid = imageAid;
    }
}
