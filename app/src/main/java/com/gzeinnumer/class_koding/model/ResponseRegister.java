package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("sukses")
	private boolean sukses;

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}