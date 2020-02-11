package fr.projet.escalade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@GetMapping("/")
	public ModelAndView adminHome(ModelMap model) {
	    return new ModelAndView("admin/home", model);
	}
}
