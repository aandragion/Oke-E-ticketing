package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class saldo_icash {
    @SerializedName("id_icash")  String id_icash;
    @SerializedName("saldo_icash")  String saldo_icash;
    @SerializedName("pemasukan")  String pemasukan;
    @SerializedName("pengeluaran")  String pengeluaran;
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
