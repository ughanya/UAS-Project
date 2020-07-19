package com.example.uasproject.Administrasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uasproject.AdapterElektro;
import com.example.uasproject.MenuJurusan;
import com.example.uasproject.R;
import com.example.uasproject.Sipil.Add_Sipil;
import com.example.uasproject.Sipil.DetailSipil;
import com.example.uasproject.Sipil.ListSipil;
import com.example.uasproject.Sipil.MyViewHolderSipil;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ListAdministrasi extends AppCompatActivity {


    private DatabaseReference database;
    private StorageReference penyimpanan;

    private RecyclerView list_administrasi;
    private Button tmbh_dataAdministrasi, btn_homeAdministrasi;

    private FirebaseRecyclerOptions<AdapterElektro> options;
    private FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderAdministrasi> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_administrasi);

        database = FirebaseDatabase.getInstance().getReference("Administrasi");
        //penyimpanan = FirebaseStorage.getInstance().getReference();

        list_administrasi = findViewById(R.id.data_Administrasi);
        tmbh_dataAdministrasi = findViewById(R.id.btn_tambahAdministrasi);
        list_administrasi.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_administrasi.setHasFixedSize(true);

        btn_homeAdministrasi = findViewById(R.id.btn_homeAdministrasi);

        btn_homeAdministrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), MenuJurusan.class));
            }
        });

        tmbh_dataAdministrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), Add_Administrasi.class));
            }
        });

        LoadData();

    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<AdapterElektro>().setQuery(database, AdapterElektro.class).build();
        adapter = new FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderAdministrasi>(options) {
            @Override
            protected void onBindViewHolder( @NonNull MyViewHolderAdministrasi holder, final int position, @NonNull AdapterElektro model ) {

                holder.namaAdministrasi.setText(model.getNama());
                holder.prodiAdministrasi.setText(model.getProdi());

                Picasso.get().load(model.getFoto()).into(holder.fotoAdministrasi);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {

                        Intent intent = new Intent(ListAdministrasi.this, DetailAministrasi.class);
                        intent.putExtra("AdministrasiKey", getRef(position).getKey());
                        startActivity((intent));

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderAdministrasi onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

                View v = LayoutInflater.from(parent.getContext()). inflate(R.layout.row_administrasi, parent, false);


                return new MyViewHolderAdministrasi(v);
            }
        };

        adapter.startListening();
        list_administrasi.setAdapter(adapter);

    }
}