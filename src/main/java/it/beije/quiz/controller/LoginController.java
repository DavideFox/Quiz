package it.beije.quiz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.quiz.model.Utente;
import it.beije.quiz.service.UtenteService;

@Controller
public class LoginController {
	
	 @Autowired
	 private UtenteService userService;

	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
			
		return "login";
	}
	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		String submittedEmail = request.getParameter("email");
		String submittedPassword = request.getParameter("password");
		HttpSession session = request.getSession();
		Utente logged = userService.checkLogin(submittedEmail, submittedPassword);
		if(logged!=null) {
			session.setAttribute("auth", true);
			//return QuizController.init(request, model);
			return "index";
		} else {
			model.addAttribute("errorLogin", "error: incorrect email or password submitted!");
			return "login";
		}

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, Model model) {
		String newNome = request.getParameter("nome");
		String newCognome = request.getParameter("cognome");
		String newEmail = request.getParameter("email");
		String newPassword = request.getParameter("password");
		Utente newUser = new Utente();
		newUser.setNome(newNome);
		newUser.setCognome(newCognome);
		newUser.setEmail(newEmail);
		newUser.setPassword(newPassword);
		Utente registered = userService.register(newUser);
		HttpSession session = request.getSession();
		if(registered!=null) {
			session.setAttribute("auth", true);
			return "index";
		} else {
			model.addAttribute("errorRegister", "error: email alredy in use!");
			return "register";
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model) {
		request.getSession().invalidate();
		return "login";		
	}
}
