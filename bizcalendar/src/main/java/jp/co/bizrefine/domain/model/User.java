package jp.co.bizrefine.domain.model;

/**
 * ユーザー
 */
public class User {

	// ユーザーID
	private int userId;

	// メールアドレス
	private String userEmail;

	// パスワード
	private String userPass;

	// 名前
	private String name;

	// 社員番号
	private String employeeNumber;

	// 誕生日
	private String birtyday;

	// グループID
	private int groupId;

	// 管理者フラグ
	private int adminF;

	// ユーザー有効フラグ
	private int userVaildF;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getAdminF() {
		return adminF;
	}

	public void setAdminF(int adminF) {
		this.adminF = adminF;
	}

	public int getUserVaildF() {
		return userVaildF;
	}

	public void setUserVaildF(int userVaildF) {
		this.userVaildF = userVaildF;
	}

}