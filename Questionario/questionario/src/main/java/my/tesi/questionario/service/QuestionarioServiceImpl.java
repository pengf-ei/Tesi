package my.tesi.questionario.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.tesi.questionario.dao.SessioneRepository;
import my.tesi.questionario.entity.Sessione;

@Service
public class QuestionarioServiceImpl implements QuestionarioService {
	
	private SessioneRepository sessioneRepository;
	
	@Autowired
	public QuestionarioServiceImpl(SessioneRepository theSessioneRepository) {
		sessioneRepository = theSessioneRepository;
	}

	@Override
	@Transactional
	public List<Sessione> findSessioni() {

		return sessioneRepository.findAll();
	}



}
