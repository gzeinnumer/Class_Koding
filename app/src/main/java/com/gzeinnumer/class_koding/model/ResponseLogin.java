package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("data_login")
	private List<DataLoginItem> dataLogin;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	public void setDataLogin(List<DataLoginItem> dataLogin){
		this.dataLogin = dataLogin;
	}

	public List<DataLoginItem> getDataLogin(){
		return dataLogin;
	}

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
}