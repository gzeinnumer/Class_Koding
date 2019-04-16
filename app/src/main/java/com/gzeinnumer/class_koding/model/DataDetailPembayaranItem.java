package com.gzeinnumer.class_koding.model;

import com.google.gson.annotations.SerializedName;

public class DataDetailPembayaranItem{

	@SerializedName("pmby_bukti")
	private String pmbyBukti;

	@SerializedName("pmby_id")
	private String pmbyId;

	@SerializedName("pmby_tanggal")
	private String pmbyTanggal;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("pmby_batas")
	private String pmbyBatas;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("pmby_status")
	private String pmbyStatus;

	public void setPmbyBukti(String pmbyBukti){
		this.pmbyBukti = pmbyBukti;
	}

	public String getPmbyBukti(){
		return pmbyBukti;
	}

	public void setPmbyId(String pmbyId){
		this.pmbyId = pmbyId;
	}

	public String getPmbyId(){
		return pmbyId;
	}

	public void setPmbyTanggal(String pmbyTanggal){
		this.pmbyTanggal = pmbyTanggal;
	}

	public String getPmbyTanggal(){
		return pmbyTanggal;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setPmbyBatas(String pmbyBatas){
		this.pmbyBatas = pmbyBatas;
	}

	public String getPmbyBatas(){
		return pmbyBatas;
	}

	public void setMateriId(String materiId){
		this.materiId = materiId;
	}

	public String getMateriId(){
		return materiId;
	}

	public void setPmbyStatus(String pmbyStatus){
		this.pmbyStatus = pmbyStatus;
	}

	public String getPmbyStatus(){
		return pmbyStatus;
	}
}