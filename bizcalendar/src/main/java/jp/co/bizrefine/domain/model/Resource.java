package jp.co.bizrefine.domain.model;

/**
 * リソース
 */
public class Resource {

	private String id;

	private String building;

	private String title;

	private int className;

	private int statusName;

	private String statusIcon;

	private String eventColor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getClassName() {
		return className;
	}

	public void setEventClassName(int className) {
		this.className = className;
	}

	public int getStatusName() {
		return statusName;
	}

	public void setStatusName(int statusName) {
		this.statusName = statusName;
	}

	public String getStatusIcon() {
		return statusIcon;
	}

	public void setStatusIcon(String statusIcon) {
		this.statusIcon = statusIcon;
	}

	public String getEventColor() {
		return eventColor;
	}

	public void setEventColor(String eventColor) {
		this.eventColor = eventColor;
	}

}