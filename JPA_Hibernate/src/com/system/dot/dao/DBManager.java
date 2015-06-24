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
 * ���ݿ����
 * @author cbchen
 * @time 2015��6��17�� ����10:18:28 
 */
public class DBManager {

	private static DBManager _dbManager = null;
	private static final String PERSISTENCE = "DotSys";
	
	/**
	 * ����ģʽ: ��ȡ���ݿ�������
	 * @return _dbManager
	 * cbchen
	 * 2015��6��17�� ����10:22:40
	 */
	public static DBManager getInstance() {
		if (_dbManager == null) {
			_dbManager = new DBManager();
		}
		
		return _dbManager;
	}
	
	/**
	 * �������ݶ���
	 * @param object
	 * @author cbchen
	 * @time 2015��6��17�� ����11:30:18
	 */
	public boolean save(Object object) {
		
		boolean isSuccess = false;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager();  
        
        // ��ȡʵ���������
        EntityTransaction tran = manager.getTransaction();
        
        // ��������
        tran.begin();
        
        try {
		    manager.persist(object); // �������ݶ���
		    
		    // �ύ����
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
	 * �������
	 * @param lstObj �����б�
	 * @author cbchen
	 * @time 2015��6��17�� ����1:52:44
	 */
	public boolean saves(List<Object> lstObj) {
		
		boolean isSuccess = false;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager();  
        
        // ��ȡʵ���������
        EntityTransaction tran = manager.getTransaction();
        
        // ��������
        tran.begin();
        try {
        	Session session = (Session) manager.getDelegate();
	        for(Object object:lstObj) {
	        	session.save(object);
	        	session.flush();
		        session.clear();
	        }
	        
	        // �ύ����
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
	 * ɾ�����ݶ���
	 * @param objType ��Ҫɾ���Ķ�������
	 * @param id ��Ҫɾ���Ķ���id
	 * @return �Ƿ�ɾ���ɹ�
	 * @author cbchen
	 * @time 2015��6��17�� ����11:49:14
	 */
	public <T> boolean del(Class<T> objType, int id) {
		boolean isSuccess = false;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager(); 
        
        // ��������
        manager.getTransaction().begin();
        
        try {
        	
	        // ����Id���ҵ���Ӧ�Ķ���Ȼ��ɾ��
	        Object object = manager.find(objType, id);
	        if (object != null) {
	        	manager.remove(object); // ɾ��
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
	 * ����ɾ��
	 * @param objType ��������
	 * @param lstId ����id�б�
	 * @return �Ƿ�ɾ���ɹ�
	 * @author cbchen
	 * @time 2015��6��17�� ����1:47:10
	 */
	public <T> boolean dels(Class<T> objType, List<Integer> lstId) {
		boolean isSuccess = false;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager(); 
        
        // ��������
        manager.getTransaction().begin();
        
        try {
        	
	        // ����id�б�
	        for(int id:lstId) {
	        	
	        	// ����Id���ҵ���Ӧ�Ķ���Ȼ��ɾ��
	            Object object = manager.find(objType, id);
	            if (object != null) {
	            	manager.remove(object); // ɾ��
	            	isSuccess = true;
	            } else {
	            	isSuccess = false;
	            	break;
	            }
	        }
	        
	        // ɾ���ɹ����ύ
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
	 * �޸ĵ�������
	 * @param objType ��Ҫ�޸ĵ���������
	 * @param id ��Ҫ�޸ĵĶ���id
	 * @param objData ����Դ����
	 * @return �Ƿ��޸ĳɹ�
	 * @author cbchen
	 * @time 2015��6��17�� ����1:01:08
	 */
	public <T> boolean update(Class<T> objType, int id, Object objData) {
		boolean isSuccess = false;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager(); 
        
        // ��������
        manager.getTransaction().begin();
        
        try {
	        // ����Id���ҵ���Ӧ�Ķ���Ȼ������޸�
	        Object object = manager.find(objType, id);
	        if (object != null) {
	        	_setupData(objType, object, objData); // ��������
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
	 * �����޸�
	 * @param objType ��������
	 * @param mapData �ֵ����ݶ���  key:id, value:����Դ
	 * @return �Ƿ��޸ĳɹ�
	 * @author cbchen
	 * @time 2015��6��17�� ����1:35:47
	 */
	public <T> boolean updates(Class<T> objType, Map<Integer, Object> mapData) {
		boolean isSuccess = false;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager(); 
        
        // ��������
        manager.getTransaction().begin();
        
        try{
        	
	        // �����ֵ����ݶ���
	        for (Integer id:mapData.keySet()) {
	        	
	        	// ����Id���ҵ���Ӧ�Ķ���Ȼ������޸�
	        	Object object = manager.find(objType, id);
	        	if (object == null) {
	        		isSuccess = false;
	        		break;
	        	}
	        	
	        	_setupData(objType, object, mapData.get(id)); // ��������
	        	isSuccess = true;
	        }
	        
	        // �޸ĳɹ����ύ
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
	 * ��������
	 * @param objType ��������
	 * @param objDec ����Ŀ��
	 * @param objSrc ����Դ
	 * @author cbchen
	 * @time 2015��6��17�� ����1:09:51
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
	 * ��ѯ����
	 * @param sql ��ѯ���
	 * @param lstCondi ��ѯ����
	 * @return ���ز�ѯ�����б�
	 * @author cbchen
	 * @time 2015��6��17�� ����3:18:44
	 */
	public List<?> getDataByCondi(String sql, List<?> lstCondi) {
		
		List<?> lstObj = null;
		
		// ��ȡʵ�������
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE);
		
		// ��ȡʵ�������
        EntityManager manager =  factory.createEntityManager(); 
        
        // ��������
        manager.getTransaction().begin();
        
        // sql = "from User where id in(?, ?, ?)"
        try {
	        Query query = manager.createQuery(sql);
	        if (lstCondi != null) { // ��Ӳ���
	        	for(int i = 1;i <= lstCondi.size();i++) {
	        		query.setParameter(i, lstCondi.get(i - 1));
	        	}
	        	
	        	//����Query�Ļ�ý�����ķ���
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
