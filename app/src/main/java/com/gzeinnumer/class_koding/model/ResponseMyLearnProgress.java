package com.gzeinnumer.class_koding.model;
//done

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseMyLearnProgress {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	@SerializedName("data_my_learn")
	private List<DataMyLearnProgressItem> dataMyLearn;

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

	public void setDataMyLearn(List<DataMyLearnProgressItem> dataMyLearn){
		this.dataMyLearn = dataMyLearn;
	}

	public List<DataMyLearnProgressItem> getDataMyLearn(){
		return dataMyLearn;
	}
}