package com.example.daftarmahasiswa;

public class dataMahasiswa {
    private String id;
    private String nama;
    private String nim;
    private String jurusan;
    private String alamat;

    public dataMahasiswa() {
    }

    public dataMahasiswa(String id, String nama, String nim, String jurusan, String alamat) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}


