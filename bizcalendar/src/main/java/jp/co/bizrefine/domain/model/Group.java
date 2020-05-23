package jp.co.bizrefine.domain.model;

/**
 * グループ
 */
public class Group {

	// グループID
	private int groupId;

	// グループ名
	private String groupName;

	// グループ種類
	private int groupType;

	// グループ有効グラフ
	private int groupValidF;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public int getGroupValidF() {
		return groupValidF;
	}

	public void setGroupValidF(int groupValidF) {
		this.groupValidF = groupValidF;
	}

}