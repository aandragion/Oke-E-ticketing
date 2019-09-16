package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class saldo {
    @SerializedName("id_icash") private String id_icash;
    @SerializedName("saldo_icash") private String saldo_icash;
    @SerializedName("pemasukan") private String pemasukan;
    @SerializedName("pengeluaran") private String pengeluaran;
    public String getId() {
        return id_icash;
    }

    public String getSaldo_icash() {
        return saldo_icash;
    }

    public String getPemasukan() {
        return pemasukan;
    }

    public String string() {
        return null;
    }
}
