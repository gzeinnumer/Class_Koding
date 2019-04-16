package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGetPembayaran{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("data_detail_pembayaran")
	private List<DataDetailPembayaranItem> dataDetailPembayaran;

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

	public void setDataDetailPembayaran(List<DataDetailPembayaranItem> dataDetailPembayaran){
		this.dataDetailPembayaran = dataDetailPembayaran;
	}

	public List<DataDetailPembayaranItem> getDataDetailPembayaran(){
		return dataDetailPembayaran;
	}
}