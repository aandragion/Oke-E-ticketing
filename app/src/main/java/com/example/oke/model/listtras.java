package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class listtras {
    @SerializedName("id_transfer") private String id_transfer;
    @SerializedName("id_user") private String id_user;
    @SerializedName("tanggal") private String tanggal;
    @SerializedName("id_pesan") private String id_pesan;
    @SerializedName("total_harga") private String total_harga;
    @SerializedName("id_bank") private String id_bank;
    @SerializedName("logo_bank") private String logo_bank;
    @SerializedName("nama_pemilik") private String nama_pemilik;
    @SerializedName("no_rekening") private String no_rekening;
    @SerializedName("nama") private String nama;
    @SerializedName("bts_transfer") private String bts_transfer;
    @SerializedName("id_status") private String id_status;

    public String getId() {
        return id_transfer;
    }
    public String getId_user() {
        return id_user;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getId_pesan() {
        return id_pesan;
    }
    public String getTotal_harga() {
        return total_harga;
    }
    public String getId_bank() {
        return id_bank;
    }
    public String getLogo_bank() {
        return logo_bank;
    }
    public String getNama_pemilik() {
        return nama_pemilik;
    }
    public String getNo_rekening() {
        return no_rekening;
    }
    public String getN_pemilik() {
        return nama;
    }
    public String getBts_transfer() {
        return bts_transfer;
    }
    public String getId_status() {
        return id_status;
    }
}
