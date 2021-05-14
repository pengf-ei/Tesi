package my.tesi.questionario.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class FormDomanda {
	
	@NotNull(message = "- campo richiesto")
	private FormQuestionario id_questionario;
	
	@NotNull(message = "- campo richiesto")
	private int id_domanda;

	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String domanda;
	
	private List<FormRisposta> risposte;
	
	public FormDomanda() {
		
	}

	public FormQuestionario getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(FormQuestionario id_questionario) {
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

	public List<FormRisposta> getRisposte() {
		return risposte;
	}

	public void setRisposte(List<FormRisposta> risposte) {
		this.risposte = risposte;
	}

	
	
}
