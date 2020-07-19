package com.example.uasproject.Sipil;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderSipil extends RecyclerView.ViewHolder {

    ImageView fotoSipil;
    TextView namaSipil, prodiSipil;
    View v;

    public MyViewHolderSipil( @NonNull View itemView ) {
        super(itemView);

        fotoSipil = itemView.findViewById(R.id.sipil_foto);
        namaSipil= itemView.findViewById(R.id.sipil_nama);
        prodiSipil = itemView.findViewById(R.id.sipil_prodi);

        v = itemView;
    }
}
