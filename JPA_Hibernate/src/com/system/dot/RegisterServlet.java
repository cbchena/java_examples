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
 * ע��
 * @author cbchen
 * 2015��6��15�� ����4:36:08
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	/**
	 * ��������:Get 2015��6��17�� ����3:45:52
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * ��������:Post 2015��6��17�� ����3:46:12
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("�������ݿ�������<br/>");
		
		// ����������ݶ��� 2015��6��17�� ����1:53:16
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
		out.println("��ӽ����" + isResult);
	}

}
