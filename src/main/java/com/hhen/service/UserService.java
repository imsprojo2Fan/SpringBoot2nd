package com.hhen.service;

import com.hhen.model.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:29 2018/7/2
 * @Modified By:
 */
public interface UserService {

	int add(User user);

	int update(User user);

	int delete(Integer id);


	User getUserByAccount(String account);

	int listAllCount(Map<String,Object> qMap);

	List<Map<String,Object>> listByPage(Map<String,Object> qMap);
}
