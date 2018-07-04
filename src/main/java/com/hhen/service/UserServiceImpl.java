package com.hhen.service;

import com.hhen.mapper.UserMapper;
import com.hhen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public int add(User user) {
		return userMapper.add(user);
	}

	@Override
	public int update(Integer id, User user) {
		return userMapper.update(id, user);
	}

	@Override
	public int delete(Integer id) {
		return userMapper.delete(id);
	}
}

