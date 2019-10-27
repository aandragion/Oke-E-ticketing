package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_pemasukkan {
    @SerializedName("id_topup") private String id_topup;
    @SerializedName("tanggal") private String tanggal;
    @SerializedName("id_user") private String id_user;
    @SerializedName("id_bank") private String id_bank;
    @SerializedName("jumlah_transfer") private String jumlah_transfer;
    @SerializedName("rek_pemilik") private String rek_pemilik;
    @SerializedName("n_pemilik") private String n_pemilik;
    @SerializedName("bts_topup") private String bts_topup;
    @SerializedName("status_topup") private String status_topup;

    public String getId() {
        return id_topup;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getId_user() {
        return id_user;
    }
    public String getId_bank() {
        return id_bank;
    }
    public String getJumlah_transfer() {
        return jumlah_transfer;
    }
    public String getRek_pemilik() {
        return rek_pemilik;
    }
    public String getN_pemilik() {
        return n_pemilik;
    }
    public String getBts_topup() {
        return bts_topup;
    }
    public String getStatus_topup() {
        return status_topup;
    }
}
