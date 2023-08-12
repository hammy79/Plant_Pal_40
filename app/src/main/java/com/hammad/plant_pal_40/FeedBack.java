package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FeedBack extends AppCompatActivity {
    Map<String, Object> users = new HashMap<>();
    RadioGroup FeedbackType;
    EditText Message;
    Button BtnFeedback;
    FirebaseFirestore firebaseFirestore;

    FeedBackModel feedBackModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        FeedbackType= findViewById(R.id.feedbackType);
        Message= findViewById(R.id.idmessage);
        BtnFeedback= findViewById(R.id.btnFeedback);

        BtnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting text from our edittext fields.
                int feedbackTypeId = FeedbackType.getCheckedRadioButtonId();
                RadioButton feedbackType = findViewById(feedbackTypeId);
                String feedbackTypeStr =feedbackType.getText().toString();
                String message = Message.getText().toString();
                firebaseFirestore = FirebaseFirestore.getInstance();


                // below line is for checking whether the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(feedbackTypeStr) && TextUtils.isEmpty(message) ) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(FeedBack.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(feedbackTypeStr,message);
                }
            }
        });



    }

    private void addDatatoFirebase(String feedbacktype, String messag) {
        users.put("feedbacktype",feedbacktype);
        users.put("message",messag);
        firebaseFirestore.collection("feedbackform")
                .add(users)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(FeedBack.this, "Data send", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FeedBack.this, "Data failed", Toast.LENGTH_SHORT).show();
                    }
                });




    }
}