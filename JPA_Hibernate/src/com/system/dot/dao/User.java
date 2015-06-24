package com.system.dot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="User")
public class User implements java.io.Serializable {
	private int id;
	private String username;
	private String password;
	private String[] pics;

/*
unique���Ա�ʾ���ֶ��Ƿ�ΪΨһ��ʶ��Ĭ��Ϊfalse�����������һ���ֶ���ҪΨһ��ʶ����ȿ���ʹ�øñ�ǣ�Ҳ����ʹ��@Table����е�@UniqueConstraint��
nullable���Ա�ʾ���ֶ��Ƿ����Ϊnullֵ��Ĭ��Ϊtrue��
insertable���Ա�ʾ��ʹ�á�INSERT���ű���������ʱ���Ƿ���Ҫ������ֶε�ֵ��
updatable���Ա�ʾ��ʹ�á�UPDATE���ű���������ʱ���Ƿ���Ҫ���¸��ֶε�ֵ��insertable��updatable����һ�������ֻ�������ԣ���������������ȡ���Щ�ֶε�ֵͨ�����Զ����ɵġ�
columnDefinition���Ա�ʾ������ʱ�����ֶδ�����SQL��䣬һ������ͨ��Entity���ɱ���ʱʹ�á�
table���Ա�ʾ��ӳ������ʱ��ָ����ı��е��ֶΡ�Ĭ��ֵΪ����ı������йض�����ӳ�佫�ڱ��µ�5.6С������ϸ������
length���Ա�ʾ�ֶεĳ��ȣ����ֶε�����Ϊvarcharʱ�������Բ���Ч��Ĭ��Ϊ255���ַ���
precision���Ժ�scale���Ա�ʾ���ȣ����ֶ�����Ϊdoubleʱ��precision��ʾ��ֵ���ܳ��ȣ�scale��ʾС������ռ��λ����
*/
	
	@Id
	@Column(name = "id", unique = true, nullable = false, 
		insertable = true, updatable = true, precision = 20, scale = 0) 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", unique = false, nullable = true, 
			insertable = true, updatable = true, length = 255)
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	@Column(name = "password", unique = false, nullable = true, 
			insertable = true, updatable = true, length = 255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Transient
	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}
}