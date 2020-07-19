package com.example.uasproject.Administrasi;

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

public class DetailAministrasi extends AppCompatActivity {

    private DatabaseReference database, databaseRef;
    private StorageReference storage;

    private ImageView detail_fotoAdministrasi;
    private TextView detail_namaAdministrasi, detail_nimAdministrasi, detail_prodiAdministrasi, detail_telpAdministrasi;
    private Button btn_hapus;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_aministrasi);

        database = FirebaseDatabase.getInstance().getReference("Administrasi");

        detail_fotoAdministrasi = findViewById(R.id.detail_fotoAdministrasi);
        detail_namaAdministrasi = findViewById(R.id.detail_nama_administrasi);
        detail_nimAdministrasi = findViewById(R.id.detail_nim_administrasi);
        detail_prodiAdministrasi = findViewById(R.id.detail_prodi_administrasi);
        detail_telpAdministrasi = findViewById(R.id.detail_telp_administrasi);

        btn_hapus = findViewById(R.id.btn_hapus_administrasi);

        String AdministrasiKey = getIntent().getStringExtra("AdministrasiKey");

        databaseRef = FirebaseDatabase.getInstance().getReference("Administrasi").child(AdministrasiKey);
        storage = FirebaseStorage.getInstance().getReference().child("imagesAdministrasi").child(AdministrasiKey+".jpg");

        database.child(AdministrasiKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot ) {

                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nim = dataSnapshot.child("nim").getValue().toString();
                    String prodi = dataSnapshot.child("prodi").getValue().toString();
                    String telp = dataSnapshot.child("telp").getValue().toString();

                    String foto = dataSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(foto).into(detail_fotoAdministrasi);
                    detail_namaAdministrasi.setText(nama);
                    detail_nimAdministrasi.setText(nim);
                    detail_prodiAdministrasi.setText(prodi);
                    detail_telpAdministrasi.setText(telp);
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
                                startActivity(new Intent(getApplicationContext(), ListAdministrasi.class));
                            }
                        });

                    }
                });

            }
        });

    }
}