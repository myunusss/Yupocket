package com.blogspot.yupocket.yupocket;

public class Record {

    private int id;
    private String tanggal;
    private String jenis;
    private String keterangan;
    private Float biaya;

    public Record() {
    }

    public Record(String tanggal, String jenis, String keterangan, Float biaya) {
        this.tanggal = tanggal;
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.biaya = biaya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Float getBiaya() {
        return biaya;
    }

    public void setBiaya(Float biaya) {
        this.biaya = biaya;
    }
}
