package my.tesi.questionario.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormQuestionario {
	
	private int id_questionario;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String titolo;
		
	private FormSessione id_sessione;
	
	public FormQuestionario() {
		
	}
	

}
