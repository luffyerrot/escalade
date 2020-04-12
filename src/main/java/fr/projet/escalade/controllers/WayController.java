package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		if(wayService.getByUserId(userService.authUserId()) != null) {
			model.addAttribute("ways", wayService.getByUserId(userService.authUserId()));
		}
	    return new ModelAndView("way/info", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createGet(ModelMap model) {
		model.addAttribute("users", userService.getById(userService.authUserId()));
		model.addAttribute("sectors", sectorService.getByUserId(userService.authUserId()));
	    return new ModelAndView("way/create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("way") Way way, ModelMap model) {
		wayService.create(way, userService.authUser());
		if(wayService.getByUserId(userService.authUserId()) != null) {
			model.addAttribute("ways", wayService.getByUserId(userService.authUserId()));
		}
	    return new ModelAndView("way/info", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model, @RequestParam(name="idWay", required = true) Long idWay) {
		model.addAttribute("ways", wayService.getById(idWay));
		model.addAttribute("users", userService.getById(userService.authUserId()));
		model.addAttribute("sectors", sectorService.getByUserId(userService.authUserId()));
	    return new ModelAndView("way/update", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("way") Way way, ModelMap model) {
		wayService.updateWay(way.getId(), way.getLength(), way.getDifficulty(), way.getDescription(), way.getSector());
		if(wayService.getByUserId(userService.authUserId()) != null) {
			model.addAttribute("ways", wayService.getByUserId(userService.authUserId()));
		}
	    return new ModelAndView("way/info", model);
	}
}
