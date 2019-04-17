package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePembayaranPerUser{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("data_detail_pembayaran_user")
	private List<DataDetailPembayaranUserItem> dataDetailPembayaranUser;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}

	public void setDataDetailPembayaranUser(List<DataDetailPembayaranUserItem> dataDetailPembayaranUser){
		this.dataDetailPembayaranUser = dataDetailPembayaranUser;
	}

	public List<DataDetailPembayaranUserItem> getDataDetailPembayaranUser(){
		return dataDetailPembayaranUser;
	}
}