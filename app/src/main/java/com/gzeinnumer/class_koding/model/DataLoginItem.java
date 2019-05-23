package com.gzeinnumer.class_koding.model;
//done
import com.google.gson.annotations.SerializedName;

public class DataLoginItem{

	@SerializedName("user_email")
	private String userEmail;

	@SerializedName("user_password")
	private String userPassword;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("user_image")
	private String userImage;

	@SerializedName("user_asal")
	private String userAsal;

	@SerializedName("user_name")
	private String userName;

	@SerializedName("user_xp")
	private String userXp;

	@SerializedName("user_date")
	private String userDate;

	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserImage(String userImage){
		this.userImage = userImage;
	}

	public String getUserImage(){
		return userImage;
	}

	public void setUserAsal(String userAsal){
		this.userAsal = userAsal;
	}

	public String getUserAsal(){
		return userAsal;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserXp(String userXp){
		this.userXp = userXp;
	}

	public String getUserXp(){
		return userXp;
	}

	public void setUserDate(String userDate){
		this.userDate = userDate;
	}

	public String getUserDate(){
		return userDate;
	}
}