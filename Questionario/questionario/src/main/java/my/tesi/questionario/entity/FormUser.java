package my.tesi.questionario.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import my.tesi.questionario.validation.FieldMatch;
import my.tesi.questionario.validation.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = " non corrispodono")
})
public class FormUser {
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String userName;

	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String password;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String matchingPassword;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String nome;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String cognome;
	
	@ValidEmail(message = "- invalido")
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "- campo richiesto")
	private String email;

	@Min(18)
	@Max(100)
	private int eta;
	
	private String genere;
	
	private int enabled;
	
	public FormUser() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(userName != null) {
			this.userName = userName.toLowerCase();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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
		if(email != null) {
			this.email = email.toLowerCase();
		}
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	

}
