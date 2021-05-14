package my.tesi.questionario.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.tesi.questionario.entity.FormQuestionario;
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
	
	@GetMapping("/surveys/delete")
	public String deleteSurvey(@RequestParam("surveyId") int surveyId) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
			
		if ( theQuestionario != null ) {
			theQuestionario.setId_sessione(questionarioService.findByNomesessione("Eliminati"));
		}
		
		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		
		return "redirect:/";
	}
	
	@GetMapping("/surveys/restore")
	public String restoreSurvey(@RequestParam("surveyId") int surveyId, @RequestParam("sessionId") int sessionId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
			
		if ( theQuestionario != null ) {
			theQuestionario.setId_sessione(questionarioService.findSessioneById(sessionId));
		}
		
		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		return "redirect:/";
	}
	
	@GetMapping("/surveys/edit")
	public String editSurvey(@RequestParam("surveyId") int surveyId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		System.out.println(theQuestionario.getTitolo());
		
		theModel.addAttribute("questionario", theQuestionario);
		
//		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		return "survey-details-edit";
	}
	

}
