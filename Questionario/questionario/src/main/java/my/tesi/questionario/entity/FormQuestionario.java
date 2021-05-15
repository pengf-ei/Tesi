package my.tesi.questionario.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormQuestionario {
	
	private int id_questionario;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String titolo;
		
	private int id_sessione;
	
	public FormQuestionario() {
		
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

	public int getId_sessione() {
		return id_sessione;
	}

	public void setId_sessione(int id_sessione) {
		this.id_sessione = id_sessione;
	}

	@Override
	public String toString() {
		return "FormQuestionario [id_questionario=" + id_questionario + ", titolo=" + titolo + ", id_sessione="
				+ id_sessione + "]";
	}
	
	
	

}
