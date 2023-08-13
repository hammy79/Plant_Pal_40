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

public class Upload_Accessories_Page extends AppCompatActivity {

    TextInputEditText A_name, A_usage_instr, A_desc, A_stock,A_price;
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
        setContentView(R.layout.activity_upload_accessories_page);
        Initilization();
    }

    private void Initilization(){
        A_name = findViewById(R.id.accessoriesuploadname);
        A_usage_instr = findViewById(R.id.accessoriesuploadusageinstr);
        A_desc = findViewById(R.id.accessoriesuploaddesc);
        A_stock = findViewById(R.id.accessoriesuploadstock);
        A_price = findViewById(R.id.accessoriesuploadprice);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Upload Accessories Product");
        dialog.setMessage("Please wait Your Product is Uploading...");

        imageView = findViewById(R.id.accessoriesproductimageUplaod);
        button = findViewById(R.id.btnadminuploadaddaccessories);

        db= FirebaseDatabase.getInstance().getReference().child("Add_accessories_upload_Product");
        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference().child("Images_accessories_upload_product");


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
                    dialog.show();
                    uploadToFirebase(imageUri,view);
                }else {

                    Toast.makeText(Upload_Accessories_Page.this, "Please Select Images", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }

    private void GetValidatonData(View view,Uri imageUri){
        DocumentReference documentReference =firebaseFirestore.collection("Add_accessories_upload_Product").document();

        String SA_name=A_name.getText().toString();
        String SA_usageinstr=A_usage_instr.getText().toString();
        String SA_desc_instruction=A_desc.getText().toString();
        String SA_stock=A_stock.getText().toString();
        String SA_price=A_price.getText().toString();



        if (SA_name.equals("")){
            Snackbar.make(view,"Please Enter Accessories Name",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else if (SA_usageinstr.equals("")){
            Snackbar.make(view,"Please Enter Usage Instruction",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (SA_desc_instruction.equals("")){
            Snackbar.make(view, "Please Enter Accessories Descripation", Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (SA_stock.equals("")){
            Snackbar.make(view,"Please Enter Accessories Price",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else if (SA_price.equals("")){
            Snackbar.make(view,"Please Enter Plant Stock",Snackbar.LENGTH_SHORT).show();
            dialog.dismiss();

        } else {
            Upload_Accessories_Module obj =  new Upload_Accessories_Module(SA_name,SA_usageinstr,SA_desc_instruction,SA_stock,SA_price,imageUri.toString());
            db.push().setValue(obj);
            documentReference.set(obj);
            startActivity(new Intent(getApplicationContext(), Admin_Home_Page.class));
            Snackbar.make(view,"Product Uploaded",Snackbar.LENGTH_SHORT).show();
            ClearData();

        }

    }

    private void ClearData(){

        A_name.setText("");
        A_usage_instr.setText("");
        A_desc.setText("");
        A_stock.setText("");
        A_price.setText("");
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


    public void accessoriesaddprdt(View view) {
        startActivity(new Intent(getApplicationContext(),Upload_Accessories_Page.class));
    }
}