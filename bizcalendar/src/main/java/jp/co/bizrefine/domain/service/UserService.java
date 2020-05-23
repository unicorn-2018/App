package jp.co.bizrefine.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bizrefine.domain.mapper.UserMapper;
import jp.co.bizrefine.domain.model.Group;
import jp.co.bizrefine.domain.model.User;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * ログインユーザーの情報を取得
	 * @param user ユーザー
	 * @return User ユーザー
	 */
	public User findAuth(User user) {
		return userMapper.findAuth(user);
	}

   /**
	*メンバーを取得
	* @return List<User> ユーザーリスト
	*/
	public List<User> findMembers() {
		return userMapper.findMembers();
	}

   /**
	*グループを取得
	* @return List<Group> グループリスト
	*/
	public List<Group> findGroups() {
		return userMapper.findGroups();
	}
}