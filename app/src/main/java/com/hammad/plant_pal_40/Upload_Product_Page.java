package com.hammad.plant_pal_40;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class Upload_Product_Page extends AppCompatActivity {

    TextInputEditText plant_name, plant_characteristics, plant_care_instruction, plant_growth_habits,plant_stock,plant_price;
    ImageView imageView;
    MaterialButton button;


    DatabaseReference db;
    FirebaseFirestore firebaseFirestore;

    Uri imageUri;

    StorageReference storageReference;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product_page);
        Initilization();
    }

    private void Initilization(){
        plant_name = findViewById(R.id.uploadplantname);
        plant_characteristics = findViewById(R.id.uploadplantcherter);
        plant_care_instruction = findViewById(R.id.uploadPCI);
        plant_growth_habits = findViewById(R.id.uploadplantgrowthhabits);
        plant_stock = findViewById(R.id.uploadplantstock);
        plant_price = findViewById(R.id.uploadplantprice);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Upload Product");
        dialog.setMessage("Please wait Your Product is Uploading...");

        imageView = findViewById(R.id.admincustomerproductimageUplaod);
        button = findViewById(R.id.btnadminuploadadd);

        db= FirebaseDatabase.getInstance().getReference().child("Add_upload_Product");
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference().child("Images_upload_product");


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (imageUri !=null){
                    uploadToFirebase(imageUri,view);
                }else {

                    Toast.makeText(Upload_Product_Page.this, "Please Select Images", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }

    private void GetValidatonData(View view,Uri imageUri){
        DocumentReference documentReference =firebaseFirestore.collection("Add_upload_Product").document();

        String Splant_name=plant_name.getText().toString();
        String Slant_characteristics=plant_characteristics.getText().toString();
        String Splant_care_instruction=plant_care_instruction.getText().toString();
        String Splant_growth_habits=plant_growth_habits.getText().toString();
        String Splant_stock=plant_stock.getText().toString();
        String Splant_price=plant_price.getText().toString();
        dialog.show();



        if (Splant_name.equals("")){
            Snackbar.make(view,"Please Enter Plant Name",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else if (Slant_characteristics.equals("")){
            Snackbar.make(view,"Please Enter Plant Characteristics",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (Splant_care_instruction.equals("")){
            Snackbar.make(view, "Please Enter Care Instruction", Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (Splant_growth_habits.equals("")){
            Snackbar.make(view,"Please Enter Plant Growth Habits",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (Splant_stock.equals("")){
            Snackbar.make(view,"Please Enter Plant Stock",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (Splant_price.equals("")){
            Snackbar.make(view,"Please Enter Plant Price",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else {

            String id = "Product" + new Date().getTime();

            Upload_Products_Module obj =  new Upload_Products_Module(id,Splant_name,Slant_characteristics,Splant_care_instruction,Splant_growth_habits,Splant_stock,Splant_price,imageUri.toString());
            db.child(id).setValue(obj);
            documentReference.set(obj);
            startActivity(new Intent(getApplicationContext(), Admin_Home_Page.class));
            Snackbar.make(view,"Product Uploaded",Snackbar.LENGTH_SHORT).show();
            ClearData();
            dialog.dismiss();
        }

    }

    private void ClearData(){

        plant_name.setText("");
        plant_characteristics.setText("");
        plant_care_instruction.setText("");
        plant_growth_habits.setText("");
        plant_stock.setText("");
        plant_price.setText("");
        imageView.setImageResource(R.drawable.baseline_add_photo_alternate_24);
        dialog.dismiss();
    }


    private  void  OpenGallery(){


        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent , 2);

    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    private void uploadToFirebase(Uri uri, View view){

        final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        GetValidatonData(view,uri);
                        ClearData();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }


    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }


}