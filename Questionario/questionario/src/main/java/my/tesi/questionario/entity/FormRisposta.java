package my.tesi.questionario.entity;

import javax.validation.constraints.NotNull;

public class FormRisposta {
	
	@NotNull(message = "- campo richiesto")
	private FormQuestionario id_questionario;
	
	@NotNull(message = "- campo richiesto")
	private FormDomanda id_domanda;
	
	@NotNull(message = "- campo richiesto")
	private int id_risposta;
	
	@NotNull(message = "- campo richiesto")
	private String desrisposta;
	
	@NotNull(message = "- campo richiesto")
	private String tipo;
	
	@NotNull(message = "- campo richiesto")
	private int score;
	
	public FormRisposta() {
		
	}

	public FormQuestionario getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(FormQuestionario id_questionario) {
		this.id_questionario = id_questionario;
	}

	public FormDomanda getId_domanda() {
		return id_domanda;
	}

	public void setId_domanda(FormDomanda id_domanda) {
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

	
}
