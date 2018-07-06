package com.hhen.config.filter;

import com.hhen.model.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: imsprojo2Fan
 * @Description:
 * @Date: Created in 17:46 2018/7/2
 * @Modified By:
 */
public class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// 顾名思义，在销毁时使用
		System.out.println("AuthFilter destroy");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
						 FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		if(needLogin(request)) {
			// 需要登录则跳转到登录Controller
			response.sendRedirect("/login");
			return;
		}
		if(!hasRight(request)){
			response.sendRedirect("/err/403");
			return;
		}
		chain.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 初始化操作
		System.out.println("AuthFilter init");
	}

	/**
	 * 判断是否需要登录
	 * @param request
	 * @return
	 */
	private boolean needLogin(HttpServletRequest request) {

		//进行是否需要登录的判断操作
		User user = (User) request.getSession().getAttribute("user");
		if (user==null){
			return true;
		}
		return false;
	}

	/**
	 * 判断是否有权限操作
	 * @param request
	 * @return
	 */
	private boolean hasRight(HttpServletRequest request) {

		//进行是否需要登录的判断操作
		User user = (User) request.getSession().getAttribute("user");
		if (user.getType()==1){
			return false;
		}
		return true;
	}

}
