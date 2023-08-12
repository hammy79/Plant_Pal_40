package com.hammad.plant_pal_40;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Upload_Show_Product_Page extends AppCompatActivity {

    RecyclerView recyclerView;
    Upload_Poduct_Adapter adapter;
    ArrayList<Upload_Products_Module> arrayList = new ArrayList<>();
    DatabaseReference db;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_show_product_page);
        Inilization();
    }

    private void Inilization(){

        recyclerView=findViewById(R.id.showadminuploadprdtrecyler);
        arrayList =new ArrayList<>();

        searchView=findViewById(R.id.seachview);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                fliterlist(newText);
                return true;
            }
        });




        db= FirebaseDatabase.getInstance().getReference().child("Add_upload_Product");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Upload_Products_Module module= dataSnapshot.getValue(Upload_Products_Module.class);
                    arrayList.add(module);

                }
                adapter=new Upload_Poduct_Adapter(getApplicationContext(),arrayList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void customerhome(View view) {
        startActivity(new Intent(getApplicationContext(),Admin_Home_Page.class));
    }


    private void fliterlist(String newText) {

        ArrayList<Upload_Products_Module> fliterlist = new ArrayList<>();
        for (Upload_Products_Module module : arrayList){

//            module.getPlant_name().toLowerCase(Locale.ROOT);

            if(module.getPlant_name().contains(newText))
            {
                fliterlist.add(module);
            }
        }

        if(fliterlist.isEmpty())
        {
            adapter.setfilterlist(fliterlist);
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.setfilterlist(fliterlist);

        }

    }



}