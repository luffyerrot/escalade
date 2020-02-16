package fr.projet.escalade.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Role;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.service.RoleService;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
public class PagesController{
	
	@Autowired
	UserService userService;
	@Autowired
	ToposService toposService;
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public ModelAndView home(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			Long id = userService.getIdByName(auth.getName());
			model.addAttribute("id", id);
		}
		model.addAttribute("users", userService.findAll());
	    return new ModelAndView("home", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") User user, ModelMap model) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = roleService.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(role));
		userService.save(user);
		model.addAttribute("users", user);
	    return new ModelAndView("create", model);
	}
}