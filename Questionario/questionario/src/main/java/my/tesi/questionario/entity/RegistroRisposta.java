package my.tesi.questionario.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "registrorisposte")
public class RegistroRisposta {
	
	@Id
	@Column(name="id_registro")
	private int id_registro;
	
	@ManyToOne
	@JoinColumn(name="id_questionario")
	private Questionario id_questionario_reg;
	
	@ManyToOne
	@JoinColumn(name="id_domanda")
	private Domanda id_domanda_reg;
	
	@ManyToOne
	@JoinColumn(name="id_risposta")
	private Risposta id_risposta_reg;
	
	@Column(name="rispaperta")
	private String rispaperta;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User username_reg;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datacompilazione")
	private Date datacompilazione;

	public RegistroRisposta() {
		
	}

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public Questionario getId_questionario_reg() {
		return id_questionario_reg;
	}

	public void setId_questionario_reg(Questionario id_questionario_reg) {
		this.id_questionario_reg = id_questionario_reg;
	}

	public Domanda getId_domanda_reg() {
		return id_domanda_reg;
	}

	public void setId_domanda_reg(Domanda id_domanda_reg) {
		this.id_domanda_reg = id_domanda_reg;
	}

	public Risposta getId_risposta_reg() {
		return id_risposta_reg;
	}

	public void setId_risposta_reg(Risposta id_risposta_reg) {
		this.id_risposta_reg = id_risposta_reg;
	}

	public String getRispaperta() {
		return rispaperta;
	}

	public void setRispaperta(String rispaperta) {
		this.rispaperta = rispaperta;
	}

	public User getUsername_reg() {
		return username_reg;
	}

	public void setUsername_reg(User username_reg) {
		this.username_reg = username_reg;
	}

	public Date getDatacompilazione() {
		return datacompilazione;
	}

	public void setDatacompilazione(Date datacompilazione) {
		this.datacompilazione = datacompilazione;
	}

	@Override
	public String toString() {
		return "RegistroRisposta [id_registro=" + id_registro + ", id_questionario_reg=" + id_questionario_reg
				+ ", id_domanda_reg=" + id_domanda_reg + ", id_risposta_reg=" + id_risposta_reg + ", rispaperta="
				+ rispaperta + ", username_reg=" + username_reg + ", datacompilazione=" + datacompilazione + "]";
	}
	

}
