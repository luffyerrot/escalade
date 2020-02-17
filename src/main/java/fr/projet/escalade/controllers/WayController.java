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
import fr.projet.escalade.entities.Way;
import fr.projet.escalade.service.SectorService;
import fr.projet.escalade.service.UserService;
import fr.projet.escalade.service.WayService;

@Controller
@RequestMapping(value = "/way")
public class WayController {
	
	@Autowired
	UserService userService;
	@Autowired
	SectorService sectorService;
	@Autowired
	WayService wayService;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		try {
			model.addAttribute("ways", wayService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("way/info", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createGet(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("users", userService.getById(id));
		model.addAttribute("sectors", sectorService.getByUserId(id));
	    return new ModelAndView("way/create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("way") Way way, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		User user = userService.getById(id);
		way.setUser(user);
		wayService.save(way);
		try {
			model.addAttribute("ways", wayService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("way/info", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model, @RequestParam(name="idWay", required = true) Long idWay) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		model.addAttribute("ways", wayService.getById(idWay));
		model.addAttribute("users", userService.getById(id));
		model.addAttribute("sectors", sectorService.getByUserId(id));
	    return new ModelAndView("way/update", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("way") Way way, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		User user = userService.getById(id);
		way.setUser(user);
		wayService.save(way);
		try {
			model.addAttribute("ways", wayService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
	    return new ModelAndView("way/info", model);
	}
}
