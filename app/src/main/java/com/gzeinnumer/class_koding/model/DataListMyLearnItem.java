package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class DataListMyLearnItem{

	@SerializedName("pmby_bukti")
	private String pmbyBukti;

	@SerializedName("pmby_id")
	private String pmbyId;

	@SerializedName("materi_platform")
	private String materiPlatform;

	@SerializedName("pmby_tanggal")
	private String pmbyTanggal;

	@SerializedName("materi_nama")
	private String materiNama;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("pmby_batas")
	private String pmbyBatas;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("pmby_status")
	private String pmbyStatus;

	@SerializedName("materi_gambar")
	private String materiGambar;

	public String getMateriGambar() {
		return materiGambar;
	}

	public void setMateriGambar(String materiGambar) {
		this.materiGambar = materiGambar;
	}

	public DataListMyLearnItem(String pmbyBukti, String pmbyId, String materiPlatform, String pmbyTanggal, String materiNama, String userId, String pmbyBatas, String materiId, String pmbyStatus, String materiGambar) {
		this.pmbyBukti = pmbyBukti;
		this.pmbyId = pmbyId;
		this.materiPlatform = materiPlatform;
		this.pmbyTanggal = pmbyTanggal;
		this.materiNama = materiNama;
		this.userId = userId;
		this.pmbyBatas = pmbyBatas;
		this.materiId = materiId;
		this.pmbyStatus = pmbyStatus;
		this.materiGambar = materiGambar;
	}

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

	public void setMateriPlatform(String materiPlatform){
		this.materiPlatform = materiPlatform;
	}

	public String getMateriPlatform(){
		return materiPlatform;
	}

	public void setPmbyTanggal(String pmbyTanggal){
		this.pmbyTanggal = pmbyTanggal;
	}

	public String getPmbyTanggal(){
		return pmbyTanggal;
	}

	public void setMateriNama(String materiNama){
		this.materiNama = materiNama;
	}

	public String getMateriNama(){
		return materiNama;
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