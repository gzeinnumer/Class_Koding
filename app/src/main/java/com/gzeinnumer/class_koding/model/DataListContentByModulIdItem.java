package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class DataListContentByModulIdItem{

	@SerializedName("content_urutan")
	private String contentUrutan;

	@SerializedName("modul_id")
	private String modulId;

	@SerializedName("content_isi")
	private String contentIsi;

	@SerializedName("modul_tipe")
	private String modulTipe;

	public DataListContentByModulIdItem(String contentUrutan, String modulId, String contentIsi, String modulTipe) {
		this.contentUrutan = contentUrutan;
		this.modulId = modulId;
		this.contentIsi = contentIsi;
		this.modulTipe = modulTipe;
	}

	public void setContentUrutan(String contentUrutan){
		this.contentUrutan = contentUrutan;
	}

	public String getContentUrutan(){
		return contentUrutan;
	}

	public void setModulId(String modulId){
		this.modulId = modulId;
	}

	public String getModulId(){
		return modulId;
	}

	public void setContentIsi(String contentIsi){
		this.contentIsi = contentIsi;
	}

	public String getContentIsi(){
		return contentIsi;
	}

	public void setModulTipe(String modulTipe){
		this.modulTipe = modulTipe;
	}

	public String getModulTipe(){
		return modulTipe;
	}
}