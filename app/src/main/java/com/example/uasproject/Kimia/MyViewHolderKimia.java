package com.example.uasproject.Kimia;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderKimia extends RecyclerView.ViewHolder {

    ImageView fotoKimia;
    TextView namaKimia, prodiKimia;
    View v;

    public MyViewHolderKimia( @NonNull View itemView ) {
        super(itemView);

        fotoKimia = itemView.findViewById(R.id.kimia_foto);
        namaKimia= itemView.findViewById(R.id.kimia_nama);
        prodiKimia = itemView.findViewById(R.id.kimia_prodi);

        v = itemView;
    }

}
