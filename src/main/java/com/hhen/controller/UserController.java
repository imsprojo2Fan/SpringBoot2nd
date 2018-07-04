package com.hhen.controller;

import com.hhen.config.JsonResult;
import com.hhen.model.User;
import com.hhen.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:38 2018/7/2
 * @Modified By:
 */
@Controller//@ResController直接返回json格式数据
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	JsonResult r = new JsonResult();


	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id){
		JsonResult r = new JsonResult();
		try {
			User user = userService.getUserById(id);
			r.setData(user);
			r.setMsg("ok");
		} catch (Exception e) {
			r.setMsg(e.getClass().getName() + ":" + e.getMessage());
			r.setMsg("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 查询用户列表
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="获取用户列表")
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList (){
		JsonResult r = new JsonResult();
		try {
			List<User> users = userService.getUserList();
			r.setData(users);
			r.setMsg("ok");
		} catch (Exception e) {
			r.setData(e.getClass().getName() + ":" + e.getMessage());
			r.setMsg("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "menu", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add (@RequestBody User user){
		JsonResult r = new JsonResult();
		try {
			int orderId = userService.add(user);
			if (orderId < 0) {
				r.setData(orderId);
				r.setMsg("fail");
			} else {
				r.setData(orderId);
				r.setMsg("ok");
			}
		} catch (Exception e) {
			r.setData(e.getClass().getName() + ":" + e.getMessage());
			r.setMsg("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") Integer id){
		JsonResult r = new JsonResult();
		try {
			int ret = userService.delete(id);
			if (ret < 0) {
				r.setData(ret);
				r.setMsg("fail");
			} else {
				r.setData(ret);
				r.setMsg("ok");
			}
		} catch (Exception e) {
			r.setData(e.getClass().getName() + ":" + e.getMessage());
			r.setMsg("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id修改用户信息
	 * @param user
	 * @return
	 */
	@ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
			@ApiImplicitParam(name = "menu", value = "用户实体user", required = true, dataType = "User")
	})
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JsonResult> update (@PathVariable("id") Integer id, @RequestBody User user){
		JsonResult r = new JsonResult();
		try {
			int ret = userService.update(id, user);
			if (ret < 0) {
				r.setData(ret);
				r.setMsg("fail");
			} else {
				r.setData(ret);
				r.setMsg("ok");
			}
		} catch (Exception e) {
			r.setData(e.getClass().getName() + ":" + e.getMessage());
			r.setMsg("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

}
