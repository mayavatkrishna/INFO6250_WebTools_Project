package com.me.eventhub.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.me.eventhub.pojo.Role;



@Component
public class RoleDAO extends DAO  {

  
	public Role get(String role_name) throws Exception {
		try {
			begin();
			Query query = getSession().createQuery("from Role where role_name = :role_name");
			query.setString("role_name", role_name);
			Role role = (Role) query.uniqueResult();
			close();
			return role;
		}catch(HibernateException e){
			rollback();
			throw new Exception("Role not found");
		}
	}
}
