package fr.projet.escalade.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.service.ToposService;

@Controller
@RequestMapping(value = "/admin/topos")
public class AdminToposController {

	@Autowired
	ToposService toposService;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		try {
			model.addAttribute("topos", toposService.getAll());
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("admin/topos/info", model);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public ModelAndView infoPost(@ModelAttribute("topos") Topos topos, ModelMap model) {
		topos.setUser(toposService.getById(topos.getId()).getUser());
		toposService.save(topos);
		try {
			model.addAttribute("topos", toposService.getAll());
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("admin/topos/info", model);
	}
}
