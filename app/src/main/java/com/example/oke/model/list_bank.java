package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_bank {

    @SerializedName("id_bank") private String id_bank;
    @SerializedName("nama_bank") private String nama_bank;
    @SerializedName("logo_bank") private String logo_bank;
    @SerializedName("no_rekening") private String no_rekening;
    @SerializedName("nama_pemilik") private String nama_pemilik;
    @SerializedName("id_genre") private String id_genre;
    @SerializedName("status_film") private String status_film;
    @SerializedName("durasi") private String durasi;
    @SerializedName("rilis") private String rilis;
    @SerializedName("total_view") private String total_view;

    public String getId() {
        return id_bank;
    }
    public String getNama_bank() {
        return nama_bank;
    }
    public String getLogo_bank() {
        return logo_bank;
    }
    public String getNo_rekening() {
        return no_rekening;
    }
    public String getNama_pemilik() {
        return nama_pemilik;
    }

}
