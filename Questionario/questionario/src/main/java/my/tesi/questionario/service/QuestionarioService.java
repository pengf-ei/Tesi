package my.tesi.questionario.service;

import java.util.List;

import my.tesi.questionario.entity.Domanda;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.Sessione;

public interface QuestionarioService {

	public List<Sessione> findSessioni();
	
	public Sessione findByNomesessione(String nomesessione);
	
	public Sessione findSessioneById(int sessionId);
	
	public Sessione saveSessione(Sessione theSessione);
	
	public void deleteSessione(Sessione theSessione);
	
	
	
	public Questionario findQuestionarioById(int id);
	
	public Questionario saveQuestionario(Questionario theQuestionario);
	
	public void deleteQuestionario(Questionario theQuestionario);
	
	

	public Domanda saveDomanda(Domanda theDomanda);


	

	
}
