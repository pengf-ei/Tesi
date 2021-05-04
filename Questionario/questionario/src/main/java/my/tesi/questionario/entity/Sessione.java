package my.tesi.questionario.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sessioni")
public class Sessione {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sessione")
	private int id;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="inizio")
	private Date inizio;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fine")
	private Date fine;
	
	@Column(name="nomesessione")
	private String nomesessione;
	
	public Sessione () {
		
	}

	public Sessione(Date inizio, Date fine, String nomesessione) {
		this.inizio = inizio;
		this.fine = fine;
		this.nomesessione = nomesessione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public String getNomesessione() {
		return nomesessione;
	}

	public void setNomesessione(String nomesessione) {
		this.nomesessione = nomesessione;
	}

	@Override
	public String toString() {
		return "Sessione [id=" + id + ", inizio=" + inizio + ", fine=" + fine + ", nomesessione=" + nomesessione + "]";
	}
	
	
	
}