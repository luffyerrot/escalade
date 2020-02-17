package fr.projet.escalade.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.entities.User;
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
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		try {
			model.addAttribute("sectors", sectorService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("sector/info", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView sectorcreateGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("users", userService.getById(id));
		model.addAttribute("topos", toposService.getByUserId(id));
	    return new ModelAndView("sector/create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView sectorcreatePost(@ModelAttribute("sector") Sector sector, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		User user = userService.getById(id);
		sector.setUser(user);
		sectorService.save(sector);
		try {
			model.addAttribute("sectors", sectorService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("sector/info", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model, @RequestParam(name="idSector", required = true) Long idSector) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("topos", toposService.getByUserId(id));
		model.addAttribute("sectors", sectorService.getById(idSector));
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("sector/update", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("sector") Sector sector, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		User user = userService.getById(id);
		sector.setUser(user);
		sectorService.save(sector);
		try {
			model.addAttribute("sectors", sectorService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("sector/info", model);
	}
}
