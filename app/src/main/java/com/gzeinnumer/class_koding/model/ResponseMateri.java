package com.gzeinnumer.class_koding.model;
//done

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMateri{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("data_materi")
	private List<DataMateriItem> dataMateri;

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

	public void setDataMateri(List<DataMateriItem> dataMateri){
		this.dataMateri = dataMateri;
	}

	public List<DataMateriItem> getDataMateri(){
		return dataMateri;
	}
}