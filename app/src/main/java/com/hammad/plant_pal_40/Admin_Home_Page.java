package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin_Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
    }

    public void btaddproduct(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_Product_Page.class));
    }

    public void accessoriesaddprdt(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_Accessories_Page.class));
    }


    public void showcheckuserdetailadmin(View view) {
//        startActivity(new Intent(getApplicationContext(),Admin_Customer_Details.class));

    }

    public void showproductadmin(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_Show_Product_Page.class));
    }
}