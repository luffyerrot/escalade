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

import fr.projet.escalade.model.Person;
import fr.projet.escalade.repository.PersonRepository;

@Controller
public class PagesController{
	
	@Autowired
	PersonRepository personrepo;
	
	@GetMapping("/")
	public ModelAndView home(ModelMap model) {
		model.addAttribute("person", personrepo.findAll());
	    return new ModelAndView("home", model);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView home(@ModelAttribute("person") Person person, ModelMap model) {
		model.addAttribute("userForm", person);
	    return new ModelAndView("home", model);
	}
	
	@RequestMapping(value = "/creation", method = RequestMethod.GET)
	public ModelAndView crea(ModelMap model) {
	    return new ModelAndView("creation", model);
	}
	
	@RequestMapping(value = "/creation", method = RequestMethod.POST)
	public ModelAndView crea(@ModelAttribute("persons") Person person, ModelMap model) {
		personrepo.save(person);
		model.addAttribute("persons", person);
	    return new ModelAndView("creation", model);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelMap model, @RequestParam(value="id",required=false) Long id) {
		model.addAttribute("userForm", this.personrepo.findById(id));
	    return new ModelAndView("detail", model);
	}
	
	@RequestMapping(value = "/formulaire", method = RequestMethod.GET)
	public ModelAndView form(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("userForm", this.personrepo.findById(id));
	    return new ModelAndView("form", model);
	}
	
	@RequestMapping(value = "/formulaire", method = RequestMethod.POST)
	public ModelAndView form(@ModelAttribute("person") Person person, ModelMap model, @RequestParam(value="id",required=true) Long id) {
		//this.person.set(id, person);
		model.addAttribute("userForm", person);
	    return new ModelAndView("form", model);
	}
}