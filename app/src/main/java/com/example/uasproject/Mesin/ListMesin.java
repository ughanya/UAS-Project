package com.example.uasproject.Mesin;

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

public class ListMesin extends AppCompatActivity {

    private DatabaseReference database;
    private StorageReference penyimpanan;

    private RecyclerView list_mesin;
    private Button tmbh_dataMesin, btn_homeMesin;

    private FirebaseRecyclerOptions<AdapterElektro> options;
    private FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderMesin> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mesin);

        database = FirebaseDatabase.getInstance().getReference("Mesin");
        //penyimpanan = FirebaseStorage.getInstance().getReference();

        list_mesin = findViewById(R.id.data_Mesin);
        tmbh_dataMesin = findViewById(R.id.btn_tambahMesin);
        list_mesin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_mesin.setHasFixedSize(true);

        btn_homeMesin = findViewById(R.id.btn_homeMesin);

        btn_homeMesin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), MenuJurusan.class));
            }
        });

        tmbh_dataMesin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), Add_Mesin.class));
            }
        });

        LoadData();

    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<AdapterElektro>().setQuery(database, AdapterElektro.class).build();
        adapter = new FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderMesin>(options) {
            @Override
            protected void onBindViewHolder( @NonNull MyViewHolderMesin holder, final int position, @NonNull AdapterElektro model ) {

                holder.namaMesin.setText(model.getNama());
                holder.prodiMesin.setText(model.getProdi());

                Picasso.get().load(model.getFoto()).into(holder.fotoMesin);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {

                        Intent intent = new Intent(ListMesin.this, DetailMesin.class);
                        intent.putExtra("MesinKey", getRef(position).getKey());
                        startActivity((intent));

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderMesin onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

                View v = LayoutInflater.from(parent.getContext()). inflate(R.layout.row_mesin, parent, false);


                return new MyViewHolderMesin(v);
            }
        };

        adapter.startListening();
        list_mesin.setAdapter(adapter);

    }
}