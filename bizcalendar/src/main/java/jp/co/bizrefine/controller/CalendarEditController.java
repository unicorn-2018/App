package jp.co.bizrefine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.model.Message;
import jp.co.bizrefine.domain.model.Resource;
import jp.co.bizrefine.domain.service.EventService;

@RestController
public class CalendarEditController {

	@Autowired
	EventService eventService;

	/**
	 * カレンダーのイベントを取得する
	 * @param event イベント
	 * @return List<Event> イベントリスト
	 */
	@ResponseBody
	@PostMapping(value = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Event> findEvents(@RequestBody Event event) throws Exception {
		return eventService.findEvents(event);
	}

	/**
	 * カレンダーのリソースを取得する
	 * @param event イベント
	 * @return List<Resource> リソースリスト
	 */
	@ResponseBody
	@PostMapping(value = "/resource", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Resource> findResources() throws Exception {
		return eventService.findResources();
	}

	/**
	 * カレンダーのイベントを更新する
	 * @param event イベント
	 * @return Message メッセージ
	 */
	@ResponseBody
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message updateEvent(@RequestBody Event event) throws Exception {
		return eventService.updateEvent(event);
	}

	/**
	 * カレンダーのイベントを追加する
	 * @param event イベント
	 * @return Message メッセージ
	 */
	@ResponseBody
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message saveEvent(@RequestBody Event event) throws Exception {
		return eventService.saveEvent(event);
	}

	/**
	 * カレンダーのイベントを削除する
	 * @param id イベントID
	 * @return Message メッセージ
	 */
	@ResponseBody
	@PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Message deleteEvent(@RequestBody String id) throws Exception {
		return eventService.deleteEvent(id);
	}
}