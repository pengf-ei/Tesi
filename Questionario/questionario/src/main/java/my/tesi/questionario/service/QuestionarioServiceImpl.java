package my.tesi.questionario.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.tesi.questionario.dao.QuestionarioRepository;
import my.tesi.questionario.dao.SessioneRepository;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.Sessione;

@Service
public class QuestionarioServiceImpl implements QuestionarioService {
	
	private SessioneRepository sessioneRepository;
	
	private QuestionarioRepository questionarioRepository;
	
	@Autowired
	public QuestionarioServiceImpl(SessioneRepository theSessioneRepository, QuestionarioRepository theQuestionarioRepository) {
		sessioneRepository = theSessioneRepository;
		questionarioRepository = theQuestionarioRepository;
	}

	@Override
	@Transactional
	public List<Sessione> findSessioni() {

		return sessioneRepository.findAll();
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

}
