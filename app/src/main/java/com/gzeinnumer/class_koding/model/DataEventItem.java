package com.gzeinnumer.class_koding.model;
//done
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DataEventItem implements Parcelable {

	@SerializedName("event_gambar")
	private String eventGambar;

	@SerializedName("event_video")
	private String eventVideo;

	@SerializedName("event_nama")
	private String eventNama;

	@SerializedName("event_tgl_mulai")
	private String eventTglMulai;

	@SerializedName("mitra_id")
	private String mitraId;

	@SerializedName("event_tgl_selesai")
	private String eventTglSelesai;

	@SerializedName("event_xp")
	private String eventXp;

	@SerializedName("event_alamat")
	private String eventAlamat;

	@SerializedName("event_deskripsi")
	private String eventDeskripsi;

	@SerializedName("event_kuota")
	private String eventKuota;

	@SerializedName("event_jenis")
	private String eventJenis;

	@SerializedName("event_id")
	private String eventId;

	@SerializedName("event_tiket")
	private String eventTiket;

	@SerializedName("event_kota")
	private String eventKota;

	public DataEventItem(String eventGambar, String eventVideo, String eventNama, String eventTglMulai, String mitraId, String eventTglSelesai, String eventXp, String eventAlamat, String eventDeskripsi, String eventKuota, String eventJenis, String eventId, String eventTiket, String eventKota) {
		this.eventGambar = eventGambar;
		this.eventVideo = eventVideo;
		this.eventNama = eventNama;
		this.eventTglMulai = eventTglMulai;
		this.mitraId = mitraId;
		this.eventTglSelesai = eventTglSelesai;
		this.eventXp = eventXp;
		this.eventAlamat = eventAlamat;
		this.eventDeskripsi = eventDeskripsi;
		this.eventKuota = eventKuota;
		this.eventJenis = eventJenis;
		this.eventId = eventId;
		this.eventTiket = eventTiket;
		this.eventKota = eventKota;
	}

	protected DataEventItem(Parcel in) {
		eventGambar = in.readString();
		eventVideo = in.readString();
		eventNama = in.readString();
		eventTglMulai = in.readString();
		mitraId = in.readString();
		eventTglSelesai = in.readString();
		eventXp = in.readString();
		eventAlamat = in.readString();
		eventDeskripsi = in.readString();
		eventKuota = in.readString();
		eventJenis = in.readString();
		eventId = in.readString();
		eventTiket = in.readString();
		eventKota = in.readString();
	}

	public static final Creator<DataEventItem> CREATOR = new Creator<DataEventItem>() {
		@Override
		public DataEventItem createFromParcel(Parcel in) {
			return new DataEventItem(in);
		}

		@Override
		public DataEventItem[] newArray(int size) {
			return new DataEventItem[size];
		}
	};

	public void setEventGambar(String eventGambar){
		this.eventGambar = eventGambar;
	}

	public String getEventGambar(){
		return eventGambar;
	}

	public void setEventVideo(String eventVideo){
		this.eventVideo = eventVideo;
	}

	public String getEventVideo(){
		return eventVideo;
	}

	public void setEventNama(String eventNama){
		this.eventNama = eventNama;
	}

	public String getEventNama(){
		return eventNama;
	}

	public void setEventTglMulai(String eventTglMulai){
		this.eventTglMulai = eventTglMulai;
	}

	public String getEventTglMulai(){
		return eventTglMulai;
	}

	public void setMitraId(String mitraId){
		this.mitraId = mitraId;
	}

	public String getMitraId(){
		return mitraId;
	}

	public void setEventTglSelesai(String eventTglSelesai){
		this.eventTglSelesai = eventTglSelesai;
	}

	public String getEventTglSelesai(){
		return eventTglSelesai;
	}

	public void setEventXp(String eventXp){
		this.eventXp = eventXp;
	}

	public String getEventXp(){
		return eventXp;
	}

	public void setEventAlamat(String eventAlamat){
		this.eventAlamat = eventAlamat;
	}

	public String getEventAlamat(){
		return eventAlamat;
	}

	public void setEventDeskripsi(String eventDeskripsi){
		this.eventDeskripsi = eventDeskripsi;
	}

	public String getEventDeskripsi(){
		return eventDeskripsi;
	}

	public void setEventKuota(String eventKuota){
		this.eventKuota = eventKuota;
	}

	public String getEventKuota(){
		return eventKuota;
	}

	public void setEventJenis(String eventJenis){
		this.eventJenis = eventJenis;
	}

	public String getEventJenis(){
		return eventJenis;
	}

	public void setEventId(String eventId){
		this.eventId = eventId;
	}

	public String getEventId(){
		return eventId;
	}

	public void setEventTiket(String eventTiket){
		this.eventTiket = eventTiket;
	}

	public String getEventTiket(){
		return eventTiket;
	}

	public void setEventKota(String eventKota){
		this.eventKota = eventKota;
	}

	public String getEventKota(){
		return eventKota;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(eventGambar);
		dest.writeString(eventVideo);
		dest.writeString(eventNama);
		dest.writeString(eventTglMulai);
		dest.writeString(mitraId);
		dest.writeString(eventTglSelesai);
		dest.writeString(eventXp);
		dest.writeString(eventAlamat);
		dest.writeString(eventDeskripsi);
		dest.writeString(eventKuota);
		dest.writeString(eventJenis);
		dest.writeString(eventId);
		dest.writeString(eventTiket);
		dest.writeString(eventKota);
	}
}