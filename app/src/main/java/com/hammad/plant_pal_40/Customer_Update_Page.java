package com.hammad.plant_pal_40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Customer_Update_Page extends AppCompatActivity {

    EditText fname,username,email,adress,age,gender,password,confirmpassword;
    Button add;
    String Sfname,Susername,Semail,Saddress,Sage,Sgender,Spassword,Sconfirmpassword,id;
    DatabaseReference db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update_page);
        Initilization();
    }

    private void Initilization(){

        fname = findViewById(R.id.updatecustomerfullname);
        username = findViewById(R.id.updatecustomerusername);
        email = findViewById(R.id.updatecustomeremail);
        adress = findViewById(R.id.customeraddress);
        age = findViewById(R.id.customerage);
        gender = findViewById(R.id.customergender);
        password = findViewById(R.id.updatecustomerpassword);
        confirmpassword=findViewById(R.id.updateconfirmpass);
        add=findViewById(R.id.btn_Update);

        Intent intent = getIntent();

        String ssid = intent.getStringExtra("id");
        String ssname = intent.getStringExtra("fullname");
        String ssusername = intent.getStringExtra("username");
        String ssemail = intent.getStringExtra("email");
        String ssaddress = intent.getStringExtra("address");
        String ssage = intent.getStringExtra("age");
        String ssgender = intent.getStringExtra("gender");
        String sspassword = intent.getStringExtra("password");
        String ssconfirmpassword = intent.getStringExtra("confirmpassword");



        fname.setText(ssname);
        username.setText(ssusername);
        email.setText(ssemail);
        adress.setText(ssaddress);
        age.setText(ssage);
        gender.setText(ssgender);
        password.setText(sspassword);
        confirmpassword.setText(ssconfirmpassword);



        db = FirebaseDatabase.getInstance().getReference().child("User_Customer_Created");


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getValidations(view,ssid);
            }
        });

    }

    private void getValidations(View view , String id){

        Sfname = fname.getText().toString();
        Susername = username.getText().toString();
        Semail= email.getText().toString();
        Saddress = adress.getText().toString();
        Sage = age.getText().toString();
        Sgender = gender.getText().toString();
        Spassword = password.getText().toString();
        Sconfirmpassword = confirmpassword.getText().toString();



        if (Sfname.isEmpty()){
            Snackbar.make(view , "please enter your name " , Snackbar.LENGTH_LONG).show();
        } else if (Susername.isEmpty()) {
            Snackbar.make(view , "please enter your email " , Snackbar.LENGTH_LONG).show();
        }else if (Semail.isEmpty()) {
            Snackbar.make(view , "please enter your email " , Snackbar.LENGTH_LONG).show();
        }else if (Saddress.isEmpty()) {
            Snackbar.make(view , "please enter your email " , Snackbar.LENGTH_LONG).show();
        }else if (Sage.isEmpty()) {
            Snackbar.make(view , "please enter your email " , Snackbar.LENGTH_LONG).show();
        }else if (Sgender.isEmpty()) {
            Snackbar.make(view, "please enter your phone ", Snackbar.LENGTH_LONG).show();
        }else if (Spassword.isEmpty()) {
            Snackbar.make(view, "please enter your phone ", Snackbar.LENGTH_LONG).show();
        }else if (Sconfirmpassword.isEmpty()) {
            Snackbar.make(view, "please enter your password ", Snackbar.LENGTH_LONG).show();
        }

        else {

            db.child(id).setValue(new Customer_Module(id,Sfname,Susername,Semail,Saddress,Sage,Sgender,Spassword,Sconfirmpassword));
            Snackbar.make(view , "Update Data " , Snackbar.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), Customer_Details.class));
            finish();
            Clear();

        }


    }

    private void Clear(){

        fname.setText("");
        username.setText("");
        email.setText("");
        adress.setText("");
        age.setText("");
        gender.setText("");
        password.setText("");
        confirmpassword.setText("");

    }


}