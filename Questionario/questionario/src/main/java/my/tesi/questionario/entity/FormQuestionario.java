package my.tesi.questionario.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormQuestionario {
	
	@NotNull(message = "- campo richiesto")
	private int id_questionario;
	
	@NotNull(message = "- campo richiesto")
	@Size(min = 1, message = "lunghezza invalida")
	private String titolo;
		
	private List<FormDomanda> domande;
	
	private List<FormRisposta> risposte;
	
	public FormQuestionario() {
		
	}
	

	public FormQuestionario(@NotNull(message = "- campo richiesto") int id_questionario,
			@NotNull(message = "- campo richiesto") @Size(min = 1, message = "lunghezza invalida") String titolo,
			List<FormDomanda> domande, List<FormRisposta> risposte) {
		this.id_questionario = id_questionario;
		this.titolo = titolo;
		this.domande = domande;
		this.risposte = risposte;
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

	public List<FormDomanda> getDomande() {
		return domande;
	}

	public void setDomande(List<FormDomanda> domande) {
		this.domande = domande;
	}

	public List<FormRisposta> getRisposte() {
		return risposte;
	}

	public void setRisposte(List<FormRisposta> risposte) {
		this.risposte = risposte;
	}

		

}
