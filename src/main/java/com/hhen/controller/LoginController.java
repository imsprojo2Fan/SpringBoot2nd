package com.hhen.controller;

import com.hhen.config.JsonResult;
import com.hhen.model.User;
import com.hhen.service.UserService;
import com.hhen.util.Md5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 9:36 2018/7/2
 * @Modified By:
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	JsonResult jsonResult = new JsonResult();

	@Value("com.hhen.sessionMaxTime")
	private static int sessionMaxTime;


	@Autowired
	private UserService userService;

	@ApiIgnore//使用该注解忽略这个API
	@RequestMapping(value = "/")
	public String index(){
		return "/html/login.html";
	}
	@ApiIgnore//使用该注解忽略这个API
	@RequestMapping(value = "/login")
	public String loginIndex(){
		return "/html/login.html";
	}

	/**
	 * 用户登录验证
	 * @param account password
	 * @return
	 */
	@ApiOperation(value="用户登录验证", notes="根据表单账号及密码验证用户")
	@ApiImplicitParams({
			@ApiImplicitParam(
					paramType = "form", dataType = "String", name = "account", value = "用户账号", required = true),
			@ApiImplicitParam(
					paramType = "form", dataType = "String", name = "password", value = "用户密码", required = true)
	})
	@PostMapping(value = "/validate")
	@ResponseBody
	public Object  login(String account, String password, HttpSession session) {

		jsonResult.setCode(-1);
		jsonResult.setMsg("failure");
		jsonResult.setData(null);

		if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)){
			jsonResult.setMsg("account or password can`t be null!");
			return jsonResult;
		}
		User user = userService.getUserByAccount(account);

		if(user!=null){
			String pass = user.getPassword();
			String md5Pass = Md5Util.getMD5WithSalt(password);
			if(pass.equals(md5Pass)){
				session.setMaxInactiveInterval(sessionMaxTime);
				session.setAttribute("user",user);
				jsonResult.setCode(1);
				jsonResult.setMsg("success");
				jsonResult.setData(null);
			}
		}
		return jsonResult;
	}

	/**
	 * 用户退出登录
	 * @param
	 * @return
	 */
	//@ApiIgnore//使用该注解忽略这个API
	@ApiOperation(value="用户退出登录", notes="无")
	@ApiImplicitParams({
			@ApiImplicitParam(
					paramType = "query", dataType = "null", name = "null", value = "null", required = false)
	})
	@RequestMapping(value = "/logout")
	public Object loginOut( HttpSession session) {
		session.removeAttribute("user");
		return "/html/login.html";
	}

}

