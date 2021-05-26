package my.tesi.questionario.entity;

import java.util.ArrayList;
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
	private int enabled = 0;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name="eta")
	private int eta;
	
	@Column(name="genere")
	private String genere;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Authority> authorities;
	
	@OneToMany(mappedBy="username_reg", cascade = CascadeType.ALL)
	private List<RegistroRisposta> registrorisposte;
	
	public User() {
		
	}

	public User(String userName, String password, int enabled, String nome, String cognome, String email, int eta,
			String genere) {
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.eta = eta;
		this.genere = genere;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	
	public List<RegistroRisposta> getRegistrorisposte() {
		return registrorisposte;
	}

	public void setRegistrorisposte(List<RegistroRisposta> registrorisposte) {
		this.registrorisposte = registrorisposte;
	}

	public void addAuthority(Authority auth) {
		
		if(authorities == null) {
			authorities = new ArrayList<>();
		}
		
		authorities.add(auth);
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", enabled=" + enabled + ", nome=" + nome
				+ ", cognome=" + cognome + ", email=" + email + ", eta=" + eta + ", genere=" + genere + ", authorities="
				+ authorities + ", registrorisposte=" + registrorisposte + "]";
	}
	
	
	
}
