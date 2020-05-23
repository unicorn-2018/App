package jp.co.bizrefine.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.bizrefine.domain.model.Group;
import jp.co.bizrefine.domain.model.User;

@Mapper
public interface UserMapper {

//	User findOne(int id);

	User findAuth(User user);

	List<User> findMembers();

	List<Group> findGroups();

}
