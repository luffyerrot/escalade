package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.service.SectorService;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
@RequestMapping(value = "/sector")
public class SectorController {

	@Autowired
	UserService userService;
	@Autowired
	SectorService sectorService;
	@Autowired
	ToposService toposService;
	
	/*
	 * affiche la page d'information des secteurs de l'utilisateur connecté.
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		if(sectorService.getByUserId(userService.authUser().getId()) != null) {
			model.addAttribute("sectors", sectorService.getByUserId(userService.authUser().getId()));
		}
	    return new ModelAndView("sector/info", model);
	}
	
	/*
	 * affiche la page de création des secteurs de l'utilisateur connecté.
	 * GET
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView sectorcreateGet(ModelMap model) {
		model.addAttribute("users", userService.getById(userService.authUser().getId()));
		model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
	    return new ModelAndView("sector/create", model);
	}

	/*
	 * permet de créer un secteur personnalisé
	 * affiche la page d"information des secteurs de l'utilisateur connecté.
	 * POST
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView sectorcreatePost(@ModelAttribute("sector") Sector sector, ModelMap model) {
		sectorService.create(sector, userService.authUser());
		if (sectorService.getByUserId(userService.authUser().getId()) != null) {
			model.addAttribute("sectors", sectorService.getByUserId(userService.authUser().getId()));
		}
	    return new ModelAndView("redirect:/sector/info", model);
	}

	/*
	 * affiche la page de modification des secteurs de l'utilisateur connecté.
	 * GET
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model, @RequestParam(name="idSector", required = true) Long idSector) {
		if(sectorService.asAcces(idSector)) {
			model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
			model.addAttribute("sectors", sectorService.getById(idSector));
			model.addAttribute("users", userService.getById(userService.authUser().getId()));
		    return new ModelAndView("sector/update", model);
		} else {
			return new ModelAndView("redirect:/", model);
		}
	}

	/*
	 * permet de modification un secteur personnalisé
	 * affiche la page d"information des secteurs de l'utilisateur connecté.
	 * POST
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("sector") Sector sector, ModelMap model) {
		sectorService.updateSector(sector.getId(), sector.getName(), sector.getGlobal_length(), sector.getType(), sector.getTopos());
		if(sectorService.getByUserId(userService.authUser().getId()) != null) {
			model.addAttribute("sectors", sectorService.getByUserId(userService.authUser().getId()));
		}
	    return new ModelAndView("redirect:/sector/info", model);
	}
}
