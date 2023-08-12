package com.hammad.plant_pal_40;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Upload_Poduct_Adapter extends RecyclerView.Adapter<Upload_Poduct_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Upload_Products_Module> arrayList = null;

    public Upload_Poduct_Adapter(Context context, ArrayList<Upload_Products_Module> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Upload_Poduct_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.upload_product_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Upload_Poduct_Adapter.MyViewHolder holder, int position) {
        Upload_Products_Module upload_products_module = arrayList.get(position);
        holder.plant_name.setText(upload_products_module.getPlant_name());
        holder.plant_characteristics.setText(upload_products_module.getPlant_characteristics());
        holder.plant_care_instruction.setText(upload_products_module.getPlant_care_instruction());
        holder.plant_growth_habits.setText(upload_products_module.getPlant_growth_habits());
        holder.plant_stock.setText(upload_products_module.getPlant_growth_habits());
        holder.plant_price.setText(upload_products_module.getPlant_price());
        Picasso.get().load(upload_products_module.getImageAid()).into(holder.imageView);

       holder.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Snackbar.make(v, upload_products_module.getPlant_stock(), Snackbar.LENGTH_LONG).show();

               Intent intent = new Intent(context, Upload_Product_Show_Details.class);
//               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("imageAid", upload_products_module.getImageAid());
               intent.putExtra("plant_name", upload_products_module.getPlant_name());
               intent.putExtra("plant_characteristics", upload_products_module.getPlant_characteristics());
               intent.putExtra("plant_care_instruction", upload_products_module.getPlant_care_instruction());
               intent.putExtra("plant_growth_habits", upload_products_module.getPlant_growth_habits());
               intent.putExtra("plant_stock", upload_products_module.getPlant_stock());
               intent.putExtra("plant_price", upload_products_module.getPlant_price());
               context.startActivity(intent);
           }
       });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setfilterlist(ArrayList<Upload_Products_Module> newlist){
        this.arrayList=newlist;
        notifyDataSetChanged();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView plant_name,plant_characteristics,plant_care_instruction,plant_growth_habits,plant_stock,plant_price;
        ImageView imageView;
        CardView cardView;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            plant_name=itemView.findViewById(R.id.adminshowplantname);
            plant_characteristics=itemView.findViewById(R.id.adminshowplc);
            plant_care_instruction=itemView.findViewById(R.id.adminshowcareinst);
            plant_growth_habits=itemView.findViewById(R.id.adminshowpgh);
            plant_stock=itemView.findViewById(R.id.adminshowplantstock);
            plant_price=itemView.findViewById(R.id.adminshowplantprices);
            imageView=itemView.findViewById(R.id.adminimgupldprdtshow);
//            cardView=itemView.findViewById(R.id.cardviewadminuploadprdt);
            button=itemView.findViewById(R.id.seedetailsadminprdt);
        }
    }
}
