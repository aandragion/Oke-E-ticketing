package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_rating {
    @SerializedName("id_rating") private String id_rating;
    @SerializedName("tanggal") private String tanggal;
    @SerializedName("id_user") private String id_user;
    @SerializedName("nama") private String nama;
    @SerializedName("photo") private String photo;
    @SerializedName("id_film") private String id_film;
    @SerializedName("nilai") private String nilai;
    @SerializedName("ulasan") private String ulasan;

    public String getId() {
        return id_rating;
    }
    public String getTanggal() {
        return tanggal;
    }
    public String getId_user() {
        return id_user;
    }
    public String getNama() {
        return nama;
    }
    public String getPhoto() {
        return photo;
    }
    public String getId_film() {
        return id_film;
    }
    public float getNilai() {
        return Float.parseFloat(nilai);
    }
    public String getUlasan() {
        return ulasan;
    }
}
