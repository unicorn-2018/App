package jp.co.bizrefine.domain.model;

import java.util.List;

/**
 * イベント
 */
public class Event {

	// イベントID
	private int id;

	// タイトル
	private String title;

	// 開始日
	private String start;

	// 終了日
	private String end;

	// コメント
	private String description;

	// 終日フラグ
	private boolean allDay;

	// 編集権限ID
	private String editAuthId;

	// 編集権限フラグ
	private boolean taskFlag;

	// イベントアイコンID
	private String eventIconId;

	// イベントアイコンクラス
	private String eventIconClass;

	// イベントアイコン名称
	private String eventIconName;

	// イベント種別ID
	private String eventTypeId;

	// イベント種別色
	private String eventTypeColor;

	// イベント種別名称
	private String eventTypeName;

	// タスク状況ID
	private String taskStatusId;

	// タスク状況名称
	private String taskStatusName;

	// 作成者ID
	private int createUserId;

	// 作成者名
	private String createName;

	// 更新者ID
	private int updateUserId;

	// 更新者名
	private String updateName;

	// イベント有効フラグ
	private int eventVaildF;

	// イベントバージョン
	private int version;

	// リソースID
	private String resourceId;

	/** 取得条件 */
	// ユーザーIDパラメータ
	private String userIdParam;

	// グループIDパラメータ
	private String groupIdParam;

	// ビルディングIDパラメータ
	private String buildingIdParam;

	// ビルディングパラメータ
	private String buildingParam;

	// タスク期限フラグ
	private boolean taskKigenFlag;

	// ログインIDパラメータ
	private int loginIdParam;

	// 選択区分
	private int selectKubun;

	// 編集権限フラグ
	private boolean editAuthFlag;

	// リソースリスト
	private List<Resource> resources;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getEditAuthId() {
		return editAuthId;
	}

	public void setEditAuthId(String editAuthId) {
		this.editAuthId = editAuthId;
	}

	public boolean isTaskFlag() {
		return taskFlag;
	}

	public void setTaskFlag(boolean taskFlag) {
		this.taskFlag = taskFlag;
	}

	public String getEventIconId() {
		return eventIconId;
	}

	public void setEventIconId(String eventIconId) {
		this.eventIconId = eventIconId;
	}

	public String getEventIconName() {
		return eventIconName;
	}

	public void setEventIconName(String eventIconName) {
		this.eventIconName = eventIconName;
	}

	public String getEventIconClass() {
		return eventIconClass;
	}

	public void setEventIconClass(String eventIconClass) {
		this.eventIconClass = eventIconClass;
	}

	public String getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public String getEventTypeColor() {
		return eventTypeColor;
	}

	public void setEventTypeColor(String eventTypeColor) {
		this.eventTypeColor = eventTypeColor;
	}

	public String getTaskStatusId() {
		return taskStatusId;
	}

	public void setTaskStatusId(String taskStatusId) {
		this.taskStatusId = taskStatusId;
	}

	public String getTaskStatusName() {
		return taskStatusName;
	}

	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public int getEventVaildF() {
		return eventVaildF;
	}

	public void setEventVaildF(int eventVaildF) {
		this.eventVaildF = eventVaildF;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUserIdParam() {
		return userIdParam;
	}

	public void setUserIdParam(String userIdParam) {
		this.userIdParam = userIdParam;
	}

	public String getGroupIdParam() {
		return groupIdParam;
	}

	public void setGroupIdParam(String groupIdParam) {
		this.groupIdParam = groupIdParam;
	}

	public String getBuildingIdParam() {
		return buildingIdParam;
	}

	public void setBuildingIdParam(String buildingIdParam) {
		this.buildingIdParam = buildingIdParam;
	}

	public String getBuildingParam() {
		return buildingParam;
	}

	public void setBuildingParam(String buildingParam) {
		this.buildingParam = buildingParam;
	}

	public boolean isTaskKigenFlag() {
		return taskKigenFlag;
	}

	public void setTaskKigenFlag(boolean taskKigenFlag) {
		this.taskKigenFlag = taskKigenFlag;
	}

	public int getLoginIdParam() {
		return loginIdParam;
	}

	public void setLoginIdParam(int loginIdParam) {
		this.loginIdParam = loginIdParam;
	}

	public int getSelectKubun() {
		return selectKubun;
	}

	public void setSelectKubun(int selectKubun) {
		this.selectKubun = selectKubun;
	}

	public boolean isEditAuthFlag() {
		return editAuthFlag;
	}

	public void setEditAuthFlag(boolean editAuthFlag) {
		this.editAuthFlag = editAuthFlag;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}