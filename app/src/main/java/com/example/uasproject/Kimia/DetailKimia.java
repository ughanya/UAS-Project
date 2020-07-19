package com.example.uasproject.Kimia;

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

public class DetailKimia extends AppCompatActivity {

    private DatabaseReference database, databaseRef;
    private StorageReference storage;

    private ImageView detail_fotoKimia;
    private TextView detail_namaKimia, detail_nimKimia, detail_prodiKimia, detail_telpKimia;
    private Button btn_hapus;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kimia);

        database = FirebaseDatabase.getInstance().getReference("Kimia");

        detail_fotoKimia = findViewById(R.id.detail_fotoKimia);
        detail_namaKimia = findViewById(R.id.detail_nama_kimia);
        detail_nimKimia = findViewById(R.id.detail_nim_kimia);
        detail_prodiKimia = findViewById(R.id.detail_prodi_kimia);
        detail_telpKimia = findViewById(R.id.detail_telp_kimia);

        btn_hapus = findViewById(R.id.btn_hapus_kimia);

        String KimiaKey = getIntent().getStringExtra("KimiaKey");

        databaseRef = FirebaseDatabase.getInstance().getReference("Kimia").child(KimiaKey);
        storage = FirebaseStorage.getInstance().getReference().child("imagesKimia").child(KimiaKey+".jpg");

        database.child(KimiaKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nim = dataSnapshot.child("nim").getValue().toString();
                    String prodi = dataSnapshot.child("prodi").getValue().toString();
                    String telp = dataSnapshot.child("telp").getValue().toString();

                    String foto = dataSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(foto).into(detail_fotoKimia);
                    detail_namaKimia.setText(nama);
                    detail_nimKimia.setText(nim);
                    detail_prodiKimia.setText(prodi);
                    detail_telpKimia.setText(telp);
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
                                startActivity(new Intent(getApplicationContext(), ListKimia.class));
                            }
                        });

                    }
                });

            }
        });

    }
}