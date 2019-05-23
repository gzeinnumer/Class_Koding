package com.gzeinnumer.class_koding.model;
//done
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseContentModul{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data_list_content_by_modul_id")
	private List<DataListContentByModulIdItem> dataListContentByModulId;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataListContentByModulId(List<DataListContentByModulIdItem> dataListContentByModulId){
		this.dataListContentByModulId = dataListContentByModulId;
	}

	public List<DataListContentByModulIdItem> getDataListContentByModulId(){
		return dataListContentByModulId;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}