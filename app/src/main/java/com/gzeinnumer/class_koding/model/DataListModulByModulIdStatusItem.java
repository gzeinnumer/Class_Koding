package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class DataListModulByModulIdStatusItem{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("modul_id")
	private String modulId;

	@SerializedName("progress_id")
	private String progressId;

	@SerializedName("status")
	private String status;

	public DataListModulByModulIdStatusItem(String userId, String materiId, String modulId, String progressId, String status) {
		this.userId = userId;
		this.materiId = materiId;
		this.modulId = modulId;
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