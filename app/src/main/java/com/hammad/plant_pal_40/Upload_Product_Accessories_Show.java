package com.hammad.plant_pal_40;

import android.os.Bundle;
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

public class Upload_Product_Accessories_Show extends AppCompatActivity {

    RecyclerView recyclerView;

    Upload_Product_Accessiores_Adapter accessiores_adapter;
    ArrayList<Upload_Accessories_Module> arrayList = new ArrayList<>();

    DatabaseReference db;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product_accessories_show);
        Initiliztion();

    }

    private void Initiliztion(){

        recyclerView=findViewById(R.id.showaccessoriesuploadprdtrecyler);
        arrayList =new ArrayList<>();

        searchView=findViewById(R.id.seachviewaccess);

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




        db= FirebaseDatabase.getInstance().getReference().child("Add_accessories_upload_Product");


        db.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Upload_Accessories_Module upload_accessories_module= dataSnapshot.getValue(Upload_Accessories_Module.class);
                    arrayList.add(upload_accessories_module);


                }
                accessiores_adapter = new Upload_Product_Accessiores_Adapter(getApplicationContext(),arrayList);
                recyclerView.setAdapter(accessiores_adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void fliterlist(String newText) {

        ArrayList<Upload_Accessories_Module> fliterlist = new ArrayList<>();
        for (Upload_Accessories_Module module : arrayList){

//            module.getCar_name().toLowerCase(Locale.ROOT);

            if(module.getA_name().contains(newText))
            {
                fliterlist.add(module);
            }
        }

        if(fliterlist.isEmpty())
        {
            accessiores_adapter.setfilterlist(fliterlist);
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
        else {
            accessiores_adapter.setfilterlist(fliterlist);

        }

    }



}