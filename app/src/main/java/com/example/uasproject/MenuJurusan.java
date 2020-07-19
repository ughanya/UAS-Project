package com.example.uasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uasproject.Administrasi.ListAdministrasi;
import com.example.uasproject.Akuntansi.Add_Akuntansi;
import com.example.uasproject.Akuntansi.ListAkuntansi;
import com.example.uasproject.Kimia.ListKimia;
import com.example.uasproject.Mesin.ListMesin;
import com.example.uasproject.Sipil.ListSipil;

public class MenuJurusan extends AppCompatActivity {

    private Button btn_Elektro, btn_Sipil, btn_Mesin, btn_Kimia, btn_Administrasi, btn_Akuntansi;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jurusan);

        findViewById(R.id.btn_elektro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(MenuJurusan.this, ListElektro.class));
            }
        });

        findViewById(R.id.btn_sipil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(MenuJurusan.this, ListSipil.class));
            }
        });

        findViewById(R.id.btn_mesin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(MenuJurusan.this, ListMesin.class));
            }
        });

        findViewById(R.id.btn_kimia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(MenuJurusan.this, ListKimia.class));
            }
        });

        findViewById(R.id.btn_administrasi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(MenuJurusan.this, ListAdministrasi.class));
            }
        });

        findViewById(R.id.btn_akuntansi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                startActivity(new Intent(MenuJurusan.this, ListAkuntansi.class));
            }
        });

    }
}