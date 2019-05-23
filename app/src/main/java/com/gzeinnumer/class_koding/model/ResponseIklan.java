package com.gzeinnumer.class_koding.model;
//done

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseIklan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("data_iklan")
	private List<DataIklanItem> dataIklan;

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

	public void setDataIklan(List<DataIklanItem> dataIklan){
		this.dataIklan = dataIklan;
	}

	public List<DataIklanItem> getDataIklan(){
		return dataIklan;
	}
}