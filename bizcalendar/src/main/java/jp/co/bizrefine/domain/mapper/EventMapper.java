package jp.co.bizrefine.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.model.Resource;

@Mapper
public interface EventMapper {

	List<Event> findEvents(Event event);

	void saveEvent(Event event);

	void updateEvent(Event event);

	void deleteEvent(int id);

	List<Resource> findResources(Event event);

}
