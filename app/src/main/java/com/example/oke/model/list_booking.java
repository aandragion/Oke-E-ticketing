package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_booking {
    @SerializedName("s_kursi")
    private String s_kursi;

    public list_booking(String s_kursi) {
        this.s_kursi = s_kursi;
    }

    public String getS_kursi() {
        return s_kursi;
    }
}
