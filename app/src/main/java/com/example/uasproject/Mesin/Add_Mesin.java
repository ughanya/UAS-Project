package com.example.uasproject.Mesin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uasproject.R;
import com.example.uasproject.Request;
import com.example.uasproject.Sipil.Add_Sipil;
import com.google.android.gms.tasks.Continuation;
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
import com.squareup.picasso.Picasso;

public class Add_Mesin extends AppCompatActivity {

    private ImageView fotoMesin;
    private Uri filePath;

    ProgressBar progressBar;

    private DatabaseReference database;
    private StorageReference penyimpanan;
    private String storageUrl;

    private String mahasiswaId;

    private Button btn_simpanMesin;

    private EditText nimMesin, namaMesin, prodiMesin, telpMesin;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__mesin);

        database = FirebaseDatabase.getInstance().getReference();
        penyimpanan = FirebaseStorage.getInstance().getReference();

        mahasiswaId = database.push().getKey();

        nimMesin = findViewById(R.id.et_nim);
        namaMesin = findViewById(R.id.et_nama);
        prodiMesin = findViewById(R.id.et_prodi);
        telpMesin = findViewById(R.id.et_telp);
        fotoMesin = findViewById(R.id.btn_foto);

        btn_simpanMesin = findViewById(R.id.btn_simpanMesin);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        findViewById(R.id.btn_foto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Add_Mesin.this.tampilGalery();
            }
        });

        findViewById(R.id.btn_simpanMesin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                progressBar.setProgress(0);
                progressBar.setVisibility(View.INVISIBLE);
                Add_Mesin.this.simpanFoto();

            }
        });

    }

    private void simpanFoto() {

        if(filePath != null){

            String imageName = nimMesin.getText().toString();

            final StorageReference ref = penyimpanan.child("imagesMesin/"+imageName+".jpg");

            UploadTask uploadTask = ref.putFile(filePath);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }

                //mengambil url dari foto yang diupload
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){

                        Uri downloadUrl = task.getResult();

                        storageUrl = downloadUrl.toString();

                        simpanData();

                        finish();
                    }
                }
            });

            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                    progressBar.setProgress((int)progress);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(), "Gagal Menyimpan Data " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }

    }


    private void tampilGalery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();

            Picasso.get().load(filePath).fit().into(fotoMesin);

        } else{
            Toast.makeText(getApplicationContext(), "Silahkan Masukkan Foto", Toast.LENGTH_SHORT).show();
        }


    }

    private void simpanData() {

        Request requests = new Request(mahasiswaId,
                nimMesin.getText().toString(),
                namaMesin.getText().toString(),
                prodiMesin.getText().toString(),
                telpMesin.getText().toString(),
                storageUrl);

        database.child("Mesin")
                .push()
                .setValue(requests).addOnSuccessListener(this, new OnSuccessListener() {
            @Override
            public void onSuccess( Object aVoid ) {

                Toast.makeText(Add_Mesin.this, "Data Berhasil Ditambahkan",
                        Toast.LENGTH_SHORT).show();


            }
        });

    }
}