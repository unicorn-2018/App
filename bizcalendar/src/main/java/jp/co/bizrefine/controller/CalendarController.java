package jp.co.bizrefine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.model.Resource;
import jp.co.bizrefine.domain.service.EventService;

@RestController
@RequestMapping(value = "/api")
public class CalendarController {

	@Autowired
	EventService eventService;

	/**
	 * カレンダーのイベントを取得する
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Event> getEvents(@RequestBody Event event) throws Exception {
		return eventService.findEvents(event);
	}

	/**
	 * カレンダーのリソースを取得する
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/resource", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Resource> getResources(@RequestBody Event event) throws Exception {
		return eventService.findResources(event);
	}

	/**
	 * カレンダーのイベントを更新する
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Event updateEvent(@RequestBody Event event) throws Exception {
		return eventService.updateEvent(event);
	}

	/**
	 * カレンダーのイベントを追加する
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Event saveEvent(@RequestBody Event event) throws Exception {
		return eventService.saveEvent(event);
	}

	/**
	 * カレンダーのイベントを削除する
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteEvent(@RequestBody String id) throws Exception {
		eventService.deleteEvent(id);
	}
}