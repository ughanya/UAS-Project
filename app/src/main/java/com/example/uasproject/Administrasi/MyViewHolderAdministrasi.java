package com.example.uasproject.Administrasi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uasproject.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderAdministrasi extends RecyclerView.ViewHolder {

    ImageView fotoAdministrasi;
    TextView namaAdministrasi, prodiAdministrasi;
    View v;

    public MyViewHolderAdministrasi( @NonNull View itemView ) {
        super(itemView);

        fotoAdministrasi = itemView.findViewById(R.id.administrasi_foto);
        namaAdministrasi= itemView.findViewById(R.id.administrasi_nama);
        prodiAdministrasi = itemView.findViewById(R.id.administrasi_prodi);

        v = itemView;
    }

}
