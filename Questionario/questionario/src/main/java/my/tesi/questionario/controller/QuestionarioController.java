package my.tesi.questionario.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.tesi.questionario.entity.FormQuestionario;
import my.tesi.questionario.entity.FormSessione;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.Sessione;
import my.tesi.questionario.service.QuestionarioService;

@Controller
public class QuestionarioController {
	
	private QuestionarioService questionarioService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
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
	
	// VISUALIZZA QUESTIONARIO
	
	@GetMapping("/surveys/showSurvey")
	public String showSurvey(@RequestParam("Id") int surveyId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		theModel.addAttribute("questionario", theQuestionario);
		
		return "survey-details";
	}
	
	// ELIMINAZIONE LOGICA QUESTIONARIO
	
	@GetMapping("/surveys/deleteSurvey")
	public String deleteSurvey(@RequestParam("Id") int surveyId) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
			
		if ( theQuestionario != null ) {
			theQuestionario.setId_sessione(questionarioService.findByNomesessione("Eliminati"));
		}
		
		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		
		return "redirect:/";
	}
	
	// RIABILITAZIONE QUESTIONARIO
	
	@GetMapping("/surveys/restore")
	public String restoreSurvey(@RequestParam("surveyId") int surveyId, @RequestParam("sessionId") int sessionId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
			
		if ( theQuestionario != null ) {
			theQuestionario.setId_sessione(questionarioService.findSessioneById(sessionId));
		}
		
		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		return "redirect:/";
	}
	
	// MODIFICA QUESTIONARIO-----IN PROGRESS
	
	@GetMapping("/surveys/editSurvey")
	public String editSurvey(@RequestParam("Id") int surveyId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		theModel.addAttribute("questionario", theQuestionario);
		
//		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		return "survey-details-edit";
	}
	
	// CREAZIONE QUESTIONARIO
	@GetMapping("/surveys/create/survey")
	public String createNewSurvey(@RequestParam("sessionId") int sessionId, Model theModel) {
		
		FormQuestionario theFormQuestionario = new FormQuestionario();
		
		theFormQuestionario.setId_sessione(sessionId);
		
		theModel.addAttribute("questionario", theFormQuestionario);
				
		return "survey-create-survey";
	}
	
	@PostMapping("/surveys/create/survey/process")
	public String processNewSurvey(@Valid @ModelAttribute("questionario") FormQuestionario formQuestionario,
			BindingResult theBindingResult, Model theModel) {


			if (theBindingResult.hasErrors()) {
			
				return "survey-create-survey";
			
			}
						
			Questionario theQuestionario = new Questionario();
			theQuestionario.setId_questionario(formQuestionario.getId_questionario());
			theQuestionario.setTitolo(formQuestionario.getTitolo());
			theQuestionario.setId_sessione(questionarioService.findSessioneById(formQuestionario.getId_sessione()));
			
			questionarioService.saveQuestionario(theQuestionario);
			
			return "redirect:/";
		}
	
	// CREAZIONE SESSIONE
	
	@GetMapping("/surveys/create/session")
	public String createNewSession(Model theModel) {
		
		theModel.addAttribute("sessione", new FormSessione());
				
		return "survey-create-session";
	}
	
	@PostMapping("/surveys/create/session/process")
	public String processNewSession(@Valid @ModelAttribute("sessione") FormSessione formSessione,
									BindingResult theBindingResult, Model theModel) {
		
		
		if (theBindingResult.hasErrors()) {
			
			return "survey-create-session";
		 }
		
		if (formSessione.getFine().before(formSessione.getInizio())){
			
			theModel.addAttribute("duedateinvalide", "");
			return "survey-create-session";
		}
		
		Sessione theSessione = new Sessione();
		
		theSessione.setId(formSessione.getId());
		theSessione.setNomesessione(formSessione.getNomesessione());
		theSessione.setInizio(formSessione.getInizio());
		theSessione.setFine(formSessione.getFine());
		
		
		questionarioService.saveSessione(theSessione);
		
		return "redirect:/";
	}
	
	@GetMapping("/surveys/deleteSession")
	public String deleteSession(@RequestParam("Id") int sessionId) {
		
		Sessione theSessione  = questionarioService.findSessioneById(sessionId);
			
		if ( theSessione != null ) {
			questionarioService.deleteSessione(theSessione);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/surveys/editSession")
	public String editSession(@RequestParam("Id") int sessionId, Model theModel) {
		
		Sessione theSessioneInit  = questionarioService.findSessioneById(sessionId);
		
		FormSessione theFormSessione = new FormSessione();
		
		theFormSessione.setId(theSessioneInit.getId());
		theFormSessione.setInizio(theSessioneInit.getInizio());
		theFormSessione.setFine(theSessioneInit.getFine());
		theFormSessione.setNomesessione(theSessioneInit.getNomesessione());
		
		theModel.addAttribute("sessione", theFormSessione);
		
		return "survey-create-session";
	}
	
}
