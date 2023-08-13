package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;

public class Customer_Details extends AppCompatActivity {

    RecyclerView recyclerView;
    Customer_details_Adapter adapter;
    ArrayList<Customer_Module> arrayList = new ArrayList<Customer_Module>();


    DatabaseReference db;
    FirebaseFirestore db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        //   Inilization();
        display_data();
    }

    private void Inilization() {


        db = FirebaseDatabase.getInstance().getReference().child("User_Customer_Created");


        recyclerView = findViewById(R.id.showuserdetailrecyler);
        recyclerView.setHasFixedSize(true);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Customer_Module customer_module = dataSnapshot.getValue(Customer_Module.class);
                    arrayList.add(customer_module);

                    System.out.println("hammad" + snapshot.toString());


                }

                adapter = new Customer_details_Adapter(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void display_data(){

        db2 = FirebaseFirestore.getInstance();

        db2.collection("User_Customer_Created").whereEqualTo("userId","User1691781013182").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                }
            }
        });


    }


}


