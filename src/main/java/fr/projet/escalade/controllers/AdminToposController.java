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
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(@RequestParam(name="idTopos", required = false) Long idTopos, ModelMap model) {
			if(idTopos != null) {
				toposService.changeOfficial(idTopos);
			}
			if (toposService.getAll() != null) {
				model.addAttribute("topos", toposService.getAll());
			}
	    return new ModelAndView("admin/topos/info", model);
	}
}
