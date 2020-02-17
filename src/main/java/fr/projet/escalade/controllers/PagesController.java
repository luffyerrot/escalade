package fr.projet.escalade.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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

import fr.projet.escalade.entities.Comment;
import fr.projet.escalade.entities.Role;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.service.CommentService;
import fr.projet.escalade.service.RoleService;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
public class PagesController{
	
	@Autowired
	UserService userService;
	@Autowired
	ToposService toposService;
	@Autowired
	RoleService roleService;
	@Autowired
	CommentService commentService;
	
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
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") User user, ModelMap model) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = roleService.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(role));
		userService.save(user);
		model.addAttribute("users", user);
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/toposAll", method = RequestMethod.GET)
	public ModelAndView infoTopos(ModelMap model) {
		model.addAttribute("topos", toposService.getAllOfficial(true));
	    return new ModelAndView("toposAll", model);
	}
	
	@RequestMapping(value = "/toposInfo", method = RequestMethod.GET)
	public ModelAndView detailToposGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos) {
		model.addAttribute("comments", commentService.getByToposId(idTopos));
		model.addAttribute("topos", toposService.getById(idTopos));
	    return new ModelAndView("toposInfo", model);
	}
	
	@RequestMapping(value = "/toposInfo", method = RequestMethod.POST)
	public ModelAndView detailToposPost(@ModelAttribute("comments") Comment comment, ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		Long id = userService.getIdByName(auth.getName());
		User user = userService.getById(id);
		Topos topos = toposService.getById(idTopos);
		comment.setTopos(topos);
		comment.setUser(user);
		comment.setRelease_date(format.format(date));
		commentService.save(comment);
		model.addAttribute("comments", commentService.getByToposId(idTopos));
		model.addAttribute("topos", toposService.getById(idTopos));
	    return new ModelAndView("toposInfo", model);
	}
	
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	public ModelAndView deleteComment(ModelMap model, @RequestParam(name="idComment", required = true) Long idComment, @RequestParam(name="idTopos", required = true) Long idTopos) {
		commentService.deleteById(idComment);
		model.addAttribute("comments", commentService.getByToposId(idTopos));
		model.addAttribute("topos", toposService.getById(idTopos));
	    return new ModelAndView("toposInfo", model);
	}
	
	@RequestMapping(value = "/modifComment", method = RequestMethod.GET)
	public ModelAndView modifComment(ModelMap model, @RequestParam(name="idComment", required = true) Long idComment) {
		model.addAttribute("comments", commentService.getById(idComment));
	    return new ModelAndView("modifComment", model);
	}
	
	@RequestMapping(value = "/modifComment", method = RequestMethod.POST)
	public ModelAndView modifComment(@ModelAttribute("comments") Comment comment, ModelMap model) {
		commentService.save(comment);
		model.addAttribute("comments", commentService.getById(comment.getId()));
		model.addAttribute("topos", toposService.getById(comment.getTopos().getId()));
	    return new ModelAndView("topos", model);
	}
}