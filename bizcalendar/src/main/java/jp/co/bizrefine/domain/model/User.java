package jp.co.bizrefine.domain.model;

/**
 * ユーザー
 */
public class User {

	private int userId;

	private String name;

	private String employeeNumber;

	private String birtyday;

	private int groupId1;

	private int groupId2;

	private int groupId3;

	private int adminF;

	private String userEmail;

	private String userPass;

	private int userVaildF;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getBirtyday() {
		return birtyday;
	}

	public void setBirtyday(String birtyday) {
		this.birtyday = birtyday;
	}

	public int getGroupId1() {
		return groupId1;
	}

	public void setGroupId1(int groupId1) {
		this.groupId1 = groupId1;
	}

	public int getGroupId2() {
		return groupId2;
	}

	public void setGroupId2(int groupId2) {
		this.groupId2 = groupId2;
	}

	public int getGroupId3() {
		return groupId3;
	}

	public void setGroupId3(int groupId3) {
		this.groupId3 = groupId3;
	}

	public int getAdminF() {
		return adminF;
	}

	public void setAdminF(int adminF) {
		this.adminF = adminF;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public int getUserVaildF() {
		return userVaildF;
	}

	public void setUserVaildF(int userVaildF) {
		this.userVaildF = userVaildF;
	}

}