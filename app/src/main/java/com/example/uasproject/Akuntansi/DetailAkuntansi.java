package com.example.uasproject.Akuntansi;

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

public class DetailAkuntansi extends AppCompatActivity {

    private DatabaseReference database, databaseRef;
    private StorageReference storage;

    private ImageView detail_fotoAkuntansi;
    private TextView detail_namaAkuntansi, detail_nimAkuntansi, detail_prodiAkuntansi, detail_telpAkuntansi;
    private Button btn_hapus;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akuntansi);

        database = FirebaseDatabase.getInstance().getReference("Akuntansi");

        detail_fotoAkuntansi = findViewById(R.id.detail_fotoAkuntansi);
        detail_namaAkuntansi = findViewById(R.id.detail_nama_akuntansi);
        detail_nimAkuntansi = findViewById(R.id.detail_nim_akuntansi);
        detail_prodiAkuntansi = findViewById(R.id.detail_prodi_akuntansi);
        detail_telpAkuntansi = findViewById(R.id.detail_telp_akuntansi);

        btn_hapus = findViewById(R.id.btn_hapus_akuntansi);

        String AkuntansiKey = getIntent().getStringExtra("AkuntansiKey");

        databaseRef = FirebaseDatabase.getInstance().getReference("Akuntansi").child(AkuntansiKey);
        storage = FirebaseStorage.getInstance().getReference().child("imagesAkuntansi").child(AkuntansiKey+".jpg");

        database.child(AkuntansiKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nim = dataSnapshot.child("nim").getValue().toString();
                    String prodi = dataSnapshot.child("prodi").getValue().toString();
                    String telp = dataSnapshot.child("telp").getValue().toString();

                    String foto = dataSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(foto).into(detail_fotoAkuntansi);
                    detail_namaAkuntansi.setText(nama);
                    detail_nimAkuntansi.setText(nim);
                    detail_prodiAkuntansi.setText(prodi);
                    detail_telpAkuntansi.setText(telp);
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
                                startActivity(new Intent(getApplicationContext(), ListAkuntansi.class));
                            }
                        });

                    }
                });

            }
        });

    }
}