package com.gzeinnumer.class_koding.model;

import com.google.gson.annotations.SerializedName;

public class DataListModulByModulIdItem{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("modul_id")
	private String modulId;

	@SerializedName("modul_judul")
	private String modulJudul;

	@SerializedName("progress_id")
	private String progressId;

	@SerializedName("status")
	private String status;

	public DataListModulByModulIdItem(String userId, String materiId, String modulId, String modulJudul, String progressId, String status) {
		this.userId = userId;
		this.materiId = materiId;
		this.modulId = modulId;
		this.modulJudul = modulJudul;
		this.progressId = progressId;
		this.status = status;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
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

	public void setProgressId(String progressId){
		this.progressId = progressId;
	}

	public String getProgressId(){
		return progressId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}