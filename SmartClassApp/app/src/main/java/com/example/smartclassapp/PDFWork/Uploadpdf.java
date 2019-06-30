package com.example.smartclassapp.PDFWork;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartclassapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Uploadpdf extends AppCompatActivity {


    FirebaseDatabase FR;


    Button upload, selectFile;
    TextView notification;
    private EditText txtImageName;

    //fetch file
    Button fetchFiles;


    private Uri pdfUri; //path or url in local storage

    StorageReference storage;//used for uploading files
    DatabaseReference database;// store URls of uploaded file
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf);

        storage = FirebaseStorage.getInstance().getReference();//return an object of firebase storage
        database = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);//return an object of firebase database

        selectFile = findViewById(R.id.selectFile);
        upload = findViewById(R.id.upload);
        notification = findViewById(R.id.notification);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        txtImageName = (EditText) findViewById(R.id.txtImageName);


        //fetch file
        fetchFiles = findViewById(R.id.fetchFiles);

        fetchFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Uploadpdf.this, MyRecyclerViewActivity.class));

            }
        });


        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Uploadpdf.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    selectPdf();
                } else {
                    ActivityCompat.requestPermissions(Uploadpdf.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pdfUri != null) {//user selected the file
                    uploadFile(pdfUri);
                } else {
                    Toast.makeText(Uploadpdf.this, "Select a file", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void uploadFile(final Uri pdfUri) {

        final String fileName = System.currentTimeMillis() + ".pdf";
        final String fileName1 = System.currentTimeMillis() + "";


        final StorageReference filePath = storage.child(Constants.DATABASE_PATH_UPLOADS).child(fileName);


        filePath.putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        notification.setText("File Uploaded Successfully");
                        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String url = uri.toString();
                                item obj = new item(txtImageName.getText().toString(),url);
                                final DatabaseReference reference = database;//return path to root
                                reference.child(fileName1).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            //item obj = new item(txtImageName.getText().toString(),url);

                                            //String uploadId = reference.push().getKey();
                                            //reference.child(uploadId).setValue(obj);
                                            Toast.makeText(Uploadpdf.this, "File is successfully uploaded", Toast.LENGTH_SHORT).show();
                                        } else
                                            Toast.makeText(Uploadpdf.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Uploadpdf.this, "File not successfully uploaded", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                //track the progress of our upload
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                notification.setText((int) progress + "% Uploading...");
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectPdf();
        } else {
            Toast.makeText(Uploadpdf.this, "Please provide permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf() {

        //to offer user select file using select manager

        // we will use intent

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);//to fetch files
        startActivityForResult(intent, 86);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // check whether user file selected or not
        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {

            pdfUri = data.getData();//return the uri of selected file
            notification.setText("Selected: " + data.getData());

        } else {
            Toast.makeText(Uploadpdf.this, "Please select a file", Toast.LENGTH_SHORT).show();
        }

    }
}
