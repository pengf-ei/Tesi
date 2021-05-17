package my.tesi.questionario.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class FormDomanda {
	
	private int id_questionario;
	
	private int id_domanda;

	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String domanda;
	
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

	@Override
	public String toString() {
		return "FormDomanda [id_questionario=" + id_questionario + ", id_domanda=" + id_domanda + ", domanda=" + domanda
				+ "]";
	}

	
}
