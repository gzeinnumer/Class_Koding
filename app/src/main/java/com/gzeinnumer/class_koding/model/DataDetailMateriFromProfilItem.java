package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class DataDetailMateriFromProfilItem{

	@SerializedName("materi_jml_modul")
	private String materiJmlModul;

	@SerializedName("materi_xp")
	private String materiXp;

	@SerializedName("materi_waktu")
	private String materiWaktu;

	@SerializedName("materi_nama")
	private String materiNama;

	@SerializedName("materi_diskon")
	private String materiDiskon;

	@SerializedName("materi_level")
	private String materiLevel;

	@SerializedName("materi_jum_siswa")
	private String materiJumSiswa;

	@SerializedName("materi_gambar")
	private String materiGambar;

	@SerializedName("materi_id")
	private String materiId;

	@SerializedName("mitra_id")
	private String mitraId;

	@SerializedName("materi_video")
	private String materiVideo;

	@SerializedName("materi_rating")
	private String materiRating;

	@SerializedName("jenis_kelas_id")
	private String jenisKelasId;

	@SerializedName("materi_deadline")
	private String materiDeadline;

	@SerializedName("materi_platform")
	private String materiPlatform;

	@SerializedName("materi_deskripsi")
	private String materiDeskripsi;

	@SerializedName("materi_harga")
	private String materiHarga;

	@SerializedName("materi_tgl")
	private String materiTgl;

	public DataDetailMateriFromProfilItem(String materiJmlModul, String materiXp, String materiWaktu, String materiNama, String materiDiskon, String materiLevel, String materiJumSiswa, String materiGambar, String materiId, String mitraId, String materiVideo, String materiRating, String jenisKelasId, String materiDeadline, String materiPlatform, String materiDeskripsi, String materiHarga, String materiTgl) {
		this.materiJmlModul = materiJmlModul;
		this.materiXp = materiXp;
		this.materiWaktu = materiWaktu;
		this.materiNama = materiNama;
		this.materiDiskon = materiDiskon;
		this.materiLevel = materiLevel;
		this.materiJumSiswa = materiJumSiswa;
		this.materiGambar = materiGambar;
		this.materiId = materiId;
		this.mitraId = mitraId;
		this.materiVideo = materiVideo;
		this.materiRating = materiRating;
		this.jenisKelasId = jenisKelasId;
		this.materiDeadline = materiDeadline;
		this.materiPlatform = materiPlatform;
		this.materiDeskripsi = materiDeskripsi;
		this.materiHarga = materiHarga;
		this.materiTgl = materiTgl;
	}

	public void setMateriJmlModul(String materiJmlModul){
		this.materiJmlModul = materiJmlModul;
	}

	public String getMateriJmlModul(){
		return materiJmlModul;
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

	public void setMateriJumSiswa(String materiJumSiswa){
		this.materiJumSiswa = materiJumSiswa;
	}

	public String getMateriJumSiswa(){
		return materiJumSiswa;
	}

	public void setMateriGambar(String materiGambar){
		this.materiGambar = materiGambar;
	}

	public String getMateriGambar(){
		return materiGambar;
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

	public void setMateriRating(String materiRating){
		this.materiRating = materiRating;
	}

	public String getMateriRating(){
		return materiRating;
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

	public void setMateriHarga(String materiHarga){
		this.materiHarga = materiHarga;
	}

	public String getMateriHarga(){
		return materiHarga;
	}

	public void setMateriTgl(String materiTgl){
		this.materiTgl = materiTgl;
	}

	public String getMateriTgl(){
		return materiTgl;
	}
}