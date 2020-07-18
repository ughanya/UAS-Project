package com.example.uasproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetailElektro extends AppCompatActivity {

    private DatabaseReference database, databaseRef;
    private StorageReference storage;

    private ImageView detail_fotoElektro;
    private TextView detail_namaElektro, detail_nimElektro, detail_prodiElektro, detail_telpElektro;
    private Button btn_hapus;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_elektro);

        database = FirebaseDatabase.getInstance().getReference("Elektro");

        detail_fotoElektro = findViewById(R.id.detail_fotoElektro);
        detail_namaElektro = findViewById(R.id.detail_nama_elektro);
        detail_nimElektro = findViewById(R.id.detail_nim_elektro);
        detail_prodiElektro = findViewById(R.id.detail_prodi_elektro);
        detail_telpElektro = findViewById(R.id.detail_telp_elektro);

        btn_hapus = findViewById(R.id.btn_hapus_elektro);

        String ElektroKey = getIntent().getStringExtra("ElektroKey");

        databaseRef = FirebaseDatabase.getInstance().getReference("Elektro").child(ElektroKey);
        storage = FirebaseStorage.getInstance().getReference().child("images").child(ElektroKey+"nama");


        database.child(ElektroKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nim = dataSnapshot.child("nim").getValue().toString();
                    String prodi = dataSnapshot.child("prodi").getValue().toString();
                    String telp = dataSnapshot.child("telp").getValue().toString();

                    String foto = dataSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(foto).into(detail_fotoElektro);
                    detail_namaElektro.setText(nama);
                    detail_nimElektro.setText(nim);
                    detail_prodiElektro.setText(prodi);
                    detail_telpElektro.setText(telp);
                }

            }

            @Override
            public void onCancelled( @NonNull DatabaseError error ) {

            }
        });

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                databaseRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess( Void aVoid ) {

                        storage.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess( Void aVoid ) {
                                startActivity(new Intent(getApplicationContext(), ListElektro.class));
                            }
                        });

                    }
                });

            }
        });

    }
}