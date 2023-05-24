package com.example.dca;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class adminAdd extends AppCompatActivity {
    ImageView uploadImage;
    Button saveButton;
    EditText uploadName, uploadDesc, uploadPrice;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        uploadImage = findViewById(R.id.uploadImage);
        uploadName = findViewById(R.id.uploadName);
        uploadDesc = findViewById(R.id.uploadDesc);
        uploadPrice = findViewById(R.id.uploadPrice);
        saveButton = findViewById(R.id.btnadd);

        uploadImage.setOnClickListener(view -> {
            Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            startActivityForResult(photoPicker, 1);
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data != null) {
                uri = data.getData();
                uploadImage.setImageURI(uri);
            } else {
                Toast.makeText(adminAdd.this, "No Image Selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveData() {
        if (uri == null) {
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Images")
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(adminAdd.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                uriTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri urlImage = task.getResult();
                            imageURL = urlImage.toString();
                            uploadData();
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(adminAdd.this, "Failed to retrieve image URL", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                dialog.dismiss();
                Toast.makeText(adminAdd.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadData() {
        String name = uploadName.getText().toString();
        String desc = uploadDesc.getText().toString();
        String price = uploadPrice.getText().toString();
        DataClass dataClass = new DataClass(name, desc, price, imageURL);

        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference("products");

        productsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long childCount = dataSnapshot.getChildrenCount(); // Get the current number of children

                DatabaseReference childRef = productsRef.child(String.valueOf(childCount + 1)); // Increment the child key by 1

                childRef.setValue(dataClass)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(adminAdd.this, "Product Information Saved", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(adminAdd.this, "Failed to save product information", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(Exception e) {
                                Toast.makeText(adminAdd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle potential error
            }
        });
    }


}
