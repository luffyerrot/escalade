package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
@RequestMapping(value = "/topos")
public class ToposController {

	@Autowired
	UserService userService;
	@Autowired
	ToposService toposService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createGet(ModelMap model) {
		model.addAttribute("users", userService.getById(userService.authUser().getId()));
	    return new ModelAndView("topos/create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("topos") Topos topos, ModelMap model) {
		toposService.create(topos, userService.authUser());
		if (!toposService.getByUserId(userService.authUser().getId()).isEmpty()) {
			model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
		}
	    return new ModelAndView("redirect:/topos/info", model);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView infoGet(ModelMap model) {
		if (!toposService.getByUserId(userService.authUser().getId()).isEmpty()) {
			model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
		}
	    return new ModelAndView("topos/info", model);
	}
	
	@RequestMapping(value = "/official", method = RequestMethod.GET)
	public ModelAndView officialGet(ModelMap model, @RequestParam(name="officialvalue", required = false) Boolean official, @RequestParam(name="idTopos", required = false) Long idTopos) {
		if(toposService.asAcces(idTopos)) {
			if (official != null) {
				toposService.changeOfficial(idTopos);
			}
			if (toposService.getByUserId(userService.authUser().getId()) == null) {
				model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
			}
		    return new ModelAndView("redirect:/topos/info", model);
		} else {
			return new ModelAndView("redirect:/", model);
		}
	}
	
	@RequestMapping(value = "/published", method = RequestMethod.GET)
	public ModelAndView publishedGet(ModelMap model, @RequestParam(name="publishedvalue", required = false) Boolean published, @RequestParam(name="idTopos", required = false) Long idTopos) {
		if(toposService.asAcces(idTopos)) {
			if (published != null) {
				toposService.changePublished(idTopos);
			}
			if (toposService.toposNull()) {
				model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
			}
		    return new ModelAndView("redirect:/topos/info", model);
		} else {
			return new ModelAndView("redirect:/", model);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos) {
		if(toposService.asAcces(idTopos)) {
			model.addAttribute("topos", toposService.getById(idTopos));
			model.addAttribute("users", userService.getById(userService.authUser().getId()));
		    return new ModelAndView("topos/update", model);
		} else {
			return new ModelAndView("redirect:/", model);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("topos") Topos topos, ModelMap model) {
		toposService.update(topos.getId(), topos.getName(), topos.getDescription(), topos.getPlace());
		if (toposService.toposNull()) {
			model.addAttribute("topos", toposService.getByUserId(userService.authUser().getId()));
		}
	    return new ModelAndView("topos/info", model);
	}
}
