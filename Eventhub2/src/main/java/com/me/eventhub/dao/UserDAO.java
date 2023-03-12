package com.me.eventhub.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.me.eventhub.pojo.User;


@Component
public class UserDAO extends DAO {

       // method for Login
		public User get(String username, String password) throws Exception{
			try {
				begin();
				Query query = getSession().createQuery("from User where username = :username and password = :password");
				query.setString("username", username);
				query.setString("password", password);
				User user = (User) query.uniqueResult();
				close();
				
				if(user == null) {
					return null;
				}else {
					return user;
				}		
			}catch(HibernateException e){
				rollback();
				throw new Exception("Invalid username or password");
			}
			
		}
		
		// method to register user
		public User registerUser(User user) {
			try {
				begin();
				getSession().save(user);
				commit();
				close();
				return user;
			}catch(HibernateException e){
				rollback();
				e.printStackTrace();
				return null;
			}
			
		}
		
		//method for validation for registration
		public boolean registerCriteria(String email, String username) {
			try {
				boolean result = true;
				begin();
				Criteria criteria = getSession().createCriteria(User.class);
				Criteria criteria1 = getSession().createCriteria(User.class);
				criteria.add(Restrictions.eq("email", email));
				List<User> crit1 = criteria.list();
				criteria1.add(Restrictions.eq("username", username));
				List<User> crit2 = criteria1.list();
				commit();
				close();
				if((crit1.size() >= 1) || (crit2.size() >= 1)) {
					result = true;
				}else {
					result = false;
				}
				return result;
			}catch(HibernateException e){
				rollback();
				e.printStackTrace();
			}
			return true;
		}
}
