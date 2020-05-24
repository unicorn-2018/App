package jp.co.bizrefine.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.bizrefine.domain.model.Code;
import jp.co.bizrefine.domain.model.Event;
import jp.co.bizrefine.domain.model.Resource;

@Mapper
public interface EventMapper {

	/**
	 * イベントリストを取得する
	 * @param event イベント
	 * @return List<Event> イベントリスト
	 */
	List<Event> findEvents(Event event);

	/**
	 * イベントを取得する
	 * @param id イベントID
	 * @return Event イベント
	 */
	Event findEvent(int id);

	/**
	 * イベントを追加する
	 * @param event イベント
	 */
	void saveEvent(Event event);

	/**
	 * イベントを更新する
	 * @param event イベント
	 */
	void updateEvent(Event event);

	/**
	 * イベントを削除する
	 * @param id イベントID
	 */
	void deleteEvent(int id);

	/**
	 * リソースを取得する
	 * @return List<Resource> リソースリスト
	 */
	List<Resource> findResources();

	/**
	 * 子リソースを取得する
	 * @return List<Resource> 子リソースリスト
	 */
	List<Resource> findChildResources();

	/**
	 * コードリストを取得する
	 * @return List<Code> コードリスト
	 */
	List<Code> findCodes(Code code);

	/**
	 * リソースを追加する
	 * @param resource リソース
	 */
	void saveResource(List<Resource> resources);

	/**
	 * リソースを削除する
	 * @param id イベントID
	 */
	void deleteResource(int id);
}
