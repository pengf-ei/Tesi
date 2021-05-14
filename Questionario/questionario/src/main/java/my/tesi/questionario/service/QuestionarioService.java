package my.tesi.questionario.service;

import java.util.List;

import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.Sessione;

public interface QuestionarioService {

	public List<Sessione> findSessioni();
	
	public Sessione findByNomesessione(String nomesessione);
	
	public Sessione findSessioneById(int sessionId);
	
	public Questionario findQuestionarioById(int id);
	
	public Questionario saveQuestionario(Questionario theQuestionario);

	
}
