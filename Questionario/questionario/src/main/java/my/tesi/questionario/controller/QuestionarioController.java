package my.tesi.questionario.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import my.tesi.questionario.entity.FormQuestionarioWrapper;
import my.tesi.questionario.entity.FormRisposta;
import my.tesi.questionario.entity.FormSessione;
import my.tesi.questionario.entity.Questionario;
import my.tesi.questionario.entity.RegistroRisposta;
import my.tesi.questionario.entity.ReplyDomanda;
import my.tesi.questionario.entity.ReplyQuestionarioWrapper;
import my.tesi.questionario.entity.Risposta;
import my.tesi.questionario.entity.Sessione;
import my.tesi.questionario.entity.User;
import my.tesi.questionario.service.QuestionarioService;
import my.tesi.questionario.service.UserService;

@Controller
public class QuestionarioController {
	
	private QuestionarioService questionarioService;
	
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
	}
	
	@Autowired
	public QuestionarioController(QuestionarioService theQuestionarioService, UserService theUserService) {
		questionarioService = theQuestionarioService;
		userService = theUserService;
	}
	
	@GetMapping("/")
	public String index(Model theModel, HttpSession session) {
	
		List<Sessione> sessioni = new ArrayList<>();
		
		sessioni = questionarioService.findSessioni();
		
		session.removeAttribute("titoloQuestionario");

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
	@Transactional
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
	@Transactional
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
	@Transactional
	public String processNewSurvey(@Valid @ModelAttribute("questionario") FormQuestionario formQuestionario,
			BindingResult theBindingResult, Model theModel, HttpSession session) {
		
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
	@Transactional
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
	@Transactional
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
	
	// CREAZIONE DOMANDA CON RISPOSTE
	
	@GetMapping("/surveys/create/question")
	public String createQuestion(@RequestParam("surveyId") int surveyId, Model theModel, HttpSession session) {
		
		FormDomanda formDomanda = new FormDomanda();
		
		formDomanda.setId_questionario(surveyId);
		
		theModel.addAttribute("domanda", formDomanda);
		
		session.setAttribute("creazione", "true");
		
		return "survey-create-question";
	}
	
	@PostMapping("/surveys/create/question/process")
	@Transactional
	public String processNewQuestion(@Valid @ModelAttribute("domanda") FormDomanda formDomanda,
			BindingResult theBindingResult, Model theModel, HttpSession session) {
		
		
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
		
		Object check = session.getAttribute("titoloQuestionario");
		
		if (check != null) {
			return "redirect:/surveys/edit/questions?surveyId=" + theDomanda.getId_questionario().getId_questionario();
		}
		
		
		return "redirect:/surveys/show/survey?Id=" + theDomanda.getId_questionario().getId_questionario();
	}
	
	@GetMapping("/surveys/edit/questions")
	public String editQuestions(@RequestParam("surveyId") int surveyId, Model theModel, HttpSession session) {
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		List<Domanda> domande = theQuestionario.getDomande();
		
		
		FormQuestionarioWrapper formQuestionarioWrapper = new FormQuestionarioWrapper();
		
		formQuestionarioWrapper.setId_questionario(theQuestionario.getId_questionario());
		
		List<FormDomanda> formDomande = new ArrayList<>();
		
		for(Domanda theDomanda : domande) {
			
			FormDomanda theFormDomanda = new FormDomanda();
			
			theFormDomanda.setId_questionario(theDomanda.getId_questionario().getId_questionario());
			
			theFormDomanda.setId_domanda(theDomanda.getId_domanda());
			
			theFormDomanda.setDomanda(theDomanda.getDomanda());
			
			theFormDomanda.setNum(theDomanda.getRisposte().size());
			
			List<Integer> id_risposte = new ArrayList<>();
			
			List<Integer> scores = new ArrayList<>();
			
			List<String> risposte = new ArrayList<>();
			
			for(Risposta theRisposta : theDomanda.getRisposte()) {
				
				id_risposte.add(theRisposta.getId_risposta());
				
				scores.add(theRisposta.getScore());
				
				risposte.add(theRisposta.getDesrisposta());
				
			}
			
			if(theDomanda.getRisposte().size() > 0) {
				theFormDomanda.setTipo(theDomanda.getRisposte().get(0).getTipo());
			}
			
			theFormDomanda.setId_risposte(id_risposte);
			
			theFormDomanda.setScores(scores);
			
			theFormDomanda.setRisposte(risposte);
			
			formDomande.add(theFormDomanda);
			
		}
		
		formQuestionarioWrapper.setFormDomande(formDomande);
		
//		System.out.println(formQuestionarioWrapper.toString());
		
		session.setAttribute("titoloQuestionario", theQuestionario.getTitolo());
		
		session.removeAttribute("fromOpen");
		
		theModel.addAttribute("formQuestionarioWrapper", formQuestionarioWrapper);	
		
		return "survey-edit-question";
	}
	
	@PostMapping("surveys/edit/questions/process")
	@Transactional
	public String processEditQuestions(@Valid @ModelAttribute("formQuestionarioWrapper") FormQuestionarioWrapper formQuestionarioWrapper,
			BindingResult theBindingResult, Model theModel) {
		
//		System.out.println(formQuestionarioWrapper.getId_questionario());
//		System.out.println(formQuestionarioWrapper.getFormDomande());

		if (theBindingResult.hasErrors() )  {
			
			return "survey-edit-question";
		 }
		
		for (FormDomanda theFormDomanda : formQuestionarioWrapper.getFormDomande()) {
			if(( ! theFormDomanda.getTipo().equals("open") ) && theFormDomanda.getNum() < 2) {
				
				theModel.addAttribute("errorNumberAnswer", "Inserire un numero di risposte non aperte maggiore di 1.");
				
				return "survey-edit-question";
			}
		}
		
		for(int i = 0; i < formQuestionarioWrapper.getFormDomande().size(); i++) {
			
			Domanda theDomanda = questionarioService.findDomandaById(formQuestionarioWrapper.getFormDomande().get(i).getId_domanda());
			
			theDomanda.setDomanda(formQuestionarioWrapper.getFormDomande().get(i).getDomanda());
			
			for(int j = 0; j < formQuestionarioWrapper.getFormDomande().get(i).getId_risposte().size(); j++) {
				
				Risposta theRisposta = questionarioService.findRispostaById(formQuestionarioWrapper.getFormDomande().get(i).getId_risposte().get(j));
				
				if (! formQuestionarioWrapper.getFormDomande().get(i).getTipo().equals("open") ) {
					theRisposta.setDesrisposta(formQuestionarioWrapper.getFormDomande().get(i).getRisposte().get(j));
				}
				else {
					theRisposta.setDesrisposta(null);
				}
				
				
				theRisposta.setScore(formQuestionarioWrapper.getFormDomande().get(i).getScores().get(j));
				
				theRisposta.setTipo(formQuestionarioWrapper.getFormDomande().get(i).getTipo());
				
				questionarioService.saveRisposta(theRisposta);
			}
			
			questionarioService.saveDomanda(theDomanda);
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping("/survey/edit/questions/fromOpen")
	@Transactional
	public String fromOpen(@RequestParam("questionId") int questionId, @RequestParam ("toType") String toType, HttpSession session) {
		
		Domanda theDomanda = questionarioService.findDomandaById(questionId);
		
		for (Risposta theRisposta : theDomanda.getRisposte()) {
			
			int aswerdId = theRisposta.getId_risposta();
		
			questionarioService.deleteRispostaById(aswerdId);
			
		}
		
		session.setAttribute("creazione", "true");
		
		session.setAttribute("fromOpen", "true");
		
		return "redirect:/surveys/create/answer?questionId=" + questionId + "&type=" + toType; 
	}
	
	@GetMapping("/survey/edit/questions/toOpen")
	@Transactional
	public String toOpen(@RequestParam ("questionId") int questionId) {
		
		Domanda theDomanda = questionarioService.findDomandaById(questionId);
		
		for (Risposta theRisposta : theDomanda.getRisposte()) {
			
			int aswerdId = theRisposta.getId_risposta();
		
			questionarioService.deleteRispostaById(aswerdId);
			
		}
		
		Risposta rispostaOpen = new Risposta();
		
		rispostaOpen.setId_questionario(theDomanda.getId_questionario());
		
		rispostaOpen.setId_domanda(theDomanda);
		
		rispostaOpen.setId_risposta(0);
		
		rispostaOpen.setDesrisposta(null);
		
		rispostaOpen.setScore(0);
		
		rispostaOpen.setTipo("open");
		
		questionarioService.saveRisposta(rispostaOpen);
		
		return "redirect:/surveys/edit/questions?surveyId=" + rispostaOpen.getId_questionario().getId_questionario();
	}
	
	@GetMapping("/surveys/delete/question")
	@Transactional
	public String deleteQuestion(@RequestParam ("Id") int questionId) {
		
		Domanda theDomanda = questionarioService.findDomandaById(questionId);
		
		int id_questionario = theDomanda.getId_questionario().getId_questionario();
		
		questionarioService.deleteDomanda(theDomanda);
		
		return "redirect:/surveys/edit/questions?surveyId=" + id_questionario;
	}
	
	// ELIMINAZIONE RISPOSTA
	
	@GetMapping("/surveys/delete/answer")
	@Transactional
	public String deleteAnswer(@RequestParam ("Id") int answerId) {
		
		Risposta theRisposta = questionarioService.findRispostaById(answerId);
		
		int id_questionario = theRisposta.getId_questionario().getId_questionario();
		
		questionarioService.deleteRisposta(theRisposta);
		
		return "redirect:/surveys/edit/questions?surveyId=" + id_questionario;
		
	}
	
	// CREAZIONE SOLO RISPOSTA
	
	@GetMapping("/surveys/create/answer")
	public String createAnswer(@RequestParam ("questionId") int questionId, @RequestParam ("type") String type, Model theModel) {
		
		Domanda theDomanda = questionarioService.findDomandaById(questionId);
		
		FormRisposta theFormRisposta = new FormRisposta();
		
		theFormRisposta.setId_questionario(theDomanda.getId_questionario().getId_questionario());
		
		theFormRisposta.setId_domanda(theDomanda.getId_domanda());
		
		theFormRisposta.setTipo(type);
		
		theModel.addAttribute("risposte", theFormRisposta);
		
		return "survey-create-answer";
	}
	
	@PostMapping("/surveys/create/answer/process")
	@Transactional
	public String processNewAnswer(@Valid @ModelAttribute("risposte") FormRisposta formRisposta,
			BindingResult theBindingResult, Model theModel, HttpSession session) {
		
		if (session.getAttribute("fromOpen") == null) {
			if (theBindingResult.hasErrors() || ( (! formRisposta.getTipo().equals("open")) && (formRisposta.getRisposte().size() < 1) ) )  {
				
				theModel.addAttribute("numrisposteerr", "");
				
				return "survey-create-answer";
			}
		}
		else {
			if (theBindingResult.hasErrors() || ( (! formRisposta.getTipo().equals("open")) && (formRisposta.getRisposte().size() < 2) ) )  {
				
				theModel.addAttribute("numrisposteerr", "");
				
				return "survey-create-answer";
			}
		}
		
		Domanda theDomanda = questionarioService.findDomandaById(formRisposta.getId_domanda());
		
		
		for (int i = 0; i < formRisposta.getRisposte().size(); i++) {
			
			Risposta theRisposta = new Risposta();
			
			theRisposta.setId_questionario(theDomanda.getId_questionario());
			
			theRisposta.setId_domanda(theDomanda);
			
			theRisposta.setId_risposta(formRisposta.getId_risposta());
			
			theRisposta.setDesrisposta(formRisposta.getRisposte().get(i));
			
			theRisposta.setTipo(formRisposta.getTipo());
			
			theRisposta.setScore(formRisposta.getScores().get(i));
			
			theDomanda.addRisposta(theRisposta);
		}
		
		questionarioService.saveDomanda(theDomanda);		
		
		return "redirect:/surveys/edit/questions?surveyId=" + formRisposta.getId_questionario();
	}
	
	// COMPILA QUESTIONARIO
	
	@GetMapping("/surveys/compile/survey")
	public String compileSurvey(@RequestParam("Id") int surveyId, Model theModel, HttpSession session) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = null;
		
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
		
		Questionario theQuestionario = questionarioService.findQuestionarioById(surveyId);
		
		// ACCESSO NEGATO SE FUORI TIMESLOT DELLA SESSIONE
		
		Date dataOggi = new Date();
		
		if(! (theQuestionario.getId_sessione().getInizio().before(dataOggi) && theQuestionario.getId_sessione().getFine().after(dataOggi))) {
			
			return "access-denied";
			
		}
		
		List<Domanda> domande = theQuestionario.getDomande();
		
		ReplyQuestionarioWrapper replyQuestionarioWrapper = new ReplyQuestionarioWrapper();
		
		replyQuestionarioWrapper.setId_questionario(theQuestionario.getId_questionario());
		
		List<ReplyDomanda> replyDomande = new ArrayList<>();
		
		int punteggioTot = 0;
		
		for(Domanda theDomanda : domande) {
		
			ReplyDomanda replyDomanda = new ReplyDomanda();
			
			replyDomanda.setId_questionario(theDomanda.getId_questionario().getId_questionario());
			
			replyDomanda.setId_domanda(theDomanda.getId_domanda());
			
			replyDomanda.setDomanda(theDomanda.getDomanda());
			
			replyDomanda.setNum(theDomanda.getRisposte().size());
			
			List<Integer> id_risposte = new ArrayList<>();
			
			List<Integer> scores = new ArrayList<>();
			
			List<String> risposte = new ArrayList<>();
			
			for(Risposta theRisposta : theDomanda.getRisposte()) {
				
				id_risposte.add(theRisposta.getId_risposta());
				
				scores.add(theRisposta.getScore());
				
				risposte.add(theRisposta.getDesrisposta());
				
			}
			
			if(theDomanda.getRisposte().size() > 0) {
				replyDomanda.setTipo(theDomanda.getRisposte().get(0).getTipo());
			}
			
			replyDomanda.setId_risposte(id_risposte);
			
			replyDomanda.setScores(scores);
			
			replyDomanda.setRisposte(risposte);
			
			
			List<RegistroRisposta> registroRisposte = questionarioService.findByDomandaAndUsername(theDomanda, userService.findByUserName(username));
			
			
			if(registroRisposte != null) {
				
				List<Integer> idrispostadata = new ArrayList<>();
				
				for(RegistroRisposta theRegistroRisposta : registroRisposte) {
					
					idrispostadata.add(theRegistroRisposta.getId_risposta_reg().getId_risposta());
					
					replyDomanda.setRispapertadata(theRegistroRisposta.getRispaperta());
					
					punteggioTot = punteggioTot + theRegistroRisposta.getPunteggio();
					
				}
				
				replyDomanda.setIdrispostadata(idrispostadata);
				
			}
						
			replyDomande.add(replyDomanda);
		
		}
		
		replyQuestionarioWrapper.setReplyDomande(replyDomande);
		
		replyQuestionarioWrapper.setPunteggioTot(punteggioTot);
		
//		System.out.println(replyQuestionarioWrapper);
		
		session.setAttribute("titoloQuestionario", theQuestionario.getTitolo());
				
		theModel.addAttribute("replyQuestionarioWrapper", replyQuestionarioWrapper);
		
		return "survey-compile-question";
	}
	
	@PostMapping("surveys/compile/questions/process")
	@Transactional
	public String processCompileSurvey(@Valid @ModelAttribute("replyQuestionarioWrapper") ReplyQuestionarioWrapper replyQuestionarioWrapper,
			BindingResult theBindingResult, Model theModel) {
		
//		System.out.println(replyQuestionarioWrapper);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = null;
		
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}
		
		User currentUser = userService.findByUserName(username);
		
		for(ReplyDomanda theReplyDomanda : replyQuestionarioWrapper.getReplyDomande()) {
			
			if(theReplyDomanda.getIdrispostadata() != null) {
				
				List<RegistroRisposta> registroRisposte = questionarioService.findByDomandaAndUsername(questionarioService.findDomandaById(theReplyDomanda.getId_domanda()), currentUser);
				
				for(RegistroRisposta theRegistroRisposta : registroRisposte) {
										
					questionarioService.deleteRegistroRisposta(theRegistroRisposta);
					
				}
				
				Questionario theQuestionario = questionarioService.findQuestionarioById(theReplyDomanda.getId_questionario());
				
				Domanda theDomanda = questionarioService.findDomandaById(theReplyDomanda.getId_domanda());
				
				for(int idrispostadata : theReplyDomanda.getIdrispostadata()) {
									
					RegistroRisposta saveRegistroRisposta = new RegistroRisposta();
					
					saveRegistroRisposta.setId_registro(0);
					
					saveRegistroRisposta.setId_questionario_reg(theQuestionario);
					
					saveRegistroRisposta.setId_domanda_reg(theDomanda);
					
					Risposta theRispostaData = questionarioService.findRispostaById(idrispostadata);
					
					saveRegistroRisposta.setId_risposta_reg(theRispostaData);
					
					saveRegistroRisposta.setPunteggio(theRispostaData.getScore());
					
					if(theReplyDomanda.getTipo().equals("open")) {
						
						saveRegistroRisposta.setRispaperta(theReplyDomanda.getRispapertadata());
						
					}
					
					saveRegistroRisposta.setUsername_reg(currentUser);
					
					saveRegistroRisposta.setDatacompilazione(new Date());
					
					questionarioService.saveRegistroRisposta(saveRegistroRisposta);				
				
				}
				
			}
			
		}
		
		return "survey-compile-question-success";
	}
	
}
