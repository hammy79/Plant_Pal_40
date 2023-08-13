package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Customer_Details extends AppCompatActivity {

    RecyclerView recyclerView;
    Customer_details_Adapter adapter;
    ArrayList<Customer_Module> arrayList = new ArrayList<Customer_Module>();


    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
          Inilization();
    }

    private void Inilization() {


        db = FirebaseDatabase.getInstance().getReference().child("User_Customer_Created");


        recyclerView = findViewById(R.id.showuserdetailrecyler);
        recyclerView.setHasFixedSize(true);


        db.addValueEventListener(new ValueEventListener() {

            SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
            String s1 = sh.getString("UserID", "");

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Customer_Module customer_module = dataSnapshot.getValue(Customer_Module.class);


                    if(s1.equals(customer_module.getUserID())){
                        System.out.println(customer_module.getFullname());
                        arrayList.add(customer_module);
                    }


                }

                adapter = new Customer_details_Adapter(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}


