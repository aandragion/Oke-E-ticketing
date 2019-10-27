package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class list_studio {
    @SerializedName("studio") private String studio;
    @SerializedName("semuastudio")private List<list_studio> semuastudio;
    public List<list_studio> getSemuastudio(){
        return semuastudio;
    }

    public String getId_studio() {
        return studio;
    }
}
