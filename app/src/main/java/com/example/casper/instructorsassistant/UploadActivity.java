package com.example.casper.instructorsassistant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {
    private static final int PICK_PDF_CODE = 2342;
    RecyclerView recyclerView;
    UploadListAdapter adapter;
    ProgressBar progressBar;
    EditText filenameEditText;
    String fileName;
    DatabaseReference mRef;
    FirebaseDatabase database;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        filenameEditText = (EditText) findViewById(R.id.filename);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_allUploadsPage);

        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();

        if(adapter == null) {
            adapter = new UploadListAdapter(new ArrayList<UploadInfo>(), new onMyDownloadAndDeleteUploadListener() {
                @Override
                public void onUploadDeleteClicked(UploadInfo uploadInfo) {
                   mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("uploads").child(uploadInfo.getDate()).removeValue();
                    Toast.makeText(UploadActivity.this, uploadInfo.getName()+ " is deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
        recyclerView.setAdapter(adapter);
        mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("uploads").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UploadInfo uploadInfo = dataSnapshot.getValue(UploadInfo.class);
                Toast.makeText(UploadActivity.this, uploadInfo.getName(), Toast.LENGTH_SHORT).show();
                 adapter.add(uploadInfo);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if(TextUtils.isEmpty(filenameEditText.getText().toString().trim())|| TextUtils.equals(filenameEditText.getText().toString().trim(),"100% Uploading...")){
                    Toast.makeText(UploadActivity.this, "Please enter the file name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // file name
                fileName = filenameEditText.getText().toString().trim();

                //for greater than lolipop versions we need the permissions asked on runtime
                //so if the permission is not available user will go to the screen to allow storage permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                    return;
                }

                //Start intent to choose file
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            if (data.getData() != null) {
                //uploading the file
                uploadFile(data.getData());
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFile(Uri data){
        progressBar.setVisibility(View.VISIBLE);
        StorageReference sRef = mStorageRef.child("pdf/"+InstructorsMainPage.courseSelected+"/"+fileName+".pdf");
        sRef.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests") UploadInfo uploadInfo = new UploadInfo(fileName,taskSnapshot.getDownloadUrl().toString(), String.valueOf(System.currentTimeMillis()));
                mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("uploads").child(String.valueOf(System.currentTimeMillis())).setValue(uploadInfo);
                progressBar.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @SuppressWarnings("VisibleForTests")
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                filenameEditText.setText((int) progress + "% Uploading...");
            }

        });


    }
}
