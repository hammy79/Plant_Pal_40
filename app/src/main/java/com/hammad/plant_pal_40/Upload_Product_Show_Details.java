package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class Upload_Product_Show_Details extends AppCompatActivity {


    TextView Dplant_name, Dplant_characteristics, Dplant_care_instruction, Dplant_growth_habits,Dplant_stock,Dplant_price1;
    ImageView imageView;

    MaterialButton buttonincrement , buttondecerement;
    MaterialButton btnaddtocart;

    private int value = 1;

    TextView showvalue,Dplant_price,priceaddtocart;

    int total_price;


    CardView cardView;


    DatabaseReference db;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog dialog;
    String stotal;

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


        db= FirebaseDatabase.getInstance().getReference().child("Add_TO_Cart");
        firebaseFirestore=FirebaseFirestore.getInstance();

        dialog= new ProgressDialog(this);
        dialog.setTitle("User Account Creating");
        dialog.setMessage("Please Wait Your Account is creating..!!!");





        String result = String.valueOf(value);
        showvalue.setText(result);
        Dplant_price.setText(result);


        Dplant_name.setText("Plant Name: "+ intent.getStringExtra("plant_name"));
        Dplant_characteristics.setText("Plant Characteristics: "+ intent.getStringExtra("plant_characteristics"));
        Dplant_care_instruction.setText("Plant Care Instruction: "+ intent.getStringExtra("plant_care_instruction"));
        Dplant_growth_habits.setText("Plant Growth Habits: "+ intent.getStringExtra("plant_growth_habits"));
        Dplant_stock.setText("Plant Stock: "+ intent.getStringExtra("plant_stock"));
        Dplant_price1.setText("Plant Price: "+ intent.getStringExtra("plant_price"));
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
                stotal = String.valueOf(total);
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
                stotal = String.valueOf(total);
                priceaddtocart.setText(stotal);




            }
        });


    }


    public void btnAddToCard(View view) {

        dialog.show();

        Intent intent =getIntent();

        DocumentReference documentReference = firebaseFirestore.collection("Add_TO_Cart").document();

        String id = "Cart" + new Date().getTime();

        String va = String.valueOf(value);

        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String userid = sh.getString("UserID", "");
        String useremail = sh.getString("UserEmail", "");
        String username = sh.getString("UserName", "");
        String userfullname = sh.getString("UserFUllName", "");

        Add_TO_Cart_Model model = new Add_TO_Cart_Model(
                userid,userfullname,username,useremail,id,
                intent.getStringExtra("plant_name"),
                intent.getStringExtra("plant_characteristics"),
                intent.getStringExtra("plant_care_instruction"),
                intent.getStringExtra("plant_growth_habits"),
                intent.getStringExtra("plant_stock"),
                intent.getStringExtra("plant_price"),
                stotal,va
        );

        db.child(id).setValue(model);
        documentReference.set(model);

        dialog.dismiss();

    }
}

