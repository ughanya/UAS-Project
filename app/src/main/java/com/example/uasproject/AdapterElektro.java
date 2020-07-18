package com.example.uasproject;

public class AdapterElektro {
    private String nama;
    private String prodi;
    private String foto;

    public AdapterElektro( String nama, String prodi, String foto ) {
        this.nama = nama;
        this.prodi = prodi;
        this.foto = foto;
    }

    public AdapterElektro() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama( String nama ) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi( String prodi ) {
        this.prodi = prodi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto( String foto ) {
        this.foto = foto;
    }
}
