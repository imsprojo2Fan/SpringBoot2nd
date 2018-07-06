package com.hhen.controller;

import com.hhen.config.JsonResult;
import com.hhen.model.User;
import com.hhen.service.UserService;
import com.hhen.util.Md5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:38 2018/7/2
 * @Modified By:
 */
@RestController//直接返回json格式数据
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	Map<String,Object> qMap = new HashMap<>();

	List<Map<String,Object>> rList = new ArrayList<>();

	Map<String,Object> backMap = new HashMap<>();

	int GlobalDraw = 0;

	@Value("com.hhen.pageNow")
	public static int PAGE_NOW;
	@Value("com.hhen.pageSize")
	public static int PAGE_SIZE;

	JsonResult r = new JsonResult();

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add (@ModelAttribute User user){
		r.setCode(-1);
		r.setMsg("fail");
		r.setData(null);
		try {
			//查询账号是否存在
			User u = userService.getUserByAccount(user.getAccount());
			if(u!=null){
				r.setMsg("账号已存在!");
				return r;
			}
			//加密密码
			String pass = Md5Util.getMD5WithSalt(user.getPassword());
			user.setPassword(pass);
			int uid = userService.add(user);
			if (uid > 0) {
				r.setCode(1);
				r.setMsg("操作成功!");
				r.setData(uid);
			}
		} catch (Exception e) {
			r.setMsg("数据库操作异常!");
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete (String id){
		r.setCode(-1);
		r.setMsg("fail");
		r.setData(null);
		int Id = 0;
		if(StringUtils.isEmpty(id)){
			r.setMsg("id can`t be null!");
			return r;
		}else{
			try {
				Id = Integer.parseInt(id);
			}catch (NumberFormatException e){
				r.setMsg("NumberFormatException!");
				e.printStackTrace();
			}
		}
		try {
			int ret = userService.delete(Id);
			if (ret > 0) {
				r.setCode(1);
				r.setMsg("success");
				r.setData(null);
			}
		} catch (Exception e) {
			r.setMsg("数据库操作异常!");
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 根据id修改用户信息
	 * @param user
	 * @return
	 */
	@ApiOperation(value="更新信息", notes="根据表单数据的id来指定更新用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
	})
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object update (User user){
		r.setCode(-1);
		r.setMsg("fail");
		r.setData(null);

		try {
			if(user.getPassword().equals("123456")){//不更新密码
				user.setPassword(null);
			}else{
				user.setPassword(Md5Util.getMD5WithSalt(user.getPassword()));
			}
			int ret = userService.update(user);
			if (ret > 0) {
				r.setCode(1);
				r.setMsg("操作成功!");
				r.setData(ret);
			}
		} catch (Exception e) {
			r.setMsg("数据库操作异常!");
			e.printStackTrace();
		}
		return r;
	}


	/**
	 * 查询用户列表
	 * @return
	 */
	@ApiOperation(value="用户分页查询", notes="获取用户列表")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object getUserList (HttpServletRequest request){
		GlobalDraw++;

		String start = request.getParameter("start");
		String length = request.getParameter("length");
		String sortType = request.getParameter("order[0][dir]");
		String searchKey = request.getParameter("search[value]");
		if(!StringUtils.isEmpty(start)){
			PAGE_NOW = Integer.parseInt(start);
		}
		if(!StringUtils.isEmpty(length)){
			PAGE_SIZE = Integer.parseInt(length);
		}

		qMap.put("pageNow",PAGE_NOW);
		qMap.put("pageSize",PAGE_SIZE);
		qMap.put("sortCol","id");
		qMap.put("sortType",sortType);
		qMap.put("searchKey",searchKey);

		int count = userService.listAllCount(qMap);
		rList = userService.listByPage(qMap);



		backMap.put("draw",GlobalDraw);
		backMap.put("recordsTotal",count);
		backMap.put("recordsFiltered",count);
		backMap.put("data",rList);

		return backMap;
	}

}
