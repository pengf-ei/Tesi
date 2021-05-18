package my.tesi.questionario.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class FormDomanda {
	
	private int id_questionario;
	
	private int id_domanda;

	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String domanda;
	
	@NotNull(message = "- campo richiesto")
	private String tipo;
	
	private int num;
	
	private List<Integer> scores;
	
	private List<String> risposte;
	
	public FormDomanda() {
		
	}

	public int getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(int id_questionario) {
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<String> getRisposte() {
		return risposte;
	}

	public void setRisposte(List<String> risposte) {
		this.risposte = risposte;
	}
	
	public void addRisposta(String risposta) {
		if(this.risposte == null) {
			this.risposte = new ArrayList<>();
		}
		
		this.risposte.add(risposta);
		
	}
	
	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "FormDomanda [id_questionario=" + id_questionario + ", id_domanda=" + id_domanda + ", domanda=" + domanda
				+ ", tipo=" + tipo + ", num=" + num + ", scores=" + scores + ", risposte=" + risposte + "]";
	}

	
}
