package my.tesi.questionario.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class FormSessione {
	
	private int id;
	
	@NotNull(message = "- campo richiesto")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date inizio;
	
	@NotNull(message = "- campo richiesto")
	@Future(message = "non può essere in passato")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date fine;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String nomesessione;
	
//	private List<FormQuestionario> questionari;

	
	public FormSessione() {
		
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "FormSessione [id=" + id + ", inizio=" + inizio + ", fine=" + fine + ", nomesessione=" + nomesessione
				+ "]";
	}


	
}
