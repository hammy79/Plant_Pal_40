package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextInputEditText email,password;
    MaterialButton button;
    FirebaseAuth Mauth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initilization();
    }


    public void Initilization(){

        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpass);

        Mauth=FirebaseAuth.getInstance();

        dialog=new ProgressDialog(this);
        dialog.setTitle("User Account Login");
        dialog.setMessage("Please wait Your Account is Login...");

    }



    public void registerascustomer(View view) {

        startActivity(new Intent(getApplicationContext(), Registration_Page.class));
        finish();

    }

    public void btnlogin(View view) {
        AccountLogin(view);

    }

    public void AccountLogin(View view){

        String Semail = email.getText().toString();
        String Spassword = password.getText().toString();
        dialog.show();


        if (Semail.equals("admin@gmail.com") && (Spassword.equals("admin123"))){
            startActivity(new Intent(getApplicationContext(),Admin_Home_Page.class));
            finish();
            dialog.dismiss();
        }

        if (Semail.equals("")){
            Toast.makeText(this, "Please Enter Your email", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else if (Spassword.equals("")){
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else {

            Mauth.signInWithEmailAndPassword(Semail,Spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Welcome Home", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Customer_Home_Page.class));
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

    public void forgetpassword(View view) {
        startActivity(new Intent(getApplicationContext(),Forget_Password_Customer.class));
    }


//    protected void onStart() {
//        super.onStart();
//        if (Mauth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(),Upload_Show_Product_Page.class));
//        }
//
//    }



}

