package my.tesi.questionario.service;

import java.util.List;

import my.tesi.questionario.entity.Domanda;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.RegistroRisposta;
import my.tesi.questionario.entity.Risposta;
import my.tesi.questionario.entity.Sessione;
import my.tesi.questionario.entity.User;

public interface QuestionarioService {

	public List<Sessione> findSessioni();
	
	public Sessione findByNomesessione(String nomesessione);
	
	public Sessione findSessioneById(int sessionId);
	
	public Sessione saveSessione(Sessione theSessione);
	
	public void deleteSessione(Sessione theSessione);
	
	
	
	public Questionario findQuestionarioById(int id);
	
	public Questionario saveQuestionario(Questionario theQuestionario);
	
	public void deleteQuestionario(Questionario theQuestionario);
	
	

	public Domanda findDomandaById(int questionId);

	public Domanda saveDomanda(Domanda theDomanda);

	public void deleteDomanda(Domanda theDomanda);
	
	

	public Risposta findRispostaById(int answerId);

	public void deleteRisposta(Risposta theRisposta);
	
	public void deleteRispostaById(int answerId);

	public Risposta saveRisposta(Risposta theRisposta);
	
	
	public List<RegistroRisposta> findByDomandaAndUsername(Domanda theDomanda, User username);
	
	public void deleteRegistroRisposta(RegistroRisposta theRegistroRisposta);

	public RegistroRisposta saveRegistroRisposta(RegistroRisposta saveRegistroRisposta);
	

	
}
