package my.tesi.questionario.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import my.tesi.questionario.dao.UserRepository;
import my.tesi.questionario.entity.Authority;
import my.tesi.questionario.entity.FormUser;
import my.tesi.questionario.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl (UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		Optional<User> result = userRepository.findById(userName);
		
		User theUser = null;
		
		if (result.isPresent()) {
			theUser = result.get();
		}
		else {
			theUser = null;
		}
		
		return theUser;
	}

	@Override
	@Transactional
	public void save(FormUser theFormUser) {
		
		User theUser = new User();
		
		theUser.setUserName(theFormUser.getUserName());
		theUser.setPassword(passwordEncoder.encode(theFormUser.getPassword()));
		theUser.setEnabled(theFormUser.getEnabled());
		theUser.setNome(theFormUser.getNome());
		theUser.setCognome(theFormUser.getCognome());
		theUser.setEmail(theFormUser.getEmail());
		theUser.setEta(theFormUser.getEta());
		theUser.setGenere(theFormUser.getGenere());
		
		
		//Arrays.asList( new Authority(theFormUser.getUserName(), "ROLE_UTENTE") ) 
		
		Authority theAuth = new Authority("ROLE_UTENTE");
		theAuth.setUser(theUser);
		
		theUser.addAuthority(theAuth);
		
		userRepository.save(theUser);
		
	}

	@Override
	public List<User> findNotEnabled() {
		return userRepository.findNotEnabled();
	}

	@Override
	@Transactional
	public void setUserEnabled(String username) {
		userRepository.setUserEnabled(username);
		
	}
	
	



}
