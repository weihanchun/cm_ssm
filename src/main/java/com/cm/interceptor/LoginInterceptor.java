package com.cm.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cm.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 在业务处理器处理请求之前被调用
	 * 如果返回false
	 *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true
	 *    执行下一个拦截器,直到所有的拦截器都执行完毕
	 *    再执行被拦截的Controller
	 *    然后进入拦截器链,
	 *    从最后一个拦截器往回执行所有的postHandle()
	 *    接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		String contextPath=session.getServletContext().getContextPath();
		//准备字符串数组，存放那些不需要登录也能访问的路径
		String[] noNeedAuthPage = new String[]{
				"home",
				"checkLogin",
				"register",
				"loginAjax",
				"login",
				"product",
				"category",
				"Find",
				"FindByPhone",
				"FindByMiBao",
				"FindByPwd",
		"search"};

		String uri = request.getRequestURI();//获取uri
		uri = StringUtils.remove(uri, contextPath);//去掉前缀
		//        System.out.println(uri);
		//if(uri.startsWith("/fore")){
		    //取出fore后面的字符串
			String method = StringUtils.substringAfterLast(uri,"/fore" );
			//判断是否在数组中，如果不在就需要进行登录验证
			if(!Arrays.asList(noNeedAuthPage).contains(method)){
				User user =(User) session.getAttribute("user");
				if(null==user){//如果对象不在，就跳转到登录页面
					response.sendRedirect("loginPage");
					return false;
				}
			}
		//}

		return true;

	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 * 可在modelAndView中加入数据，比如当前时间
	 */

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,   
			ModelAndView modelAndView) throws Exception {

	}

	/** 
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等  
	 *  
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	 */

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) 
			throws Exception { 

	}  
}
