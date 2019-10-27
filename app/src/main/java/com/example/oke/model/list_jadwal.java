package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_jadwal {
    private boolean isSelect;

    @SerializedName("id_jadwal") private String id_jadwal;
    @SerializedName("id_film") private String id_film;
    @SerializedName("tgl_jadwal") private String tgl_jadwal;
    @SerializedName("jam_tayang") private String jam_tayang;
    @SerializedName("harga") private String harga;
    @SerializedName("studio") private String studio;


    public String getId() {
        return id_jadwal;
    }
    public String getId_film() {
        return id_film;
    }
    public String getTgl_jadwal() {
        return tgl_jadwal;
    }
    public String getJam_tayang() {
        return jam_tayang;
    }
    public String getHarga() {
        return harga;
    }
    public String getStudio() {
        return studio;
    }
//    public boolean isSelect() {
//        return isSelect;
//    }
//    public void setSelect(boolean select) {
//        isSelect = select;
//    }

}
