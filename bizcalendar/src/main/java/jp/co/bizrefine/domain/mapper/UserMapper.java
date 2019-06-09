package jp.co.bizrefine.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.co.bizrefine.domain.model.User;

@Mapper
public interface UserMapper {

	User findOne(int id);

	User auth(User user);
}
