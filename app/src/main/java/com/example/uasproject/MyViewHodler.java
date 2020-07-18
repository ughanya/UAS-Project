package com.example.uasproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHodler extends RecyclerView.ViewHolder {

    ImageView fotoElektro;
    TextView namaElektro, prodiElektro;
    View v;

    public MyViewHodler( @NonNull View itemView ) {
        super(itemView);

        fotoElektro = itemView.findViewById(R.id.elektro_foto1);
        namaElektro= itemView.findViewById(R.id.elektro_nama1);
        prodiElektro = itemView.findViewById(R.id.elektro_prodi1);

        v = itemView;
    }
}
