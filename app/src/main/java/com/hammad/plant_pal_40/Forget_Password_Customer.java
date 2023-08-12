package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Password_Customer extends AppCompatActivity {

    TextInputEditText forEmail;
    MaterialButton forgot;
    String email;
    FirebaseAuth Mauth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_customer);
        Initilization();
    }
    public void  Initilization(){

        forEmail=findViewById(R.id.forgetloginemail);
        forgot=findViewById(R.id.forgetpasswordbtn);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Reset Your Password");
        dialog.setMessage("Please wait a moment...");





        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=forEmail.getText().toString();

                if (email.equals("")){
                    Toast.makeText(Forget_Password_Customer.this, "Please Enter A Valid Email", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }else {
                    Forget_Password();
                    dialog.show();

                }


            }
        });

    }

    private void Forget_Password() {

        Mauth=FirebaseAuth.getInstance();
        Mauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(Forget_Password_Customer.this, "Please Check Your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    dialog.dismiss();
                    finish();
                }else {
                    Toast.makeText(Forget_Password_Customer.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });

    }

    public void forgetpasswordbacklogin(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        dialog.dismiss();
        finish();
    }


}