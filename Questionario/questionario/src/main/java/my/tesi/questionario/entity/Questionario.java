package my.tesi.questionario.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="questionari")
public class Questionario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_questionario")
	private int id_questionario;
	
	@Column(name="titolo")
	private String titolo;
	
	@ManyToOne
	@JoinColumn(name="id_sessione")
	private Sessione id_sessione;
	
	@OneToMany(mappedBy="id_questionario", cascade = CascadeType.ALL)
	private List<Domanda> domande;
	
	@OneToMany(mappedBy="id_domanda", cascade = CascadeType.ALL)
	private List<Risposta> risposte;
	
	
	public Questionario() {
		
	}

	public int getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(int id_questionario) {
		this.id_questionario = id_questionario;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Sessione getId_sessione() {
		return id_sessione;
	}

	public void setId_sessione(Sessione id_sessione) {
		this.id_sessione = id_sessione;
	}

	public List<Domanda> getDomande() {
		return domande;
	}

	public void setDomande(List<Domanda> domande) {
		this.domande = domande;
	}
	
	public void addDomanda(Domanda theDomanda) {
		
		if(domande == null) {
			domande = new ArrayList<>();
		}
		
		domande.add(theDomanda);
	}

	public List<Risposta> getRisposte() {
		return risposte;
	}

	public void setRisposte(List<Risposta> risposte) {
		this.risposte = risposte;
	}


	public void addRisposta(Risposta theRisposta) {
		
		if(risposte == null) {
			risposte = new ArrayList<>();
		}
		
		risposte.add(theRisposta);
	}

	@Override
	public String toString() {
		return "Questionario [id_questionario=" + id_questionario + ", titolo=" + titolo + ", id_sessione="
				+ id_sessione + ", domande=" + domande + ", risposte=" + risposte + "]";
	}
	

	
}
