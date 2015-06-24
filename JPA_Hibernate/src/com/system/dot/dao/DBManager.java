package com.system.dot.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

/** 
 * 数据库管理
 * @author cbchen
 * @time 2015年6月17日 上午10:18:28 
 */
public class DBManager {

	private static DBManager _dbManager = null;
	private static final String PERSISTENCE = "DotSys";
	
	/**
	 * 单例模式: 获取数据库管理对象
	 * @return _dbManager
	 * cbchen
	 * 2015年6月17日 上午10:22:40
	 */
	public static DBManager getInstance() {
		if (_dbManager == null) {
			_dbManager = new DBManager();
		}
		
		return _dbManager;
	}
	
	/**
	 * 保存数据对象
	 * @param object
	 * @author cbchen
	 * @time 2015年6月17日 上午11:30:18
	 */
	public boolean save(Object object) {
		
		boolean isSuccess = false;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager();  
        
        // 获取实体事务对象
        EntityTransaction tran = manager.getTransaction();
        
        // 开启事务
        tran.begin();
        
        try {
		    manager.persist(object); // 保存数据对象
		    
		    // 提交事务
		    tran.commit();
		    isSuccess = true;
        } catch (Exception e) {
        	tran.rollback();
        	isSuccess = false;
        } finally {
        	manager.close();
            factory.close();
        }
        
        return isSuccess;
	}
	
	/**
	 * 批量添加
	 * @param lstObj 数据列表
	 * @author cbchen
	 * @time 2015年6月17日 下午1:52:44
	 */
	public boolean saves(List<Object> lstObj) {
		
		boolean isSuccess = false;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager();  
        
        // 获取实体事务对象
        EntityTransaction tran = manager.getTransaction();
        
        // 开启事务
        tran.begin();
        try {
        	Session session = (Session) manager.getDelegate();
	        for(Object object:lstObj) {
	        	session.save(object);
	        	session.flush();
		        session.clear();
	        }
	        
	        // 提交事务
	        tran.commit();
	        isSuccess = true;
        } catch (Exception e) {
        	tran.rollback();
        	isSuccess = false;
        } finally {
        	manager.close();
            factory.close();
        }
        
        return isSuccess;
	}
	
	/**
	 * 删除数据对象
	 * @param objType 需要删除的对象类型
	 * @param id 需要删除的对象id
	 * @return 是否删除成功
	 * @author cbchen
	 * @time 2015年6月17日 上午11:49:14
	 */
	public <T> boolean del(Class<T> objType, int id) {
		boolean isSuccess = false;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager(); 
        
        // 开启事务
        manager.getTransaction().begin();
        
        try {
        	
	        // 根据Id查找到相应的对象，然后删除
	        Object object = manager.find(objType, id);
	        if (object != null) {
	        	manager.remove(object); // 删除
	        	manager.getTransaction().commit();
	        	isSuccess = true;
	        }
        } catch (Exception e) {
        	manager.getTransaction().rollback();
        } finally {
        	manager.close();
            factory.close();
        }
        
        return isSuccess;
	}
	
	/**
	 * 批量删除
	 * @param objType 数据类型
	 * @param lstId 对象id列表
	 * @return 是否删除成功
	 * @author cbchen
	 * @time 2015年6月17日 下午1:47:10
	 */
	public <T> boolean dels(Class<T> objType, List<Integer> lstId) {
		boolean isSuccess = false;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager(); 
        
        // 开启事务
        manager.getTransaction().begin();
        
        try {
        	
	        // 遍历id列表
	        for(int id:lstId) {
	        	
	        	// 根据Id查找到相应的对象，然后删除
	            Object object = manager.find(objType, id);
	            if (object != null) {
	            	manager.remove(object); // 删除
	            	isSuccess = true;
	            } else {
	            	isSuccess = false;
	            	break;
	            }
	        }
	        
	        // 删除成功，提交
	        if (isSuccess)
	        	manager.getTransaction().commit();
	        
        } catch (Exception e) {
        	manager.getTransaction().rollback();
        } finally {
        	manager.close();
            factory.close();
        }
        
		return isSuccess;
	}
	
