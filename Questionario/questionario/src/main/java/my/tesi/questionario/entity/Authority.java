package my.tesi.questionario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;	

@Entity
@Table(name="authorities")
@IdClass(AuthorityPK.class)
public class Authority {
		
	@Id
	@Column(name="authority")
	private String authority;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	public Authority() {
		
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Authority(String authority) {
		this.authority = authority;
	}
	
	
}
