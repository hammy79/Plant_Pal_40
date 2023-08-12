package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Upload_Product_Accessories_Show_Details extends AppCompatActivity {

    TextView Access_name, Access_usage_of_instr, Access_desc, Acess_stock,Acess_price,Acess_price1,total_price;
    ImageView imageView;
    Button button;

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
        Acess_price1=findViewById(R.id.checkoutplantaccesprice);
        total_price=findViewById(R.id.totalaccespriceprdt);
        imageView = findViewById(R.id.accessprdtdetailimage);
        cardView=findViewById(R.id.cardviewacessoriesheckout);





//        showvalue=findViewById(R.id.showvalue);
//        buttonincrement=findViewById(R.id.increment);
//        buttondecrement=findViewById(R.id.decrement);


        Access_name.setText("Accessories Name : "+ intent.getStringExtra("a_name"));
        Access_usage_of_instr.setText("Usage Instruction : "+ intent.getStringExtra("a_usageinstru"));
        Access_desc.setText("Accessories Descripation : "+ intent.getStringExtra("a_desc"));
        Acess_stock.setText("Stock : "+ intent.getStringExtra("a_stock"));
        Acess_price.setText("Price : "+ intent.getStringExtra("a_price"));
        Acess_price1.setText(intent.getStringExtra("a_price"));
        total_price.setText(intent.getStringExtra("a_price"));


        Picasso.get().load(intent.getStringExtra("imageAid")).into(imageView);

    }

}