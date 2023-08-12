package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class Registration_Page extends AppCompatActivity {

    TextInputEditText fullname,username,email,address,age,gender,password,confirmpassword;
    MaterialButton button;



    DatabaseReference db;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth Mauth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Initilization();
    }

    private void  Initilization(){

        fullname=findViewById(R.id.fname);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        address=findViewById(R.id.customeraddress);
        age=findViewById(R.id.customerage);
        gender=findViewById(R.id.customergender);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.customerconfirmpass);




        db= FirebaseDatabase.getInstance().getReference().child("User_Customer_Created");
        Mauth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        dialog= new ProgressDialog(this);
        dialog.setTitle("User Account Creating");
        dialog.setMessage("Please Wait Your Account is creating..!!!");



    }


    public void customerbtregister(View view) {
        UserAccountCreated(view);

    }


    public void UserAccountCreated(View view) {

//        String id = Mauth.getCurrentUser().getUid();


        String Sfullname = fullname.getText().toString();
        String Susername = username.getText().toString();
        String Semail = email.getText().toString();
        String Saddress = address.getText().toString();
        String Sage = age.getText().toString();
        String Sgender = gender.getText().toString();
        String Spassword = password.getText().toString();
        String Sconfirmpassword = confirmpassword.getText().toString();

        dialog.show();


        if (Sfullname.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(fullname);
            Toast.makeText(this, "Please Enter Your Full Nmae", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }else if (Susername.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(username);
            Toast.makeText(this, "Please Enter Your User Name", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }else if (Semail.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(email);
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else if (Saddress.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(address);
            Toast.makeText(this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else if (Sage.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(age);
            Toast.makeText(this, "Please Enter Your Age", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else if (Sgender.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(gender);
            Toast.makeText(this, "Please Enter Your Gender", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else if (Spassword.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(password);
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else if (Sconfirmpassword.equals("")) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(confirmpassword);
            Toast.makeText(this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else if (!Spassword.equals(Sconfirmpassword)) {
            YoYo.with(Techniques.Shake).duration(1000).repeat(2).playOn(confirmpassword);
            Toast.makeText(this, "Please Check Your Password Not Match", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else {
            DocumentReference documentReference = firebaseFirestore.collection("User_Customer_Created").document();

            Mauth.createUserWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    String id = "User" + new Date().getTime();



                    if (task.isSuccessful()) {
                        Customer_Module obj = new Customer_Module(id,Sfullname,Susername, Semail,Saddress, Sage, Sgender, Spassword, Sconfirmpassword);
                        db.child(id).setValue(obj);
                        documentReference.set(obj);
                        Toast.makeText(getApplicationContext(), "Customer Account created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        dialog.dismiss();
                        finish();




                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
    }


    public void alreadyhaveaccountcustomer(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        dialog.dismiss();
        finish();
    }

}