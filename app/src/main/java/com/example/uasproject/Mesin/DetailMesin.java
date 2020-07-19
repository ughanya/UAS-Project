package com.example.uasproject.Mesin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.R;
import com.example.uasproject.Sipil.ListSipil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetailMesin extends AppCompatActivity {

    private DatabaseReference database, databaseRef;
    private StorageReference storage;

    private ImageView detail_fotoMesin;
    private TextView detail_namaMesin, detail_nimMesin, detail_prodiMesin, detail_telpMesin;
    private Button btn_hapus;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mesin);

        database = FirebaseDatabase.getInstance().getReference("Mesin");

        detail_fotoMesin = findViewById(R.id.detail_fotoMesin);
        detail_namaMesin = findViewById(R.id.detail_nama_mesin);
        detail_nimMesin = findViewById(R.id.detail_nim_mesin);
        detail_prodiMesin = findViewById(R.id.detail_prodi_mesin);
        detail_telpMesin = findViewById(R.id.detail_telp_mesin);

        btn_hapus = findViewById(R.id.btn_hapus_mesin);

        String MesinKey = getIntent().getStringExtra("MesinKey");

        databaseRef = FirebaseDatabase.getInstance().getReference("Mesin").child(MesinKey);
        storage = FirebaseStorage.getInstance().getReference().child("imagesMesin").child(MesinKey+" .jpg");

        database.child(MesinKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nim = dataSnapshot.child("nim").getValue().toString();
                    String prodi = dataSnapshot.child("prodi").getValue().toString();
                    String telp = dataSnapshot.child("telp").getValue().toString();

                    String foto = dataSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(foto).into(detail_fotoMesin);
                    detail_namaMesin.setText(nama);
                    detail_nimMesin.setText(nim);
                    detail_prodiMesin.setText(prodi);
                    detail_telpMesin.setText(telp);
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
                                startActivity(new Intent(getApplicationContext(), ListMesin.class));
                            }
                        });

                    }
                });

            }
        });


    }
}