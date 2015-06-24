package com.system.dot.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 
 * 测试数据库管理对象
 * @author cbchen
 * @time 2015年6月17日 上午11:21:41 
 */
public class TestDao {

	public static void main(String[] args) {
		
		// 添加数据对象 2015年6月17日 上午11:28:46
//		User user = new User();
//		user.setPassword("123456b");
//		user.setUsername("ccb2");
//		
//		DBManager.getInstance().save(user);
		
		// 批量添加数据对象 2015年6月17日 下午1:53:16
//		List<Object> lstObj = new ArrayList<Object>();
//		User user = new User();
//		user.setPassword("123456");
//		user.setUsername("ccb1");
//		lstObj.add(user);
//		
//		user = new User();
//		user.setPassword("123456asdf");
//		user.setUsername("ccb2asdf");
//		lstObj.add(user);
//		
//		boolean isResult = DBManager.getInstance().saves(lstObj);
//		System.out.println("=========    " + isResult);
		
		// 删除数据对象 2015年6月17日 上午11:45:01
//		boolean isResult = DBManager.getInstance().del(User.class, 3);
//		System.out.println("=========    " + isResult);
		
		// 批量删除数据对象 2015年6月17日 下午1:49:35
//		List<Integer> lstId = new ArrayList<Integer>();
//		lstId.add(9);
//		lstId.add(10);
//		DBManager.getInstance().dels(User.class, lstId);
		
		// 修改数据对象 2015年6月17日 下午12:56:58
//		User user = new User();
//		user.setPassword("123456a");
//		DBManager.getInstance().update(User.class, 4, user);
		
		// 批量修改数据 2015年6月17日 下午1:42:20
//		Map<Integer, Object> mapData = new HashMap<Integer, Object>();
//		User user = new User();
//		user.setPassword("1234567");
//		
//		User user1 = new User();
//		user1.setPassword("1234568");
//		
//		mapData.put(4, user);
//		mapData.put(5, user1);
//		DBManager.getInstance().updates(User.class, mapData);
		
		
		// 根据id，查询数据
//		List lstCondi = new ArrayList();
//		lstCondi.add(5);
//		lstCondi.add(7);
//		
//		List<?> lstUser = DBManager.getInstance()
//				.getDataByCondi("from User where id in (?, ?)", lstCondi);
		
		// 根据username
//		List lstCondi = new ArrayList();
//		lstCondi.add("%wpeng%");
//		
//		List<?> lstUser = DBManager.getInstance()
//				.getDataByCondi("from User where username like ?", lstCondi);
//		
//		for(Object obj:lstUser) {
//			User user = (User) obj;
//			System.out.println("=========  name " + user.getUsername());
//			System.out.println("=========  password " + user.getPassword());
//		}
	}
}
