package my.tesi.questionario.entity;

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
@Table(name="risposte")
public class Risposta {
	
	@ManyToOne
	@JoinColumn(name="id_questionario")
	private Questionario id_questionario;
	
	@ManyToOne
	@JoinColumn(name="id_domanda")
	private Domanda id_domanda;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_risposta")
	private int id_risposta;
	
	@Column(name="desrisposta")
	private String desrisposta;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="score")
	private int score;
	
	@OneToMany(mappedBy="id_risposta_reg", cascade = CascadeType.ALL)
	private List<RegistroRisposta> registroRisposte;
	
	public Risposta() {
		
	}

	public Questionario getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(Questionario id_questionario) {
		this.id_questionario = id_questionario;
	}

	public Domanda getId_domanda() {
		return id_domanda;
	}

	public void setId_domanda(Domanda id_domanda) {
		this.id_domanda = id_domanda;
	}

	public int getId_risposta() {
		return id_risposta;
	}

	public void setId_risposta(int id_risposta) {
		this.id_risposta = id_risposta;
	}

	public String getDesrisposta() {
		return desrisposta;
	}

	public void setDesrisposta(String desrisposta) {
		this.desrisposta = desrisposta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Risposta [id_questionario=" + id_questionario + ", id_domanda=" + id_domanda + ", id_risposta="
				+ id_risposta + ", desrisposta=" + desrisposta + ", tipo=" + tipo + ", score=" + score
				+ ", registroRisposte=" + registroRisposte + "]";
	}

	

}
