package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Comment;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	ToposService toposService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    Long id = userService.getIdByName(name);
	    User user = userService.getById(id);
	    if(user.getEmail().equals(name)) {
	    	model.addAttribute("users", userService.getById(id));
	    	return new ModelAndView("user/detail", model);
	    } else {
	    	model.addAttribute("users", userService.findAll());
	    	return new ModelAndView("redirect:/", model);
	    }
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("user/update", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("user") User user, ModelMap model) {
		user.setPassword(user.getPassword());
		userService.save(user);
		model.addAttribute("users", user);
    	return new ModelAndView("user/detail", model);
	}
}
