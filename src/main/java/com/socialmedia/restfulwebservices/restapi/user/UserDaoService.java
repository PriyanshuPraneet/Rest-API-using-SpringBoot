package com.socialmedia.restfulwebservices.restapi.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	static {
		users.add(new User(++userCount,"Ram",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Shyam",LocalDate.now().minusYears(25)));
		users.add(new User(++userCount,"Dhawan",LocalDate.now().minusYears(10)));

	}
	
	public User addUser(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
	public List<User> retrieveAllUsers(){
		return users;
	}
	
	public User retrieveUser(Integer id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public void deleteById(Integer id) {
		for(User user : users) {
			if(user.getId() == id) {
				users.remove(user);
				break;
			}
		}
	}
}
