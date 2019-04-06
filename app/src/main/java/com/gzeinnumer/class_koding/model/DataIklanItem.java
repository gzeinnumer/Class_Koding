package com.gzeinnumer.class_koding.model;

import com.google.gson.annotations.SerializedName;

public class DataIklanItem{

	@SerializedName("iklan_m_id")
	private String iklanMId;

	@SerializedName("iklan_tgl_mulai")
	private String iklanTglMulai;

	@SerializedName("mitra_id")
	private String mitraId;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("iklan_tgl_selesai")
	private String iklanTglSelesai;

	@SerializedName("iklan_m_gambar")
	private String iklanMGambar;

	public void setIklanMId(String iklanMId){
		this.iklanMId = iklanMId;
	}

	public String getIklanMId(){
		return iklanMId;
	}

	public void setIklanTglMulai(String iklanTglMulai){
		this.iklanTglMulai = iklanTglMulai;
	}

	public String getIklanTglMulai(){
		return iklanTglMulai;
	}

	public void setMitraId(String mitraId){
		this.mitraId = mitraId;
	}

	public String getMitraId(){
		return mitraId;
	}

	public void setMateriId(String materiId){
		this.materiId = materiId;
	}

	public String getMateriId(){
		return materiId;
	}

	public void setIklanTglSelesai(String iklanTglSelesai){
		this.iklanTglSelesai = iklanTglSelesai;
	}

	public String getIklanTglSelesai(){
		return iklanTglSelesai;
	}

	public void setIklanMGambar(String iklanMGambar){
		this.iklanMGambar = iklanMGambar;
	}

	public String getIklanMGambar(){
		return iklanMGambar;
	}
}