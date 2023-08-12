package com.hammad.plant_pal_40;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class Customer_details_Adapter extends RecyclerView.Adapter<Customer_details_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Customer_Module> list;
    DatabaseReference db;

    public Customer_details_Adapter(Context context, ArrayList<Customer_Module> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Customer_details_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_details_item,parent,false);
        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Customer_details_Adapter.MyViewHolder holder, int position) {
      Customer_Module customer_module = list.get(position);
      holder.shname.setText(customer_module.getFullname());
      holder.susername.setText(customer_module.getUsername());
      holder.shemail.setText(customer_module.getEmail());
      holder.shaddrees.setText(customer_module.getAddress());
      holder.sage.setText(customer_module.getAge());
      holder.sgender.setText(customer_module.getGender());
      holder.shpassword.setText(customer_module.getPassword());
      holder.sconfirmpassword.setText(customer_module.getConfirmpassword());






        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = FirebaseDatabase.getInstance().getReference().child("User_Customer_Created").child(customer_module.getUserID());



                Intent intent = new Intent(context,Customer_Update_Page.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                intent.putExtra("id",customer_module.getUserID());
                intent.putExtra("fullname",customer_module.getFullname());
                intent.putExtra("username",customer_module.getUsername());
                intent.putExtra("email",customer_module.getEmail());
                intent.putExtra("address",customer_module.getAddress());
                intent.putExtra("age",customer_module.getAge());
                intent.putExtra("gender",customer_module.getGender());
                intent.putExtra("password",customer_module.getPassword());
                intent.putExtra("confirmpassword",customer_module.getConfirmpassword());
                context.startActivity(intent);
                Toast.makeText(context, customer_module.getUserID(), Toast.LENGTH_SHORT).show();

            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db = FirebaseDatabase.getInstance().getReference().child("User_Customer_Created").child(customer_module.getUserID());


                db.removeValue();
                Intent intent = new Intent(context,Customer_Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView shname,susername,shemail, shaddrees, sage, sgender, shpassword,sconfirmpassword;
        Button buttonDelete;
        Button buttonUpdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shname=itemView.findViewById(R.id.showname);
            susername=itemView.findViewById(R.id.showusername);
            shemail=itemView.findViewById(R.id.showemail);
            shaddrees=itemView.findViewById(R.id.showadress);
            sage=itemView.findViewById(R.id.showage);
            sgender=itemView.findViewById(R.id.showgender);
            shpassword=itemView.findViewById(R.id.showpassowrd);
            sconfirmpassword=itemView.findViewById(R.id.showconfirmpassword);
            buttonUpdate=itemView.findViewById(R.id.Updatecustomer);
            buttonDelete=itemView.findViewById(R.id.deletecustomer);



        }
    }
}
