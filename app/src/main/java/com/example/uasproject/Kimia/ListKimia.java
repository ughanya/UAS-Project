package com.example.uasproject.Kimia;

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

public class ListKimia extends AppCompatActivity {

    private DatabaseReference database;
    private StorageReference penyimpanan;

    private RecyclerView list_kimia;
    private Button tmbh_dataKimia, btn_homeKimia;

    private FirebaseRecyclerOptions<AdapterElektro> options;
    private FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderKimia> adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kimia);

        database = FirebaseDatabase.getInstance().getReference("Kimia");
        //penyimpanan = FirebaseStorage.getInstance().getReference();

        list_kimia = findViewById(R.id.data_Kimia);
        tmbh_dataKimia = findViewById(R.id.btn_tambahKimia);
        list_kimia.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_kimia.setHasFixedSize(true);

        btn_homeKimia = findViewById(R.id.btn_homeKimia);

        btn_homeKimia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), MenuJurusan.class));
            }
        });

        tmbh_dataKimia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), Add_Kimia.class));
            }
        });

        LoadData();
    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<AdapterElektro>().setQuery(database, AdapterElektro.class).build();
        adapter = new FirebaseRecyclerAdapter<AdapterElektro, MyViewHolderKimia>(options) {
            @Override
            protected void onBindViewHolder( @NonNull MyViewHolderKimia holder, final int position, @NonNull AdapterElektro model ) {

                holder.namaKimia.setText(model.getNama());
                holder.prodiKimia.setText(model.getProdi());

                Picasso.get().load(model.getFoto()).into(holder.fotoKimia);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {

                        Intent intent = new Intent(ListKimia.this, DetailKimia.class);
                        intent.putExtra("KimiaKey", getRef(position).getKey());
                        startActivity((intent));

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderKimia onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

                View v = LayoutInflater.from(parent.getContext()). inflate(R.layout.row_kimia, parent, false);


                return new MyViewHolderKimia(v);
            }
        };

        adapter.startListening();
        list_kimia.setAdapter(adapter);

    }
}