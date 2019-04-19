package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListModul{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data_list_modul_by_modul_id_status")
	private List<DataListModulByModulIdStatusItem> dataListModulByModulIdStatus;

	@SerializedName("data_list_modul_by_modul_id")
	private List<DataListModulByModulIdItem> dataListModulByModulId;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataListModulByModulIdStatus(List<DataListModulByModulIdStatusItem> dataListModulByModulIdStatus){
		this.dataListModulByModulIdStatus = dataListModulByModulIdStatus;
	}

	public List<DataListModulByModulIdStatusItem> getDataListModulByModulIdStatus(){
		return dataListModulByModulIdStatus;
	}

	public void setDataListModulByModulId(List<DataListModulByModulIdItem> dataListModulByModulId){
		this.dataListModulByModulId = dataListModulByModulId;
	}

	public List<DataListModulByModulIdItem> getDataListModulByModulId(){
		return dataListModulByModulId;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}