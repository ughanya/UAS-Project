package com.example.uasproject;

import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListElektro extends AppCompatActivity {

    private DatabaseReference database;
    private StorageReference penyimpanan;

    private RecyclerView list_elektro;
    private Button tmbh_dataelektro, btn_homeElektro;
    private EditText elektroSearch;

    private FirebaseRecyclerOptions<AdapterElektro> options;
    private FirebaseRecyclerAdapter<AdapterElektro, MyViewHodler>adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_elektro);

        database = FirebaseDatabase.getInstance().getReference("Elektro");
        //penyimpanan = FirebaseStorage.getInstance().getReference();

        list_elektro = findViewById(R.id.data_elektro);
        tmbh_dataelektro = findViewById(R.id.btn_tambahElektro);
        list_elektro.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list_elektro.setHasFixedSize(true);

        tmbh_dataelektro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        LoadData();

    }

    private void LoadData() {


        options = new FirebaseRecyclerOptions.Builder<AdapterElektro>().setQuery(database, AdapterElektro.class).build();
        adapter = new FirebaseRecyclerAdapter<AdapterElektro, MyViewHodler>(options) {
            @Override
            protected void onBindViewHolder( @NonNull MyViewHodler holder, final int position, @NonNull AdapterElektro model ) {

                holder.namaElektro.setText(model.getNama());
                holder.prodiElektro.setText(model.getProdi());

                Picasso.get().load(model.getFoto()).into(holder.fotoElektro);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick( View view ) {

                        Intent intent = new Intent(ListElektro.this, DetailElektro.class);
                        intent.putExtra("ElektroKey", getRef(position).getKey());
                        startActivity((intent));

                    }
                });

            }

            @NonNull
            @Override
            public MyViewHodler onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {

                View v = LayoutInflater.from(parent.getContext()). inflate(R.layout.row_elektro, parent, false);


                return new MyViewHodler(v);
            }
        };

        adapter.startListening();
        list_elektro.setAdapter(adapter);

    }

}

