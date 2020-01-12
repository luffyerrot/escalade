package fr.projet.escalade.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.UserRepository;

@Controller
public class PagesController{
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/")
	public ModelAndView home(ModelMap model) {
		model.addAttribute("users", userRepo.findAll());
	    return new ModelAndView("home", model);
	}
	
	@GetMapping("admin/")
	public ModelAndView adminHome(ModelMap model) {
	    return new ModelAndView("adminHome", model);
	}
	
	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("users", userRepo.findById(id).get());
	    return new ModelAndView("detail", model);
	}
	
	@RequestMapping(value = "/user/detail", method = RequestMethod.POST)
	public ModelAndView detailp(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("users", userRepo.findById(id).get());
	    return new ModelAndView("detail", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") User user, ModelMap model) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		model.addAttribute("users", user);
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public ModelAndView update(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("users", userRepo.findById(id).get());
	    return new ModelAndView("update", model);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("user") User user, ModelMap model, @RequestParam(value="id",required=true) Long id) {
		userRepo.save(user);
		model.addAttribute("users", userRepo.findById(id).get());
	    return new ModelAndView("update", model);
	}
}