package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseEvent{

	@SerializedName("data_event")
	private List<DataEventItem> dataEvent;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	public void setDataEvent(List<DataEventItem> dataEvent){
		this.dataEvent = dataEvent;
	}

	public List<DataEventItem> getDataEvent(){
		return dataEvent;
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