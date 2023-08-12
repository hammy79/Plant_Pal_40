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

public class Upload_Product_Accessiores_Adapter extends RecyclerView.Adapter<Upload_Product_Accessiores_Adapter.MyViewHolder> {



    Context context;
    ArrayList<Upload_Accessories_Module> arrayList = null;

    public Upload_Product_Accessiores_Adapter(Context context, ArrayList<Upload_Accessories_Module> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Upload_Product_Accessiores_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.upload_product_accessories_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Upload_Product_Accessiores_Adapter.MyViewHolder holder, int position) {
        Upload_Accessories_Module upload_accessories_module = arrayList.get(position);
        holder.Aaname.setText(upload_accessories_module.getA_name());
        holder.Aausageinstra.setText(upload_accessories_module.getA_usageinstru());
        holder.Aadesc.setText(upload_accessories_module.getA_desc());
        holder.Aastock.setText(upload_accessories_module.getA_stock());
        holder.Aaprice.setText(upload_accessories_module.getA_price());

        Picasso.get().load(upload_accessories_module.getImageAid()).into(holder.imageView);


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, upload_accessories_module.getA_name(), Snackbar.LENGTH_LONG).show();

                Intent intent = new Intent(context, Upload_Product_Accessories_Show_Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("imageAid", upload_accessories_module.getImageAid());
                intent.putExtra("a_name", upload_accessories_module.getA_name());
                intent.putExtra("a_usageinstru", upload_accessories_module.getA_usageinstru());
                intent.putExtra("a_desc", upload_accessories_module.getA_desc());
                intent.putExtra("a_stock", upload_accessories_module.getA_stock());
                intent.putExtra("a_price", upload_accessories_module.getA_price());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setfilterlist(ArrayList<Upload_Accessories_Module> newlist){
        this.arrayList=newlist;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Aaname,Aausageinstra ,Aadesc,Aastock,Aaprice;
        ImageView imageView;
        CardView cardView;
        Button btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Aaname=itemView.findViewById(R.id.accessoriesshowprdtname);
            Aausageinstra=itemView.findViewById(R.id.accessoriesshowUI);
            Aadesc=itemView.findViewById(R.id.accessoriesshowdesc);
            Aastock=itemView.findViewById(R.id.accessoriesshowstock);
            Aaprice=itemView.findViewById(R.id.accessoriesshowpeice);
            imageView=itemView.findViewById(R.id.accessoriesimgupldprdtshow);
//            cardView=itemView.findViewById(R.id.cardviewaccessoriesuploadprdt);
            btn=itemView.findViewById(R.id.seedetailsaccessoriesprdt);

        }
    }
}
