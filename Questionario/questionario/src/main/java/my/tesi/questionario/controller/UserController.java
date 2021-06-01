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
	
}
