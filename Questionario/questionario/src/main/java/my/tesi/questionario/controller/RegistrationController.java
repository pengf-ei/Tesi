package my.tesi.questionario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.tesi.questionario.entity.FormUser;
import my.tesi.questionario.entity.User;
import my.tesi.questionario.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showForm")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("formUser", new FormUser());
		
		return "registration";
	}
	
	@PostMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("formUser") FormUser formUser, 
			BindingResult theBindingResult, 
			Model theModel, Authentication authentication) {
		
		
		 if (theBindingResult.hasErrors()) {
			  return "registration";
		 }
		
		 
		 User theUser = userService.findByUserName(formUser.getUserName());
		 if (theUser != null) {
			 
			 theModel.addAttribute("formUser", new FormUser());
			 theModel.addAttribute("registrationError", "Username già esistente.");
			 
			 return "registration";
		 }
		 
		 userService.save(formUser);
		
		 // se utente registrato da admin reindirizzo ad un altra pagina
		 try {
			if ( authentication.getAuthorities().toString().contains("ADMIN") ) {
				 
				 return "registration-success";
			 }
		} catch (Exception e) {
			
		}
		    	 
		 
		 theModel.addAttribute("registrationSuccess", "Utenza registrata.");
		 
		 return "login-bootstrap";
	}
}
