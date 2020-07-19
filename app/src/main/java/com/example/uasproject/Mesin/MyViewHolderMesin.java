package com.example.uasproject.Mesin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderMesin extends RecyclerView.ViewHolder {

    ImageView fotoMesin;
    TextView namaMesin, prodiMesin;
    View v;

    public MyViewHolderMesin( @NonNull View itemView ) {
        super(itemView);

        fotoMesin = itemView.findViewById(R.id.mesin_foto);
        namaMesin= itemView.findViewById(R.id.mesin_nama);
        prodiMesin = itemView.findViewById(R.id.mesin_prodi);

        v = itemView;
    }

}
