//package com.hammad.plant_pal_40;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.DatabaseReference;
//
//import java.util.ArrayList;
//
//public class Admin_Customer_details_Adapter extends RecyclerView.Adapter<Admin_Customer_details_Adapter.MyViewHolder> {
//
//    Context context;
//    ArrayList<Customer_Module> arrayList;
//
//    public Admin_Customer_details_Adapter(Context context, ArrayList<Customer_Module> arrayList) {
//        this.context = context;
//        this.arrayList = arrayList;
//    }
//
//    DatabaseReference db;
//
//
//
//    @NonNull
//    @Override
//    public Admin_Customer_details_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.admin_check_details_item,parent,false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Admin_Customer_details_Adapter.MyViewHolder holder, int position) {
//
//        Customer_Module customer_module = arrayList.get(position);
//        holder.shname.setText(customer_module.getFullname());
//        holder.susername.setText(customer_module.getUsername());
//        holder.shemail.setText(customer_module.getEmail());
//        holder.shaddrees.setText(customer_module.getAddress());
//        holder.sage.setText(customer_module.getAge());
//        holder.sgender.setText(customer_module.getGender());
//        holder.shpassword.setText(customer_module.getPassword());
//        holder.sconfirmpassword.setText(customer_module.getConfirmpassword());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        TextView shname,susername,shemail, shaddrees, sage, sgender, shpassword,sconfirmpassword;
//        Button buttonDelete;
//        Button buttonUpdate;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            shname=itemView.findViewById(R.id.showname);
//            susername=itemView.findViewById(R.id.showusername);
//            shemail=itemView.findViewById(R.id.showemail);
//            shaddrees=itemView.findViewById(R.id.showadress);
//            sage=itemView.findViewById(R.id.showage);
//            sgender=itemView.findViewById(R.id.showgender);
//            shpassword=itemView.findViewById(R.id.showpassowrd);
//            sconfirmpassword=itemView.findViewById(R.id.showconfirmpassword);
//            buttonUpdate=itemView.findViewById(R.id.Updatecustomer);
//            buttonDelete=itemView.findViewById(R.id.deletecustomer);
//        }
//    }
//}
