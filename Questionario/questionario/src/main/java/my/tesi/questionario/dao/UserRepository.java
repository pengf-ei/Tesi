package my.tesi.questionario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.tesi.questionario.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u FROM User u where u.enabled = 0")
	public List<User> findNotEnabled();
	
	@Modifying
	@Query(value = "UPDATE User SET enabled = 1 WHERE userName = :username")
	public void setUserEnabled(@Param("username") String username);
	
	public User findByEmail(String email);
}
