package my.tesi.questionario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.Sessione;
import my.tesi.questionario.service.QuestionarioService;

@Controller
public class QuestionarioController {
	
	private QuestionarioService questionarioService;
	
	@Autowired
	public QuestionarioController(QuestionarioService theQuestionarioService) {
		questionarioService = theQuestionarioService;
	}
	
	@GetMapping("/")
	public String index(Model theModel) {
	
		List<Sessione> sessioni = new ArrayList<>();
		
		sessioni = questionarioService.findSessioni();
		
		theModel.addAttribute("sessioni", sessioni);

		
		return "index";
	}
	
	@GetMapping("/surveys/show")
	public String showSurvey(@RequestParam("surveyId") int surveyId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		theModel.addAttribute("questionario", theQuestionario);
		
		return "survey-details";
	}

}
