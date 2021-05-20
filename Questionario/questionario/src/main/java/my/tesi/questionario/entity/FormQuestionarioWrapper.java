package my.tesi.questionario.entity;

import java.util.List;

public class FormQuestionarioWrapper {
	
	private int id_questionario;
	
	private List<FormDomanda> formDomande;

	public int getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(int id_questionario) {
		this.id_questionario = id_questionario;
	}

	public List<FormDomanda> getFormDomande() {
		return formDomande;
	}

	public void setFormDomande(List<FormDomanda> formDomande) {
		this.formDomande = formDomande;
	}

	@Override
	public String toString() {
		return "FormQuestionarioWrapper [id_questionario=" + id_questionario + ", formDomande=" + formDomande + "]";
	}
	
	

}
