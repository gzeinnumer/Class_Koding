package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseDataUser{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data_user")
	private List<DataUserItem> dataUser;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataUser(List<DataUserItem> dataUser){
		this.dataUser = dataUser;
	}

	public List<DataUserItem> getDataUser(){
		return dataUser;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}