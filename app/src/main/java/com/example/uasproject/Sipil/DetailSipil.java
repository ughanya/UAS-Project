package com.example.uasproject.Sipil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.ListElektro;
import com.example.uasproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetailSipil extends AppCompatActivity {

    private DatabaseReference database, databaseRef;
    private StorageReference storage;

    private ImageView detail_fotoSipil;
    private TextView detail_namaSipil, detail_nimSipil, detail_prodiSipil, detail_telpSipil;
    private Button btn_hapus;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sipil);

        database = FirebaseDatabase.getInstance().getReference("Sipil");

        detail_fotoSipil = findViewById(R.id.detail_fotoSipil);
        detail_namaSipil = findViewById(R.id.detail_nama_sipil);
        detail_nimSipil = findViewById(R.id.detail_nim_sipil);
        detail_prodiSipil = findViewById(R.id.detail_prodi_sipil);
        detail_telpSipil = findViewById(R.id.detail_telp_sipil);

        btn_hapus = findViewById(R.id.btn_hapus_sipil);

        String SipilKey = getIntent().getStringExtra("SipilKey");

        databaseRef = FirebaseDatabase.getInstance().getReference("Sipil").child(SipilKey);
        storage = FirebaseStorage.getInstance().getReference().child("imagesSipil").child(SipilKey+".jpg");

        database.child(SipilKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nim = dataSnapshot.child("nim").getValue().toString();
                    String prodi = dataSnapshot.child("prodi").getValue().toString();
                    String telp = dataSnapshot.child("telp").getValue().toString();

                    String foto = dataSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(foto).into(detail_fotoSipil);
                    detail_namaSipil.setText(nama);
                    detail_nimSipil.setText(nim);
                    detail_prodiSipil.setText(prodi);
                    detail_telpSipil.setText(telp);
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
                                startActivity(new Intent(getApplicationContext(), ListSipil.class));
                            }
                        });

                    }
                });

            }
        });

    }
}