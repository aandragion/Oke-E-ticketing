package com.example.oke.model;



import com.google.gson.annotations.SerializedName;



public class list_film {
    @SerializedName("id_film") private String id_film;
    @SerializedName("judul_film") private String judul_film;
    @SerializedName("gambar") private String gambar;

    public String getId() {
        return id_film;
    }

    public String getJudul_film() {
        return judul_film;
    }

    public String getGambar() {
        return gambar;
    }
}
