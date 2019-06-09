package jp.co.bizrefine.domain.model;

/**
 * イベント
 */
public class Event {

	private int id;

	private int userId;

	private int groupId1;

	private int groupId2;

	private int groupId3;

	private String title;

	private String description;

	private String start;

	private String end;

	private int eventVaildF;

	private int className;

	private int statusName;

	private String resourceId;

	private String eventColor;

	private String statusIcon;

	private String name;

	private String eventType;

	private String eventStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getClassName() {
		return className;
	}

	public void setClassName(int className) {
		this.className = className;
	}

	public int getEventVaildF() {
		return eventVaildF;
	}

	public void setEventVaildF(int eventVaildF) {
		this.eventVaildF = eventVaildF;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public int getStatusName() {
		return statusName;
	}

	public void setStatusName(int statusName) {
		this.statusName = statusName;
	}

	public String getEventColor() {
		return eventColor;
	}

	public void setEventColor(String eventColor) {
		this.eventColor = eventColor;
	}

	public String getStatusIcon() {
		return statusIcon;
	}

	public void setStatusIcon(String statusIcon) {
		this.statusIcon = statusIcon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
}