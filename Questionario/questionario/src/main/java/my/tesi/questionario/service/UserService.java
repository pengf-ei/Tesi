package my.tesi.questionario.service;

import my.tesi.questionario.entity.FormUser;
import my.tesi.questionario.entity.User;

public interface UserService {

	public User findByUserName(String userName);
	
	public void save(FormUser theFormUser);
	
}
