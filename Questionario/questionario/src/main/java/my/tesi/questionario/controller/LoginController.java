package my.tesi.questionario.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Authentication authentication) {
		
		// un utente già loggato viene reindirizzato verso home
		try {
			if(authentication.isAuthenticated()) {
				return "redirect:/";
			}
		} catch (Exception e) {
			
		}
		
		 return "login-bootstrap";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}

}
