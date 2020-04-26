package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.service.ToposService;

@Controller
@RequestMapping(value = "/admin/topos")
public class AdminToposController {

	@Autowired
	ToposService toposService;

	/**
	 * affiche la page d'information des topos pour l'officialisation.
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		if (toposService.getAll() != null) {
			model.addAttribute("topos", toposService.getAll());
		}
	    return new ModelAndView("admin/topos/info", model);
	}
	
	/**
	 * permet d'officialiser des topos.
	 */
	@RequestMapping(value = "/toposOfficial", method = RequestMethod.GET)
	public ModelAndView requestAcceptedGet(ModelMap model, @RequestParam(name="idTopos", required = false) Long idTopos) {
		if(idTopos != null) {
			toposService.changeOfficial(idTopos);
		}
		if (toposService.getAll() != null) {
			model.addAttribute("topos", toposService.getAll());
		}
	    return new ModelAndView("redirect:/admin/topos/info", model);
	}
}
