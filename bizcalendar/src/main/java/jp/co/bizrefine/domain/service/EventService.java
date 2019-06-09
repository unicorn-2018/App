package jp.co.bizrefine.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ajd4jp.AJDException;
import jp.co.bizrefine.domain.mapper.EventMapper;
import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.model.Resource;
import jp.co.bizrefine.util.MakeCalendarUtil;

@Service
public class EventService {

	@Autowired
	EventMapper eventMapper;

	/**
	 * カレンダーのイベントを取得する
	 *
	 */
	public List<Event> findEvents(Event event) throws AJDException, JsonParseException, JsonMappingException {
		List<Event> events = eventMapper.findEvents(event);
		String date = event.getStart().replace("-", "");
		List<Event> holiday = MakeCalendarUtil.getHoliDay(String.valueOf(date));
		events.addAll(holiday);
		List<Resource> resources = findResources(event);
		for (Event eventResult : events) {
			for (Resource resource : resources) {
				if (eventResult.getClassName() == resource.getClassName()
						&& eventResult.getStatusName() == resource.getStatusName()) {
					eventResult.setResourceId(resource.getId());
					eventResult.setStatusIcon(resource.getStatusIcon());
					eventResult.setEventType(resource.getBuilding());
					eventResult.setEventStatus(resource.getTitle());
				}
			}
		}
		return events;
	}

	/**
	 * カレンダーのリソースを取得する
	 *
	 */
	public List<Resource> findResources(Event event) throws JsonParseException, JsonMappingException {
		List<Resource> resources = eventMapper.findResources(event);
		int id = 1;
		for (Resource resource : resources) {
			resource.setId(String.valueOf(id));
			id++;
		}
		return resources;
	}

	/**
	 * カレンダーのイベントを更新する
	 *
	 */
	public Event updateEvent(Event event) throws JsonParseException, JsonMappingException {
		eventMapper.updateEvent(event);
		return event;
	}

	/**
	 * カレンダーのイベントを追加する
	 *
	 */
	public Event saveEvent(Event event) throws JsonParseException, JsonMappingException {
		eventMapper.saveEvent(event);
		return event;
	}

	/**
	 * カレンダーのイベントを削除する
	 *
	 */
	public void deleteEvent(String id) throws JsonParseException, JsonMappingException {
		eventMapper.deleteEvent(Integer.valueOf(id));

	}
}