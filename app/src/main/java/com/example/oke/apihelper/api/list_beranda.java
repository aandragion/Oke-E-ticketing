package com.example.oke.apihelper.api;



import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class list_beranda {
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
