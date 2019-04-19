package com.gzeinnumer.class_koding.model;

import com.google.gson.annotations.SerializedName;

public class DataMyLearnProgressItem {

	@SerializedName("belajar_status")
	private String belajarStatus;

	@SerializedName("materi_xp")
	private String materiXp;

	@SerializedName("materi_waktu")
	private String materiWaktu;

	@SerializedName("progress_modul")
	private String progressModul;

	@SerializedName("last_seen")
	private String lastSeen;

	@SerializedName("materi_nama")
	private String materiNama;

	@SerializedName("materi_diskon")
	private String materiDiskon;

	@SerializedName("materi_level")
	private String materiLevel;

	@SerializedName("materi_gambar")
	private String materiGambar;

	@SerializedName("belajar_deadline")
	private String belajarDeadline;

	@SerializedName("materi_rating")
	private String materiRating;

	@SerializedName("materi_platform")
	private String materiPlatform;

	@SerializedName("materi_deskripsi")
	private String materiDeskripsi;

	@SerializedName("materi_tgl")
	private String materiTgl;

	@SerializedName("belajar_mulai")
	private String belajarMulai;

	@SerializedName("materi_jml_modul")
	private String materiJmlModul;

	@SerializedName("materi_jum_siswa")
	private String materiJumSiswa;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("mitra_id")
	private String mitraId;

	@SerializedName("materi_video")
	private String materiVideo;

	@SerializedName("jenis_kelas_id")
	private String jenisKelasId;

	@SerializedName("materi_deadline")
	private String materiDeadline;

	@SerializedName("belajar_id")
	private String belajarId;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("materi_harga")
	private String materiHarga;

	public DataMyLearnProgressItem(String belajarStatus, String materiXp, String materiWaktu, String progressModul, String lastSeen, String materiNama, String materiDiskon, String materiLevel, String materiGambar, String belajarDeadline, String materiRating, String materiPlatform, String materiDeskripsi, String materiTgl, String belajarMulai, String materiJmlModul, String materiJumSiswa, String materiId, String mitraId, String materiVideo, String jenisKelasId, String materiDeadline, String belajarId, String userId, String materiHarga) {
		this.belajarStatus = belajarStatus;
		this.materiXp = materiXp;
		this.materiWaktu = materiWaktu;
		this.progressModul = progressModul;
		this.lastSeen = lastSeen;
		this.materiNama = materiNama;
		this.materiDiskon = materiDiskon;
		this.materiLevel = materiLevel;
		this.materiGambar = materiGambar;
		this.belajarDeadline = belajarDeadline;
		this.materiRating = materiRating;
		this.materiPlatform = materiPlatform;
		this.materiDeskripsi = materiDeskripsi;
		this.materiTgl = materiTgl;
		this.belajarMulai = belajarMulai;
		this.materiJmlModul = materiJmlModul;
		this.materiJumSiswa = materiJumSiswa;
		this.materiId = materiId;
		this.mitraId = mitraId;
		this.materiVideo = materiVideo;
		this.jenisKelasId = jenisKelasId;
		this.materiDeadline = materiDeadline;
		this.belajarId = belajarId;
		this.userId = userId;
		this.materiHarga = materiHarga;
	}

	public void setBelajarStatus(String belajarStatus){
		this.belajarStatus = belajarStatus;
	}

	public String getBelajarStatus(){
		return belajarStatus;
	}

	public void setMateriXp(String materiXp){
		this.materiXp = materiXp;
	}

	public String getMateriXp(){
		return materiXp;
	}

	public void setMateriWaktu(String materiWaktu){
		this.materiWaktu = materiWaktu;
	}

	public String getMateriWaktu(){
		return materiWaktu;
	}

	public void setProgressModul(String progressModul){
		this.progressModul = progressModul;
	}

	public String getProgressModul(){
		return progressModul;
	}

	public void setLastSeen(String lastSeen){
		this.lastSeen = lastSeen;
	}

	public String getLastSeen(){
		return lastSeen;
	}

	public void setMateriNama(String materiNama){
		this.materiNama = materiNama;
	}

	public String getMateriNama(){
		return materiNama;
	}

	public void setMateriDiskon(String materiDiskon){
		this.materiDiskon = materiDiskon;
	}

	public String getMateriDiskon(){
		return materiDiskon;
	}

	public void setMateriLevel(String materiLevel){
		this.materiLevel = materiLevel;
	}

	public String getMateriLevel(){
		return materiLevel;
	}

	public void setMateriGambar(String materiGambar){
		this.materiGambar = materiGambar;
	}

	public String getMateriGambar(){
		return materiGambar;
	}

	public void setBelajarDeadline(String belajarDeadline){
		this.belajarDeadline = belajarDeadline;
	}

	public String getBelajarDeadline(){
		return belajarDeadline;
	}

	public void setMateriRating(String materiRating){
		this.materiRating = materiRating;
	}

	public String getMateriRating(){
		return materiRating;
	}

	public void setMateriPlatform(String materiPlatform){
		this.materiPlatform = materiPlatform;
	}

	public String getMateriPlatform(){
		return materiPlatform;
	}

	public void setMateriDeskripsi(String materiDeskripsi){
		this.materiDeskripsi = materiDeskripsi;
	}

	public String getMateriDeskripsi(){
		return materiDeskripsi;
	}

	public void setMateriTgl(String materiTgl){
		this.materiTgl = materiTgl;
	}

	public String getMateriTgl(){
		return materiTgl;
	}

	public void setBelajarMulai(String belajarMulai){
		this.belajarMulai = belajarMulai;
	}

	public String getBelajarMulai(){
		return belajarMulai;
	}

	public void setMateriJmlModul(String materiJmlModul){
		this.materiJmlModul = materiJmlModul;
	}

	public String getMateriJmlModul(){
		return materiJmlModul;
	}

	public void setMateriJumSiswa(String materiJumSiswa){
		this.materiJumSiswa = materiJumSiswa;
	}

	public String getMateriJumSiswa(){
		return materiJumSiswa;
	}

	public void setMateriId(String materiId){
		this.materiId = materiId;
	}

	public String getMateriId(){
		return materiId;
	}

	public void setMitraId(String mitraId){
		this.mitraId = mitraId;
	}

	public String getMitraId(){
		return mitraId;
	}

	public void setMateriVideo(String materiVideo){
		this.materiVideo = materiVideo;
	}

	public String getMateriVideo(){
		return materiVideo;
	}

	public void setJenisKelasId(String jenisKelasId){
		this.jenisKelasId = jenisKelasId;
	}

	public String getJenisKelasId(){
		return jenisKelasId;
	}

	public void setMateriDeadline(String materiDeadline){
		this.materiDeadline = materiDeadline;
	}

	public String getMateriDeadline(){
		return materiDeadline;
	}

	public void setBelajarId(String belajarId){
		this.belajarId = belajarId;
	}

	public String getBelajarId(){
		return belajarId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setMateriHarga(String materiHarga){
		this.materiHarga = materiHarga;
	}

	public String getMateriHarga(){
		return materiHarga;
	}
}