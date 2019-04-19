package com.gzeinnumer.class_koding.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseListMyLearn{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data_list_my_learn")
	private List<DataListMyLearnItem> dataListMyLearn;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setDataListMyLearn(List<DataListMyLearnItem> dataListMyLearn){
		this.dataListMyLearn = dataListMyLearn;
	}

	public List<DataListMyLearnItem> getDataListMyLearn(){
		return dataListMyLearn;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}