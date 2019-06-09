package jp.co.bizrefine.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.bizrefine.domain.mapper.UserMapper;
import jp.co.bizrefine.domain.model.User;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * フォームに入力されたユーザ名、パスワードによる認証処理。
	 * 認証の成否はUserクラスの項目UserVaildFで判断する。認証成功、失敗をコンソールへ出力する
	 *
	 */
	public User auth(User user) {

		User userResult = userMapper.auth(user);

		if (userResult != null)
			System.out.println("認証成功");
		else
			System.out.println("認証失敗");

		return userResult;
	}

}