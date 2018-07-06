package com.hhen.service;

import com.hhen.dao.UserMapper;
import com.hhen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:30 2018/7/2
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int add(User user) {
		return userMapper.add(user);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public int delete(Integer id) {
		return userMapper.delete(id);
	}


	@Override
	public User getUserByAccount(String account) {
		return userMapper.getUserByAccount(account);
	}

	@Override
	public int listAllCount(Map<String,Object> qMap) {
		return userMapper.listAllCount(qMap);
	}

	@Override
	public List<Map<String,Object>> listByPage(Map<String,Object> qMap) {
		return userMapper.listByPage(qMap);
	}
}

