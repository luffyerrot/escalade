package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.projet.escalade.entities.User;
import fr.projet.escalade.service.BookingService;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	ToposService toposService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailGet(ModelMap model) {
	    String name = userService.auth().getName();
	    if(userService.authUser().getEmail().equals(name)) {
	    	model.addAttribute("users", userService.getById(userService.authUserId()));
	    	return new ModelAndView("user/detail", model);
	    } else {
	    	model.addAttribute("users", userService.findAll());
	    	return new ModelAndView("redirect:/", model);
	    }
	}

	@RequestMapping(value = "/toposRequest", method = RequestMethod.GET)
	public ModelAndView requestToposGet(ModelMap model) {
		model.addAttribute("bookings", bookingService.getByUserIdAndAcceptedNull(userService.authUser()));
	    return new ModelAndView("user/toposRequest", model);
	}
	
	@RequestMapping(value = "/toposAccepted", method = RequestMethod.GET)
	public ModelAndView requestAcceptedGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos, @RequestParam(name="idBooking", required = true) Long idBooking) {
		bookingService.changeAccepted(idBooking, true);
		toposService.changeRequest(idTopos);
		toposService.changeReserved(idTopos);
		model.addAttribute("bookings", bookingService.getByUserIdAndAcceptedNull(userService.authUser()));
	    return new ModelAndView("user/toposRequest", model);
	}
	
	@RequestMapping(value = "/toposRefused", method = RequestMethod.GET)
	public ModelAndView requestRefusedGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos, @RequestParam(name="idBooking", required = true) Long idBooking) {
		bookingService.deleteById(idBooking);
		toposService.changeRequest(idTopos);
	    return new ModelAndView("user/toposRequest", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model) {
		model.addAttribute("users", userService.getById(userService.authUserId()));
	    return new ModelAndView("user/update", model);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("user") User user, ModelMap model) {
		userService.updateUser(user.getId(), user.getUsername(), user.getEmail());
		model.addAttribute("users", user);
    	return new ModelAndView("user/detail", model);
	}
}
