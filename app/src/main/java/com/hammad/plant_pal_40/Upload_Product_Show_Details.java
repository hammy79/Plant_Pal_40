package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class Upload_Product_Show_Details extends AppCompatActivity {


    TextView Dplant_name, Dplant_characteristics, Dplant_care_instruction, Dplant_growth_habits,Dplant_stock,Dplant_price1;
    ImageView imageView;

    MaterialButton buttonincrement , buttondecerement;
    MaterialButton btnaddtocart;

    private int value = 1;

    TextView showvalue,Dplant_price,priceaddtocart;

    int total_price;


    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product_show_details);
        Initilization();
    }

    @SuppressLint("WrongViewCast")
    private void Initilization(){

        Intent intent =getIntent();

        Dplant_name=findViewById(R.id.detailedplantname);
        Dplant_characteristics=findViewById(R.id.detailedpc);
        Dplant_care_instruction=findViewById(R.id.detailedpci);
        Dplant_growth_habits=findViewById(R.id.detailedpgh);
        Dplant_stock=findViewById(R.id.detailedps);
        Dplant_price1=findViewById(R.id.detailedpp);
        Dplant_price=findViewById(R.id.quantityaddtocart);
        priceaddtocart=findViewById(R.id.totalpriceprdt);
        imageView = findViewById(R.id.customerdetailimage);
        cardView=findViewById(R.id.cardviewdeatiledcheckout);
//        quantityaddtocart = findViewById(R.id.quantityaddtocart);

        showvalue=findViewById(R.id.showvalue);
        buttonincrement=findViewById(R.id.increment);
        buttondecerement=findViewById(R.id.decrement);
        btnaddtocart=findViewById(R.id.addtocartbtnprdt);

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Product_Add_To_Cart_Page.class));
            }
        });





        String result = String.valueOf(value);
        showvalue.setText(result);
        Dplant_price.setText(result);


        Dplant_name.setText("Plant Name: "+ intent.getStringExtra("plant_name"));
        Dplant_characteristics.setText("Plant Characteristics: "+ intent.getStringExtra("plant_characteristics"));
        Dplant_care_instruction.setText("Plant Care Instruction: "+ intent.getStringExtra("plant_care_instruction"));
        Dplant_growth_habits.setText("Plant Growth Habits: "+ intent.getStringExtra("plant_growth_habits"));
        Dplant_stock.setText("Plant Stock: "+ intent.getStringExtra("plant_stock"));
        Dplant_price1.setText("Plant Price: "+intent.getStringExtra("plant_price"));
//        Dplant_price.setText("Rs:" + intent.getStringExtra("plant_price"));
         total_price = Integer.parseInt(intent.getStringExtra("plant_price"));



        Picasso.get().load(intent.getStringExtra("imageAid")).into(imageView);

        SubPart();
    }

    private void SubPart(){

        buttonincrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                value++;
//                System.out.println(value);
                String result = String.valueOf(value);
                showvalue.setText(result);
                Dplant_price.setText(result);
//                System.out.println(total_price*value);
                int total = total_price*value;
                String stotal = String.valueOf(total);
                priceaddtocart.setText(stotal);










            }
        });

//        showvalue.setText(va);



        buttondecerement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value--;
                System.out.println(value);
                String result = String.valueOf(value);
                showvalue.setText(result);
                Dplant_price.setText(result);
                int total = total_price*value;
                String stotal = String.valueOf(total);
                priceaddtocart.setText(stotal);




            }
        });


    }



}

//
//    String result = String.valueOf(value);
//                showvalue.setText(result);
//                        int total = totalprice*value;
//                        cardView.setVisibility(View.VISIBLE);
//                        String stotal = String.valueOf(total);
//                        priceaddtocart.setText( "Rs " +stotal)



//    String result = String.valueOf(value);
//                showvalue.setText(result);
//                quantityaddtocart.setText(result);
//                        int total = totalprice*value;
//                        cardView.setVisibility(View.VISIBLE);
//
//                        String stotal = String.valueOf(total);
//                        priceaddtocart.setText( "Rs " +stotal);