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
@Table(name="domande")
public class Domanda {
	
	@ManyToOne
	@JoinColumn(name="id_questionario")
	private Questionario id_questionario;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_domanda")
	private int id_domanda;
	
	@Column(name="domanda")
	private String domanda;
	
	@OneToMany(mappedBy="id_domanda", cascade = CascadeType.ALL)
	private List<Risposta> risposte;
	
	@OneToMany(mappedBy="id_domanda_reg", cascade = CascadeType.ALL)
	private List<RegistroRisposta> registrorisposte;
	
	public Domanda() {
		
	}

	public Questionario getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(Questionario id_questionario) {
		this.id_questionario = id_questionario;
	}

	public int getId_domanda() {
		return id_domanda;
	}

	public void setId_domanda(int id_domanda) {
		this.id_domanda = id_domanda;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
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
	

	public List<RegistroRisposta> getRegistrorisposte() {
		return registrorisposte;
	}

	public void setRegistrorisposte(List<RegistroRisposta> registrorisposte) {
		this.registrorisposte = registrorisposte;
	}

	@Override
	public String toString() {
		return "Domanda [id_questionario=" + id_questionario + ", id_domanda=" + id_domanda + ", domanda=" + domanda
				+ ", risposte=" + risposte + ", registrorisposte=" + registrorisposte + "]";
	}


}