	/**
	 * 修改单条数据
	 * @param objType 需要修改的数据类型
	 * @param id 需要修改的对象id
	 * @param objData 数据源对象
	 * @return 是否修改成功
	 * @author cbchen
	 * @time 2015年6月17日 下午1:01:08
	 */
	public <T> boolean update(Class<T> objType, int id, Object objData) {
		boolean isSuccess = false;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager(); 
        
        // 开启事务
        manager.getTransaction().begin();
        
        try {
	        // 根据Id查找到相应的对象，然后进行修改
	        Object object = manager.find(objType, id);
	        if (object != null) {
	        	_setupData(objType, object, objData); // 设置数据
	        	manager.getTransaction().commit();
	        	isSuccess = true;
	        }
        } catch (Exception e) {
        	manager.getTransaction().rollback();
        } finally {
        	manager.close();
            factory.close();
        }
        
		return isSuccess;
	}
	
	/**
	 * 批量修改
	 * @param objType 数据类型
	 * @param mapData 字典数据对象  key:id, value:数据源
	 * @return 是否修改成功
	 * @author cbchen
	 * @time 2015年6月17日 下午1:35:47
	 */
	public <T> boolean updates(Class<T> objType, Map<Integer, Object> mapData) {
		boolean isSuccess = false;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager(); 
        
        // 开启事务
        manager.getTransaction().begin();
        
        try{
        	
	        // 遍历字典数据对象
	        for (Integer id:mapData.keySet()) {
	        	
	        	// 根据Id查找到相应的对象，然后进行修改
	        	Object object = manager.find(objType, id);
	        	if (object == null) {
	        		isSuccess = false;
	        		break;
	        	}
	        	
	        	_setupData(objType, object, mapData.get(id)); // 设置数据
	        	isSuccess = true;
	        }
	        
	        // 修改成功，提交
	        if (isSuccess)
	        	manager.getTransaction().commit();
	        
        } catch (Exception e) {
        	manager.getTransaction().rollback();
        } finally {
        	manager.close();
            factory.close();
        }
        
		return isSuccess;
	}
	
	/**
	 * 设置数据
	 * @param objType 数据类型
	 * @param objDec 数据目标
	 * @param objSrc 数据源
	 * @author cbchen
	 * @time 2015年6月17日 下午1:09:51
	 */
	private <T> void _setupData(Class<T> objType, 
			Object objDec, Object objSrc) {
		
		if (objType.equals(User.class)) {
    		User decUser = (User) objDec;
    		User srcUser = (User) objSrc;
    		if (srcUser.getPassword() != null)
    			decUser.setPassword(srcUser.getPassword());
    		
    		if (srcUser.getUsername() != null)
    			decUser.setUsername(srcUser.getUsername());
    	}
	}
	
	/**
	 * 查询数据
	 * @param sql 查询语句
	 * @param lstCondi 查询条件
	 * @return 返回查询对象列表
	 * @author cbchen
	 * @time 2015年6月17日 下午3:18:44
	 */
	public List<?> getDataByCondi(String sql, List<?> lstCondi) {
		
		List<?> lstObj = null;
		
		// 获取实体管理工厂
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// 获取实体管理类
        EntityManager manager =  factory.createEntityManager(); 
        
        // 开启事务
        manager.getTransaction().begin();
        
        // sql = "from User where id in(?, ?, ?)"
        try {
	        Query query = manager.createQuery(sql);
	        if (lstCondi != null) { // 添加参数
	        	for(int i = 1;i <= lstCondi.size();i++) {
	        		query.setParameter(i, lstCondi.get(i - 1));
	        	}
	        	
	        	//调用Query的获得结果集的方法
	        	lstObj = query.getResultList();
	        }
        } catch (Exception e) {
        	manager.getTransaction().rollback();
        } finally {
        	manager.close();
            factory.close();
        }
        
		return lstObj;
	}
	
}
