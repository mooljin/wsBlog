package com.wscompany.dto;


public class UserData {
	private int userNum;
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private String userEmail;
	private String userTel;

	public UserData() {
	}

	public UserData(int userNum, String userId, String userName, String userNick, String userEmail, String userTel) {
		this.userNum = userNum;
		this.userId = userId;
		this.userName = userName;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userTel = userTel;
	}

	public UserData(int userNum, String userId, String userPw, String userName, String userNick, String userEmail, String userTel) {
		this.userNum = userNum;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNick = userNick;
		this.userEmail = userEmail;
		this.userTel = userTel;
	}


	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Override
	public String toString() {
		return "UserData [userNum=" + userNum + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userNick=" + userNick + ", userEmail=" + userEmail + ", userTel=" + userTel + "]";
	}
}
