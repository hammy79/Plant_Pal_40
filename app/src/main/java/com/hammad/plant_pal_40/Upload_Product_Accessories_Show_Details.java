package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

public class Upload_Product_Accessories_Show_Details extends AppCompatActivity {

    TextView Access_name, Access_usage_of_instr, Access_desc, Acess_stock,Acess_price;
    ImageView imageView;

    MaterialButton buttonincrement , buttondecerement;

    MaterialButton addtocartbtnprdtaccess;

    private int value = 1;

    TextView showvalue,Acess_price1,priceaddtocart;


    int total_price;

    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product_accessories_show_details);
        Initilization();
    }

    private void Initilization(){

        Intent intent =getIntent();

        Access_name=findViewById(R.id.detailedaccesname);
        Access_usage_of_instr=findViewById(R.id.detailedaccespc);
        Access_desc=findViewById(R.id.detailedaccespci);
        Acess_stock=findViewById(R.id.detailedstockaccespgh);
        Acess_price=findViewById(R.id.detailedaccespriceps);
        Acess_price1=findViewById(R.id.quantityaddtocartaccess);
        priceaddtocart=findViewById(R.id.totalpriceprdtaccess);
//        total_price=findViewById(R.id.totalpriceprdtaccess);
        imageView = findViewById(R.id.accessprdtdetailimage);
        cardView=findViewById(R.id.cardviewdeatiledcheckoutaccess);


        showvalue=findViewById(R.id.showvalueacess);
        buttonincrement=findViewById(R.id.incrementaccess);
        buttondecerement=findViewById(R.id.decrementaccess);
        addtocartbtnprdtaccess=findViewById(R.id.addtocartbtnprdtaccess);

        addtocartbtnprdtaccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Add_To_Cart_Accessories.class));
            }
        });

        String result = String.valueOf(value);
        showvalue.setText(result);
        Acess_price.setText(result);



        Access_name.setText("Accessories Name : "+ intent.getStringExtra("a_name"));
        Access_usage_of_instr.setText("Usage Instruction : "+ intent.getStringExtra("a_usageinstru"));
        Access_desc.setText("Accessories Descripation : "+ intent.getStringExtra("a_desc"));
        Acess_stock.setText("Stock : "+ intent.getStringExtra("a_stock"));
        Acess_price.setText("Price : "+ intent.getStringExtra("a_price"));
//        Acess_price1.setText(intent.getStringExtra("a_price"));
        total_price = Integer.parseInt(intent.getStringExtra("a_price"));

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
                Acess_price1.setText(result);
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
                Acess_price1.setText(result);
                int total = total_price*value;
                String stotal = String.valueOf(total);
                priceaddtocart.setText(stotal);




            }
        });


    }


}