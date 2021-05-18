package my.tesi.questionario.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
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

import my.tesi.questionario.entity.Domanda;
import my.tesi.questionario.entity.FormDomanda;
import my.tesi.questionario.entity.FormQuestionario;
import my.tesi.questionario.entity.FormSessione;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.Risposta;
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
	
	@GetMapping("/surveys/show/survey")
	public String showSurvey(@RequestParam("Id") int surveyId, Model theModel) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		theModel.addAttribute("questionario", theQuestionario);
		
		return "survey-details";
	}
	
	// ELIMINAZIONE LOGICA QUESTIONARIO
	
	@GetMapping("/surveys/delete/survey")
	public String deleteSurvey(@RequestParam("Id") int surveyId) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
			
		if ( theQuestionario != null ) {
			theQuestionario.setId_sessione(questionarioService.findByNomesessione("Eliminati"));
		}
		
		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
//		questionarioService.deleteQuestionario(theQuestionario);
		
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
	
	// MODIFICA QUESTIONARIO
	
	@GetMapping("/surveys/edit/survey")
	public String editSurvey(@RequestParam("Id") int surveyId, Model theModel, HttpSession session) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		FormQuestionario theFormQuestionario = new FormQuestionario();
		
		theFormQuestionario.setId_questionario(theQuestionario.getId_questionario());
		theFormQuestionario.setId_sessione(theQuestionario.getId_sessione().getId());
		theFormQuestionario.setTitolo(theQuestionario.getTitolo());
				
		theModel.addAttribute("questionario", theFormQuestionario);
		
		session.setAttribute("creazione", "false");
		
//		Questionario theSaved = questionarioService.saveQuestionario(theQuestionario);
		
		return "survey-create-survey"; //"survey-details-edit";
	}
	
	// CREAZIONE QUESTIONARIO
	@GetMapping("/surveys/create/survey")
	public String createNewSurvey(@RequestParam("sessionId") int sessionId, Model theModel, HttpSession session) {
		
		FormQuestionario theFormQuestionario = new FormQuestionario();
		
		theFormQuestionario.setId_sessione(sessionId);
		
		theModel.addAttribute("questionario", theFormQuestionario);
		
		session.setAttribute("creazione", "true");
				
		return "survey-create-survey";
	}
	
	@PostMapping("/surveys/create/survey/process")
	public String processNewSurvey(@Valid @ModelAttribute("questionario") FormQuestionario formQuestionario,
			BindingResult theBindingResult, Model theModel, HttpSession session) {

			System.out.println(theModel.toString());
		
			if (theBindingResult.hasErrors()) {
			
				return "survey-create-survey";
			
			}
						
			Questionario theQuestionario = new Questionario();
			theQuestionario.setId_questionario(formQuestionario.getId_questionario());
			theQuestionario.setTitolo(formQuestionario.getTitolo());
			theQuestionario.setId_sessione(questionarioService.findSessioneById(formQuestionario.getId_sessione()));
			
			theQuestionario = questionarioService.saveQuestionario(theQuestionario);
			
			if(session.getAttribute("creazione").equals("true")) {
				return "redirect:/surveys/create/question?surveyId=" + theQuestionario.getId_questionario();
			}
			
			return "redirect:/";
		}
	
	// CREAZIONE SESSIONE
	
	@GetMapping("/surveys/create/session")
	public String createNewSession(Model theModel, HttpSession session) {
		
		theModel.addAttribute("sessione", new FormSessione());
		
		session.setAttribute("creazione", "true");
				
		return "survey-create-session";
	}
	
	@PostMapping("/surveys/create/session/process")
	public String processNewSession(@Valid @ModelAttribute("sessione") FormSessione formSessione,
									BindingResult theBindingResult, Model theModel, HttpSession session) {
		
		
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
		
		
		theSessione = questionarioService.saveSessione(theSessione);
		
		if(session.getAttribute("creazione").equals("true")) {
			return "redirect:/surveys/create/survey?sessionId=" + theSessione.getId();
		}
		
		return "redirect:/";
	}
	
	// ELIMINAZIONE FISICA SESSIONE
	
	@GetMapping("/surveys/delete/session")
	public String deleteSession(@RequestParam("Id") int sessionId) {
		
		Sessione theSessione  = questionarioService.findSessioneById(sessionId);
			
		if ( theSessione != null ) {
			questionarioService.deleteSessione(theSessione);
		}
		
		return "redirect:/";
	}
	
	// MODIFICA SESSIONE
	
	@GetMapping("/surveys/edit/session")
	public String editSession(@RequestParam("Id") int sessionId, Model theModel, HttpSession session) {
		
		Sessione theSessioneInit  = questionarioService.findSessioneById(sessionId);
		
		FormSessione theFormSessione = new FormSessione();
		
		theFormSessione.setId(theSessioneInit.getId());
		theFormSessione.setInizio(theSessioneInit.getInizio());
		theFormSessione.setFine(theSessioneInit.getFine());
		theFormSessione.setNomesessione(theSessioneInit.getNomesessione());
		
		theModel.addAttribute("sessione", theFormSessione);
	
		session.setAttribute("creazione", "false");
		
		return "survey-create-session";
	}
	
	// CREAZIONE DOMANDA
	@GetMapping("/surveys/create/question")
	public String createQuestion(@RequestParam("surveyId") int surveyId, Model theModel, HttpSession session) {
		
		FormDomanda formDomanda = new FormDomanda();
		
		formDomanda.setId_questionario(surveyId);
		
		theModel.addAttribute("domanda", formDomanda);
		
		session.setAttribute("creazione", "true");
		
		return "survey-create-question";
	}
	
	@PostMapping("/surveys/create/question/process")
	public String processNewQuestion(@Valid @ModelAttribute("domanda") FormDomanda formDomanda,
			BindingResult theBindingResult, Model theModel) {
		
		
		if (theBindingResult.hasErrors() || ( (! formDomanda.getTipo().equals("open")) && (formDomanda.getNum() < 2) ) )  {
			
			theModel.addAttribute("numrisposteerr", "");
			
			return "survey-create-question";
		 }
		
		Domanda theDomanda = new Domanda();
		
		theDomanda.setId_questionario(questionarioService.findQuestionarioById(formDomanda.getId_questionario()));
		theDomanda.setId_domanda(formDomanda.getId_domanda());
		theDomanda.setDomanda(formDomanda.getDomanda());
		
//		System.out.println(theDomanda.getId_questionario().getTitolo());
//		System.out.println(theDomanda.getId_domanda());
//		System.out.println(theDomanda.getRisposte());
		
		List<Risposta> risposte = new ArrayList<>();
		
		for (int i = 0; i < formDomanda.getNum(); i++) {
			
			Risposta tempRisposta = new Risposta();
					
			tempRisposta.setId_questionario(theDomanda.getId_questionario());
			tempRisposta.setId_domanda(theDomanda);
			tempRisposta.setId_risposta(0);
			
			if(formDomanda.getRisposte() == null) {
				tempRisposta.setDesrisposta(null);
			}
			else {
				tempRisposta.setDesrisposta(formDomanda.getRisposte().get(i));
			}
			
			tempRisposta.setTipo(formDomanda.getTipo());
			tempRisposta.setScore(formDomanda.getScores().get(i));
			
			risposte.add(tempRisposta);
		}

		
		theDomanda.setRisposte(risposte);
		
		questionarioService.saveDomanda(theDomanda);
		
		return "redirect:/surveys/show/survey?Id=" + theDomanda.getId_questionario().getId_questionario();
	}
}
