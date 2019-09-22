package com.example.oke.apihelper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPrefManager {

    public static final String SP_MAHASISWA_APP = "spMahasiswaApp";
    public static  final  String SP_ID = "id_user";
    public static  final  String SP_ic = "saldo_icash";
    public static final String SP_NAMA = "username";
    public static final String SP_EMAIL = "email";
    public static final String SP_ALAMAT = "alamat";
    public static final String SP_NO_TLP = "no_tlp";
    public static final String SP_PASS = "password";


    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

//    public HashMap getDetailProfil()
//    {
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put(SP_NAMA, sp.getString(SP_NAMA, null));
//        map.put(SP_EMAIL, sp.getString(SP_EMAIL, null));
//
//        return map;
//    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.putString(keySP, value);
        spEditor.putString(keySP, value);
        spEditor.putString(keySP, value);
        spEditor.putString(keySP, value);
        spEditor.putString(keySP, value);
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public String getSPId(String nama, String spNama) {
        return sp.getString(SP_ID,"");
    }

    public String getSPNama(String nama, String spNama) {
        return sp.getString(SP_NAMA,"");
    }

    public String getSP_ic(String nama, String spNama) {
        return sp.getString(SP_ic,"");
    }

    public String getSpEmail(String nama, String spNama) {
        return sp.getString(SP_EMAIL,"");
    }

    public String getSpAlamat(String nama, String spNama) {
        return sp.getString(SP_ALAMAT,"");
    }

    public String getSpNoTlp(String nama, String spNama) {
        return sp.getString(SP_NO_TLP,"");
    }

    public String getSpPass(String nama, String spNama) {
        return sp.getString(SP_PASS,"");
    }
}
