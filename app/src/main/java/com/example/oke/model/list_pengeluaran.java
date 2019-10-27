package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_pengeluaran {
    @SerializedName("id_keluar") private String id_keluar;
    @SerializedName("tgl") private String tgl;
    @SerializedName("id_user") private String id_user;
    @SerializedName("judul") private String judul;
    @SerializedName("harga") private String harga;
    @SerializedName("jumlah") private String jumlah;
    @SerializedName("total") private String total;


    public String getId() {
        return id_keluar;
    }
    public String getTgl() {
        return tgl;
    }
    public String getId_user() {
        return id_user;
    }
    public String getJudul() {
        return judul;
    }
    public String getHarga() {
        return harga;
    }
    public String getJumlah() {
        return jumlah;
    }
    public String getTotal() {
        return total;
    }

}
