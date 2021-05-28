package my.tesi.questionario.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.tesi.questionario.dao.DomandaRepository;
import my.tesi.questionario.dao.QuestionarioRepository;
import my.tesi.questionario.dao.RegistroRispostaRepository;
import my.tesi.questionario.dao.RispostaRepository;
import my.tesi.questionario.dao.SessioneRepository;
import my.tesi.questionario.entity.Domanda;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.RegistroRisposta;
import my.tesi.questionario.entity.Risposta;
import my.tesi.questionario.entity.Sessione;
import my.tesi.questionario.entity.User;

@Service
public class QuestionarioServiceImpl implements QuestionarioService {
	
	private SessioneRepository sessioneRepository;
	
	private QuestionarioRepository questionarioRepository;
	
	private DomandaRepository domandaRepository;
	
	private RispostaRepository rispostaRepository;
	
	private RegistroRispostaRepository registroRispostaRepository;
	
	@Autowired
	public QuestionarioServiceImpl(SessioneRepository theSessioneRepository,
									QuestionarioRepository theQuestionarioRepository,
									DomandaRepository theDomandaRepository,
									RispostaRepository theRispostaRepository,
									RegistroRispostaRepository theRegistroRispostaRepository) {
		sessioneRepository = theSessioneRepository;
		questionarioRepository = theQuestionarioRepository;
		domandaRepository = theDomandaRepository;
		rispostaRepository = theRispostaRepository;
		registroRispostaRepository = theRegistroRispostaRepository;
	}

	@Override
	@Transactional
	public List<Sessione> findSessioni() {

		return sessioneRepository.findAll();
	}

	@Override
	public Sessione findByNomesessione(String nomesessione) {
		return sessioneRepository.findByNomesessione(nomesessione);
	}

	@Override
	public Sessione findSessioneById(int sessionId) {
		Optional<Sessione> sessione = sessioneRepository.findById(sessionId);
		
		Sessione theSessione = null;
		
		if (sessione.isPresent()) {
			theSessione = sessione.get();
		}
		else {
			theSessione = null;
		}
		
		return theSessione;
	}

	@Override
	public Sessione saveSessione(Sessione theSessione) {
		return sessioneRepository.saveAndFlush(theSessione);
	}
	
	

	@Override
	public void deleteSessione(Sessione theSessione) {
		sessioneRepository.delete(theSessione);
		
	}

	@Override
	public Questionario findQuestionarioById(int id) {
		Optional<Questionario> questionario = questionarioRepository.findById(id);
		
		Questionario theQuestionario = null;
		
		if (questionario.isPresent()) {
			theQuestionario = questionario.get();
		}
		else {
			theQuestionario = null;
		}
		
		return theQuestionario;
	}

	@Override
	public Questionario saveQuestionario(Questionario theQuestionario) {
		return questionarioRepository.saveAndFlush(theQuestionario);
		
	}
	

	@Override
	public void deleteQuestionario(Questionario theQuestionario) {
		questionarioRepository.delete(theQuestionario);
		
	}
	
	@Override
	public Domanda findDomandaById(int questionId) {
		
		Optional<Domanda> domanda = domandaRepository.findById(questionId);
		
		Domanda theDomanda = null;
		
		if (domanda.isPresent()) {
			theDomanda = domanda.get();
		}
		else {
			theDomanda = null;
		}
				
		return theDomanda;
	}

	@Override
	public Domanda saveDomanda(Domanda theDomanda) {
		
		return domandaRepository.saveAndFlush(theDomanda);
	}

	@Override
	public void deleteDomanda(Domanda theDomanda) {
		domandaRepository.delete(theDomanda);
		
	}

	@Override
	public Risposta findRispostaById(int answerId) {
		
		Optional<Risposta> risposta = rispostaRepository.findById(answerId);
		
		Risposta theRisposta = null;
		
		if (risposta.isPresent()) {
			theRisposta = risposta.get();
		}
		else {
			theRisposta = null;
		}
		
		return theRisposta;
	}

	@Override
	public void deleteRisposta(Risposta theRisposta) {
		rispostaRepository.delete(theRisposta);
		
	}

	@Override
	public void deleteRispostaById(int answerId) {
		rispostaRepository.deleteRisposta(answerId);
		
	}

	@Override
	public Risposta saveRisposta(Risposta theRisposta) {
		return rispostaRepository.saveAndFlush(theRisposta);
	}

	@Override
	public List<RegistroRisposta> findByDomandaAndUsername(Domanda theDomanda, User username) {
		return registroRispostaRepository.findByDomandaAndUsername(theDomanda, username);
	}

	@Override
	public void deleteRegistroRisposta(RegistroRisposta theRegistroRisposta) {
		registroRispostaRepository.delete(theRegistroRisposta);
		
	}

	@Override
	public RegistroRisposta saveRegistroRisposta(RegistroRisposta saveRegistroRisposta) {
		return registroRispostaRepository.saveAndFlush(saveRegistroRisposta);
	}

	
}
