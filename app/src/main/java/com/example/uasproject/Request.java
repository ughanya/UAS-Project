package com.example.uasproject;


public class Request {

    private String id;
    private String nim;
    private String nama;
    //private String jurusan;
    private String prodi;
    private String telp;
    private String foto;

    public void Requests(){}


    public Request(String id, String nim, String nama, String prodi, String telp, String foto) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.telp = telp;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

