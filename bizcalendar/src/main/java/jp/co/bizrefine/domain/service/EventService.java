package jp.co.bizrefine.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ajd4jp.AJDException;
import jp.co.bizrefine.domain.mapper.EventMapper;
import jp.co.bizrefine.domain.mapper.UserMapper;
import jp.co.bizrefine.domain.model.Code;
import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.model.Message;
import jp.co.bizrefine.domain.model.Resource;
import jp.co.bizrefine.util.MakeCalendarUtil;

@Service
public class EventService {

	@Autowired
	EventMapper eventMapper;
	@Autowired
	UserMapper userMapper;

	public static final class MessageNaiyo {
		// タイトル
		public static final String TITLE_INSERT_1 = "追加されました";
		public static final String TITLE_UPDATE_1 = "変更されました";
		public static final String TITLE_UPDATE_2 = "他のユーザーによって削除されたため、操作をやり直してください";
		public static final String TITLE_UPDATE_3 = "他のユーザーによって変更されたため、操作をやり直してください";
		public static final String TITLE_DELETE_1 = "削除されました";
		// テキスト
		public static final String TEXT_1 = "３秒後にアラートが自動的に閉じます";
		// アイコン
		public static final String ICON_SUCCESS = "success";
		public static final String ICON_ERROR = "error";
		public static final String ICON_WARNING = "warning";
		public static final String ICON_INFO = "info";
		// 表示時間
		public static final int TIMER_3M = 3000;
		// ボタン非表示
		public static final boolean BUTTONS_HIDDEN = false;
		// ボタン表示
		public static final boolean BUTTONS_DISPLAY = true;
	};

	/**
	 * カレンダーのイベントを取得する
	 * @param event イベント
	 * @return List<Event> イベントリスト
	 */
	public List<Event> findEvents(Event event) throws AJDException, JsonParseException, JsonMappingException {
		List<Event> events = eventMapper.findEvents(event);
		// タスク一覧選択時以外の場合
		if (1 != event.getSelectKubun()) {
			// 祝日の設定
			List<Event> holiday = MakeCalendarUtil.getHoliDay(event.getStart().replace("-", "").substring(0, 4));
			events.addAll(holiday);
			// 誕生日の設定
			List<Event> birthdays = userMapper.findBirthday(event);
			events.addAll(birthdays);
		}
		return events;
	}

	/**
	 * カレンダーのリソースを取得する
	 * @param event イベント
	 * @return List<Resource> リソースリスト
	 */
	public List<Resource> findResources() throws JsonParseException, JsonMappingException {
		List<Resource> resources = eventMapper.findResources();
		List<Resource> childResources = eventMapper.findChildResources();
		for(Resource parentResource : resources) {
			List<Resource> tmpResources = new ArrayList<Resource>();
			if (childResources.size() > 0) {
				for(Resource childResource : childResources) {
					if (childResource.getId().startsWith(parentResource.getId())) {
						tmpResources.add(childResource);
					}
				}
			}
			if (tmpResources.size() > 0) {
				parentResource.setChildren(tmpResources);
			}
		}
		return resources;
	}

	/**
	 * コードリストを取得する
	 * @param event イベント
	 * @return List<Code> コードリスト
	 */
	public List<Code> findCodes(Code code) throws JsonParseException, JsonMappingException {
		List<Code> codes = eventMapper.findCodes(code);
		return codes;
	}

	/**
	 * カレンダーのイベントを更新する
	 * @param event イベント
	 * @return Message メッセージ
	 */
	public Message updateEvent(Event event) throws JsonParseException, JsonMappingException {
		Message message = new Message();
		Event beforeEvent = eventMapper.findEvent(event.getId());
		if (beforeEvent == null) {
			message.setTitle(MessageNaiyo.TITLE_UPDATE_2);
			message.setText(MessageNaiyo.TEXT_1);
			message.setIcon(MessageNaiyo.ICON_ERROR);
			message.setTimer(MessageNaiyo.TIMER_3M);
			message.setButtons(MessageNaiyo.BUTTONS_HIDDEN);
		} else if (beforeEvent.getVersion() > event.getVersion()) {
			message.setTitle(MessageNaiyo.TITLE_UPDATE_3);
			message.setText(MessageNaiyo.TEXT_1);
			message.setIcon(MessageNaiyo.ICON_ERROR);
			message.setTimer(MessageNaiyo.TIMER_3M);
			message.setButtons(MessageNaiyo.BUTTONS_HIDDEN);
		} else {
			event.setVersion(event.getVersion() + 1);
			eventMapper.updateEvent(event);
			eventMapper.deleteResource(event.getId());
			eventMapper.saveResource(event.getResources());
			message.setTitle(MessageNaiyo.TITLE_UPDATE_1);
			message.setText(MessageNaiyo.TEXT_1);
			message.setIcon(MessageNaiyo.ICON_SUCCESS);
			message.setTimer(MessageNaiyo.TIMER_3M);
			message.setButtons(MessageNaiyo.BUTTONS_HIDDEN);
		}
		return message;
	}

	/**
	 * カレンダーのイベントを追加する
	 * @param event イベント
	 * @return Message メッセージ
	 */
	public Message saveEvent(Event event) throws JsonParseException, JsonMappingException {
		eventMapper.saveEvent(event);
		for(Resource resource : event.getResources()) {
			resource.setEventId(event.getId());
		}
		eventMapper.saveResource(event.getResources());
		Message message = new Message();
		message.setTitle(MessageNaiyo.TITLE_INSERT_1);
		message.setText(MessageNaiyo.TEXT_1);
		message.setIcon(MessageNaiyo.ICON_SUCCESS);
		message.setTimer(MessageNaiyo.TIMER_3M);
		message.setButtons(MessageNaiyo.BUTTONS_HIDDEN);
		return message;
	}

	/**
	 * カレンダーのイベントを削除する
	 * @param id イベントID
	 * @return Message メッセージ
	 */
	public Message deleteEvent(String id) throws JsonParseException, JsonMappingException {
		eventMapper.deleteEvent(Integer.valueOf(id));
		eventMapper.deleteResource(Integer.valueOf(id));
		Message message = new Message();
		message.setTitle(MessageNaiyo.TITLE_DELETE_1);
		message.setText(MessageNaiyo.TEXT_1);
		message.setIcon(MessageNaiyo.ICON_SUCCESS);
		message.setTimer(MessageNaiyo.TIMER_3M);
		message.setButtons(MessageNaiyo.BUTTONS_HIDDEN);
		return message;
	}

}