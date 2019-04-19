package com.gzeinnumer.class_koding.model;

import com.google.gson.annotations.SerializedName;

public class ResponseRegisToTableBelajar{

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("pesan")
	private String pesan;

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}

	public String getPesan() {
		return pesan;
	}

	public void setPesan(String pesan) {
		this.pesan = pesan;
	}
}