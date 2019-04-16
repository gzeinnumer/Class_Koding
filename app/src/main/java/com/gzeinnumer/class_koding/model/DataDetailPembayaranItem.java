package com.gzeinnumer.class_koding.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataDetailPembayaranItem implements Parcelable {

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

	public DataDetailPembayaranItem(String pmbyBukti, String pmbyId, String pmbyTanggal, String userId, String pmbyBatas, String materiId, String pmbyStatus) {
		this.pmbyBukti = pmbyBukti;
		this.pmbyId = pmbyId;
		this.pmbyTanggal = pmbyTanggal;
		this.userId = userId;
		this.pmbyBatas = pmbyBatas;
		this.materiId = materiId;
		this.pmbyStatus = pmbyStatus;
	}

	protected DataDetailPembayaranItem(Parcel in) {
		pmbyBukti = in.readString();
		pmbyId = in.readString();
		pmbyTanggal = in.readString();
		userId = in.readString();
		pmbyBatas = in.readString();
		materiId = in.readString();
		pmbyStatus = in.readString();
	}

	public static final Creator<DataDetailPembayaranItem> CREATOR = new Creator<DataDetailPembayaranItem>() {
		@Override
		public DataDetailPembayaranItem createFromParcel(Parcel in) {
			return new DataDetailPembayaranItem(in);
		}

		@Override
		public DataDetailPembayaranItem[] newArray(int size) {
			return new DataDetailPembayaranItem[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pmbyBukti);
		dest.writeString(pmbyId);
		dest.writeString(pmbyTanggal);
		dest.writeString(userId);
		dest.writeString(pmbyBatas);
		dest.writeString(materiId);
		dest.writeString(pmbyStatus);
	}
}