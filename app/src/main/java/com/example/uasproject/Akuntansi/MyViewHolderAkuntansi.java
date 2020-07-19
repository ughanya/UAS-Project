package com.example.uasproject.Akuntansi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderAkuntansi extends RecyclerView.ViewHolder {

    ImageView fotoAkuntansi;
    TextView namaAkuntansi, prodiAkuntansi;
    View v;

    public MyViewHolderAkuntansi( @NonNull View itemView ) {
        super(itemView);

        fotoAkuntansi = itemView.findViewById(R.id.akuntansi_foto);
        namaAkuntansi= itemView.findViewById(R.id.akuntansi_nama);
        prodiAkuntansi = itemView.findViewById(R.id.akuntansi_prodi);

        v = itemView;
    }

}
