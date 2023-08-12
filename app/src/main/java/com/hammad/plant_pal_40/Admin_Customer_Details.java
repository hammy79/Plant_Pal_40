//package com.hammad.plant_pal_40;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class Admin_Customer_Details extends AppCompatActivity {
//    RecyclerView recyclerView;
//    Admin_Customer_details_Adapter adapter;
//    ArrayList<Admin_Customer_Detail_Module> arrayList = new ArrayList<Admin_Customer_Detail_Module>();
//
//    DatabaseReference db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_customer_details);
//        Inilization();
//
//    }
//    private void Inilization() {
//
//
//        db = FirebaseDatabase.getInstance().getReference().child("Admin_Check_Customer_Details");
//
//
//        recyclerView = findViewById(R.id.showuserdetailrecyler);
//        recyclerView.setHasFixedSize(true);
//
//
//
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Admin_Customer_Detail_Module module = dataSnapshot.getValue(Admin_Customer_Detail_Module.class);
//                    arrayList.add(module);
//
//                    System.out.println("hammad" + snapshot.toString());
//                    adapter = new Admin_Customer_details_Adapter(getApplicationContext(),arrayList);
//                    recyclerView.setAdapter(adapter);
//
//                }
//
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//    }
//
//}