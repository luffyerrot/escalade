package fr.projet.escalade.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.Role;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.RoleRepository;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
public class PagesController{
	
	@Autowired
	UserService userService = new UserService();
	@Autowired
	ToposService toposService = new ToposService();
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public ModelAndView home(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			Long id = userService.getIdByName(auth.getName());
			model.addAttribute("id", id);
		}
		model.addAttribute("users", userService.findAll());
	    return new ModelAndView("home", model);
	}
	
	@GetMapping("admin/")
	public ModelAndView adminHome(ModelMap model) {
	    return new ModelAndView("adminHome", model);
	}
	
	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
	    User user = userService.getById(id);
	    if(user.getEmail().equals(name)) {
	    	model.addAttribute("users", user);
	    	return new ModelAndView("detail", model);
	    } else {
	    	model.addAttribute("users", userService.findAll());
	    	return new ModelAndView("redirect:/", model);
	    }
	}
	
	@RequestMapping(value = "/user/detail", method = RequestMethod.POST)
	public ModelAndView detailp(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("detail", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") User user, ModelMap model) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = roleRepo.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(role));
		userService.save(user);
		model.addAttribute("users", user);
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public ModelAndView update(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("update", model);
	}
	
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("user") User user, ModelMap model, @RequestParam(value="id",required=true) Long id) {
		userService.save(user);
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("update", model);
	}
	
	@RequestMapping(value = "/user/topos", method = RequestMethod.GET)
	public ModelAndView topos(ModelMap model, @RequestParam(value="id",required=true) Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id2 = userService.getIdByName(auth.getName());
		try {
			model.addAttribute("topos", toposService.getByUserId(id));
		}catch(NoSuchElementException e) {
		}
		model.addAttribute("id", id2);
		model.addAttribute("users", userService.getById(id));
	    return new ModelAndView("topos", model);
	}
	
	@RequestMapping(value = "/user/topos", method = RequestMethod.POST)
	public ModelAndView topos(@ModelAttribute("topos") Topos topos, @ModelAttribute("user") User user, ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long id = userService.getIdByName(auth.getName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		model.addAttribute("id", id);
		topos.setUser(user);
		topos.setDate(format.format(date));;
		toposService.save(topos);
		model.addAttribute("topos", toposService.getByUserId(id));
	    return new ModelAndView("topos", model);
	}
}