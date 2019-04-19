package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMateriByIdFromProfil{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data_detail_materi_from_profil")
	private List<DataDetailMateriFromProfilItem> dataDetailMateriFromProfil;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataDetailMateriFromProfil(List<DataDetailMateriFromProfilItem> dataDetailMateriFromProfil){
		this.dataDetailMateriFromProfil = dataDetailMateriFromProfil;
	}

	public List<DataDetailMateriFromProfilItem> getDataDetailMateriFromProfil(){
		return dataDetailMateriFromProfil;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}