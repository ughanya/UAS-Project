package com.example.uasproject.Sipil;

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
import android.widget.EditText;

import com.example.uasproject.AdapterElektro;
import com.example.uasproject.DetailElektro;
import com.example.uasproject.ListElektro;
import com.example.uasproject.MainActivity;
import com.example.uasproject.MenuJurusan;
import com.example.uasproject.MyViewHodler;
import com.example.uasproject.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ListSipil extends AppCompatActivity {

    private DatabaseReference database;
    private StorageReference penyimpanan;

    private RecyclerView list_sipil;
    private Button tmbh_dataSipil, btn_homeSipil;

    private FirebaseRecyclerOptions<AdapterElektro> options;
    private FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderSipil> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sipil);

        database = FirebaseDatabase.getInstance().getReference("Sipil");
        //penyimpanan = FirebaseStorage.getInstance().getReference();

        list_sipil = findViewById(R.id.data_Sipil);
        tmbh_dataSipil = findViewById(R.id.btn_tambahSipil);
        list_sipil.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_sipil.setHasFixedSize(true);

        btn_homeSipil = findViewById(R.id.btn_homeSipil);

        btn_homeSipil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), MenuJurusan.class));
            }
        });

        tmbh_dataSipil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), Add_Sipil.class));
            }
        });

        LoadData();

    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<AdapterElektro>().setQuery(database, AdapterElektro.class).build();
        adapter = new FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderSipil>(options) {
            @Override
            protected void onBindViewHolder( @NonNull MyViewHolderSipil holder, final int position, @NonNull AdapterElektro model ) {

                holder.namaSipil.setText(model.getNama());
                holder.prodiSipil.setText(model.getProdi());

                Picasso.get().load(model.getFoto()).into(holder.fotoSipil);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {

                        Intent intent = new Intent(ListSipil.this, DetailSipil.class);
                        intent.putExtra("SipilKey", getRef(position).getKey());
                        startActivity((intent));

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderSipil onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

                View v = LayoutInflater.from(parent.getContext()). inflate(R.layout.row_sipil, parent, false);


                return new MyViewHolderSipil(v);
            }
        };

        adapter.startListening();
        list_sipil.setAdapter(adapter);
    }
}