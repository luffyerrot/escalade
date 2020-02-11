package fr.projet.escalade.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
@RequestMapping(value = "/topos")
public class ToposController {

	@Autowired
	UserService userService = new UserService();
	@Autowired
	ToposService toposService = new ToposService();
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("topos/create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("topos") Topos topos, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		User user = userService.getById(id);
		topos.setUser(user);
		topos.setDate(format.format(date));
		toposService.save(topos);
	    return new ModelAndView("topos/create", model);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		try {
			model.addAttribute("topos", toposService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("topos/info", model);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public ModelAndView infoPost(@ModelAttribute("topos") Topos topos, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("topos/info", model);
	}
}
