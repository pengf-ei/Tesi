package my.tesi.questionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.tesi.questionario.entity.User;
import my.tesi.questionario.service.UserService;

@Controller
@RequestMapping("/enable")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users-list")
	public String showForm(Model theModel) {
		
		List<User> usersNotEnabled = userService.findNotEnabled();
		
		
		theModel.addAttribute("usersNotEnabled", usersNotEnabled);
		
		return "users-not-enabled-list";
		
	}
	
	@GetMapping("/users-list/active")
	public String showForm(@RequestParam("userName") String username) {
		
		userService.setUserEnabled(username);		
		
		return "redirect:/enable/users-list";
		
	}
	
	/*
	@PostMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("formUser") FormUser formUser, 
			BindingResult theBindingResult, 
			Model theModel, Authentication authentication) {
		
		
		 if (theBindingResult.hasErrors()) {
			  return "registration-bootstrap";
		 }
		
		 
		 User theUser = userService.findByUserName(formUser.getUserName());
		 if (theUser != null) {
			 
			 theModel.addAttribute("formUser", new FormUser());
			 theModel.addAttribute("registrationError", "Username già esistente.");
			 
			 return "registration-bootstrap";
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
	*/
}
