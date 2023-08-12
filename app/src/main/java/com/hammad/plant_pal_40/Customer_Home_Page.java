package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Customer_Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);
    }


    public void showuserdetails(View view) {
        startActivity(new Intent(getApplicationContext(),Customer_Details.class));
    }

    public void showproduct(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_Show_Product_Page.class));
    }

    public void feedbackcustomer(View view) {
        startActivity(new Intent(getApplicationContext(),FeedBack.class));
    }

    public void showproductaccessories(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_Product_Accessories_Show.class));
    }
}