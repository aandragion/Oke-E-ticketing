package com.example.oke.library;

public class load {
    public static String foto(String photo){
        String hasil = "";

        hasil = "https://cobabioskop.000webhostapp.com/upload/gbrfilm/" +photo;

        return hasil;
    }

//    public static String video(String movie){
//        String hasil = "";
//
//        hasil = "http://192.168.8.109/webadmin/upload/vdfilm/" +movie;
//
//        return hasil;
//    }

    public static String gambar(String gambar){
        String hasil = "";

        hasil = "https://cobabioskop.000webhostapp.com/gambar/" +gambar;

        return hasil;
    }
}
