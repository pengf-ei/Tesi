package my.tesi.questionario.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="enabled")
	private int enabled = 1;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Authority> authorities;
	
	public User() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public User(String userName, String password, int enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", enabled=" + enabled + ", authorities="
				+ authorities + "]";
	}
	
	public void addAuthority(Authority auth) {
		
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		
		authorities.add(auth);
	}
	
}
