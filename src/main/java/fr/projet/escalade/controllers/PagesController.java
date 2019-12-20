package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.models.Person;
import fr.projet.escalade.repositories.PersonRepository;

@Controller
public class PagesController{
	
	@Autowired
	PersonRepository personrepo;
	
	@GetMapping("/")
	public ModelAndView home(ModelMap model) {
		model.addAttribute("persons", personrepo.findAll());
	    return new ModelAndView("home", model);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("persons", personrepo.findById(id).get());
	    return new ModelAndView("detail", model);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView detailp(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("persons", personrepo.findById(id).get());
	    return new ModelAndView("detail", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("persons") Person person, ModelMap model) {
		personrepo.save(person);
		model.addAttribute("persons", person);
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("persons", personrepo.findById(id).get());
	    return new ModelAndView("update", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("person") Person person, ModelMap model, @RequestParam(value="id",required=true) Long id) {
		personrepo.save(person);
		model.addAttribute("persons", personrepo.findById(id).get());
	    return new ModelAndView("update", model);
	}
}