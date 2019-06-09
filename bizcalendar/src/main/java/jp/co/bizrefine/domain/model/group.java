package jp.co.bizrefine.domain.model;

/**
 * グループ
 */
public class group {

	private int id;

	private String groupName;

	private int groupType;

	private int groupValidF;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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