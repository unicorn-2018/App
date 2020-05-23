package jp.co.bizrefine.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.service.EventService;
@RestController
public class CalendarDisplayController {

	@Autowired
	EventService eventService;

	/**
	 * タスクリスト画面を表示
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/tasklist", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView toTaskList(@RequestBody String json) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Event[] events = mapper.readValue(json, Event[].class);

		List<Event> tasks = Arrays.asList(events);
		if (tasks.size() > 0) {
			Event event = tasks.get(0);
			if (1 == event.getSelectKubun()) {
				tasks = eventService.findEvents(event);
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarTaskList");
		mv.addObject("tasks", tasks);

		return mv;
	}

	/**
	 * スケジュール編集画面を表示
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView toEdit(@RequestBody String json) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Event[] event = mapper.readValue(json, Event[].class);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarEdit");
		mv.addObject("event", event[0]);

		return mv;
	}

	/**
	 * イベント編集画面を表示
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/eventedit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView toEventEdit(@RequestBody String json) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Event[] event = mapper.readValue(json, Event[].class);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarEventEdit");
		mv.addObject("event", event[0]);

		return mv;
	}

	/**
	 * タスク編集画面を表示
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/taskedit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView toTaskEdit(@RequestBody String json) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Event[] event = mapper.readValue(json, Event[].class);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarTaskEdit");

		mv.addObject("event", event[0]);

		return mv;
	}

	/**
	 * スケジュールリスト画面を表示
	 *
	 */
/*	@ResponseBody
	@PostMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView toScheduleList(@RequestBody String json) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Event[] events = mapper.readValue(json, Event[].class);
		List<Event> eventList = new ArrayList<Event>();
		List<Event> taskList = new ArrayList<Event>();

		for(Event event : Arrays.asList(events)) {
			if (event.isTaskFlag()) {
				taskList.add(event);
			} else {
				eventList.add(event);
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarList");

		mv.addObject("events", eventList);
		mv.addObject("tasks", taskList);

		return mv;
	}*/

	/**
	 * イベントリスト画面を表示
	 *
	 */
/*	@ResponseBody
	@PostMapping(value = "/eventlist", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView toEventList(@RequestBody String json) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		Event[] events = mapper.readValue(json, Event[].class);
		List<Event> eventList = Arrays.asList(events);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarEventList");

		mv.addObject("events", eventList);

		return mv;
	}*/
}