package com.example.oke.model;



import com.google.gson.annotations.SerializedName;



public class list_film {
    @SerializedName("id_film") private String id_film;
    @SerializedName("judul_film") private String judul_film;
    @SerializedName("gambar") private String gambar;
    @SerializedName("sinopsis") private String sinopsis;
    @SerializedName("trailer") private String trailer;
    @SerializedName("id_genre") private String id_genre;
    @SerializedName("status_film") private String status_film;
    @SerializedName("durasi") private String durasi;
    @SerializedName("rilis") private String rilis;
    @SerializedName("total_view") private String total_view;

    public String getId() {
        return id_film;
    }
    public String getJudul_film() {
        return judul_film;
    }
    public String getGambar() {
        return gambar;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public String getTrailer() {
        return trailer;
    }
    public String getId_genre() {
        return id_genre;
    }
    public String getStatus_film() {
        return status_film;
    }
    public String getDurasi() {
        return durasi;
    }
    public String getRilis() {
        return rilis;
    }
    public String getTotal_view() {
        return total_view;
    }


}
