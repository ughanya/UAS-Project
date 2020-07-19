package com.example.uasproject.Akuntansi;

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

public class ListAkuntansi extends AppCompatActivity {

    private DatabaseReference database;
    private StorageReference penyimpanan;

    private RecyclerView list_akuntansi;
    private Button tmbh_dataAkuntansi, btn_homeAkuntansi;

    private FirebaseRecyclerOptions<AdapterElektro> options;
    private FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderAkuntansi> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_akuntansi);

        database = FirebaseDatabase.getInstance().getReference("Akuntansi");
        //penyimpanan = FirebaseStorage.getInstance().getReference();

        list_akuntansi = findViewById(R.id.data_Akuntansi);
        tmbh_dataAkuntansi = findViewById(R.id.btn_tambahAkuntansi);
        list_akuntansi.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_akuntansi.setHasFixedSize(true);

        btn_homeAkuntansi = findViewById(R.id.btn_homeAkuntansi);

        btn_homeAkuntansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), MenuJurusan.class));
            }
        });

        tmbh_dataAkuntansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), Add_Akuntansi.class));
            }
        });

        LoadData();

    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<AdapterElektro>().setQuery(database, AdapterElektro.class).build();
        adapter = new FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderAkuntansi>(options) {
            @Override
            protected void onBindViewHolder( @NonNull MyViewHolderAkuntansi holder, final int position, @NonNull AdapterElektro model ) {

                holder.namaAkuntansi.setText(model.getNama());
                holder.prodiAkuntansi.setText(model.getProdi());

                Picasso.get().load(model.getFoto()).into(holder.fotoAkuntansi);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {

                        Intent intent = new Intent(ListAkuntansi.this, DetailAkuntansi.class);
                        intent.putExtra("AkuntansiKey", getRef(position).getKey());
                        startActivity((intent));

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderAkuntansi onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

                View v = LayoutInflater.from(parent.getContext()). inflate(R.layout.row_akuntansi, parent, false);


                return new MyViewHolderAkuntansi(v);
            }
        };

        adapter.startListening();
        list_akuntansi.setAdapter(adapter);

    }
}