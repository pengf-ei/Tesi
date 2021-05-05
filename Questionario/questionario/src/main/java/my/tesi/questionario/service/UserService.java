package my.tesi.questionario.service;

import java.util.List;

import my.tesi.questionario.entity.FormUser;
import my.tesi.questionario.entity.User;

public interface UserService {

	public User findByUserName(String userName);
	
	public void save(FormUser theFormUser);
	
	public List<User> findNotEnabled();
	
	public void setUserEnabled(String username);
	
}
