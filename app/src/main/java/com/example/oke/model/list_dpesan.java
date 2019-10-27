package com.example.oke.model;

import com.google.gson.annotations.SerializedName;

public class list_dpesan {
	@SerializedName("id_pesan") private String id_pesan;
	@SerializedName("id_user") private String id_user;
	@SerializedName("tanggal_pesan") private String tanggal_pesan;
	@SerializedName("id_film") private String id_film;
	@SerializedName("judul") private String judul;
	@SerializedName("gambar") private String gambar;
	@SerializedName("id_jadwal") private String id_jadwal;
	@SerializedName("tgl_jadwal") private String tgl_jadwal;
	@SerializedName("jam_ayang") private String jam_ayang;
	@SerializedName("harga") private String harga;
	@SerializedName("studio") private String studio;
	@SerializedName("kursi") private String kursi;
	@SerializedName("jumlah_pesanan") private String jumlah_pesanan;
	@SerializedName("total_harga") private String total_harga;
	@SerializedName("status_pesanan") private String status_pesanan;

	public String getId() {
		return id_pesan;
	}
	public String getId_user() {
		return id_user;
	}
	public String getTanggal_pesan() {
		return tanggal_pesan;
	}
	public String getId_film() {
		return id_film;
	}
	public String getJudul() {
		return judul;
	}
	public String getGambar() {
		return gambar;
	}
	public String getId_jadwal() {
		return id_jadwal;
	}
	public String getTgl_jadwal() {
		return tgl_jadwal;
	}
	public String getJam_ayang() {
		return jam_ayang;
	}
	public String getHarga() {
		return harga;
	}
    public String getStudio() {
        return studio;
    }
    public String getKursi() {
        return kursi;
    }
    public String getJumlah_pesanan() {
        return jumlah_pesanan;
    }
    public String getTotal_harga() {
        return total_harga;
    }
    public String getStatus_pesanan() {
        return status_pesanan;
    }

}
