package jp.co.bizrefine.domain.model;

import java.util.List;

/**
 * リソース
 */
public class Resource {

	// イベントID
	private int eventId;

	// ビルディング種類ID
	private int buildingTypeId;

	// ビルディングID
	private int buildingId;

	// 親リソースID
	private String parentResourceTypeId;

	// 子リソースID
	private String childResourceTypeId;

	/** 表示内容 */
	// イベント色
	private String eventColor;

	// ビルディング、
	private String building;

	// リソースID
	private String id;

	// タイトル
	private String title;

	// 子リソースリスト
	private List<Resource> children;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getBuildingTypeId() {
		return buildingTypeId;
	}

	public void setBuildingTypeId(int buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getParentResourceTypeId() {
		return parentResourceTypeId;
	}

	public void setParentResourceTypeId(String parentResourceTypeId) {
		this.parentResourceTypeId = parentResourceTypeId;
	}

	public String getChildResourceTypeId() {
		return childResourceTypeId;
	}

	public void setChildResourceTypeId(String childResourceTypeId) {
		this.childResourceTypeId = childResourceTypeId;
	}

	public String getEventColor() {
		return eventColor;
	}

	public void setEventColor(String eventColor) {
		this.eventColor = eventColor;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}


}