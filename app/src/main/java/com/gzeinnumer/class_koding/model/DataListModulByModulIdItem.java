package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class DataListModulByModulIdItem{

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("modul_id")
	private String modulId;

	@SerializedName("modul_judul")
	private String modulJudul;

	public DataListModulByModulIdItem(String materiId, String modulId, String modulJudul) {
		this.materiId = materiId;
		this.modulId = modulId;
		this.modulJudul = modulJudul;
	}

	public void setMateriId(String materiId){
		this.materiId = materiId;
	}

	public String getMateriId(){
		return materiId;
	}

	public void setModulId(String modulId){
		this.modulId = modulId;
	}

	public String getModulId(){
		return modulId;
	}

	public void setModulJudul(String modulJudul){
		this.modulJudul = modulJudul;
	}

	public String getModulJudul(){
		return modulJudul;
	}
}