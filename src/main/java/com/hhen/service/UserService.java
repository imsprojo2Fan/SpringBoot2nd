package com.hhen.service;

import com.hhen.model.User;

import java.util.List;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:29 2018/7/2
 * @Modified By:
 */
public interface UserService {

	User getUserById(Integer id);

	public List<User> getUserList();

	public int add(User user);

	public int update(Integer id, User user);

	public int delete(Integer id);
}
