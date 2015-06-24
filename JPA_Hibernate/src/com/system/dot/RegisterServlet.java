package com.system.dot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.dot.dao.DBManager;
import com.system.dot.dao.User;

/**
 * 注册
 * @author cbchen
 * 2015年6月15日 下午4:36:08
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	/**
	 * 请求类型:Get 2015年6月17日 下午3:45:52
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 请求类型:Post 2015年6月17日 下午3:46:12
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("增加数据咯！！！<br/>");
		
		// 批量添加数据对象 2015年6月17日 下午1:53:16
		List<Object> lstObj = new ArrayList<Object>();
		User user = new User();
		user.setPassword("123456");
		user.setUsername("ccb1");
		lstObj.add(user);
		
		user = new User();
		user.setPassword("123456");
		user.setUsername("ccb2");
		lstObj.add(user);
		
		boolean isResult = DBManager.getInstance().saves(lstObj);
		out.println("添加结果：" + isResult);
	}

}
