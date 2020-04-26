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
	
	/**
	 * affiche la page d'information de l'utilisateur connecté.
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detailGet(ModelMap model) {
	    String name = userService.authUser().getEmail();
	    if(userService.authUser().getEmail().equals(name)) {
	    	model.addAttribute("users", userService.getById(userService.authUser().getId()));
	    	return new ModelAndView("user/detail", model);
	    } else {
	    	model.addAttribute("users", userService.findAll());
	    	return new ModelAndView("redirect:/", model);
	    }
	}

	/**
	 * affiche la page des requêtes de réservation.
	 */
	@RequestMapping(value = "/toposRequest", method = RequestMethod.GET)
	public ModelAndView requestToposGet(ModelMap model) {
		model.addAttribute("bookings", bookingService.getByUserAndAcceptedNull(userService.authUser()));
	    return new ModelAndView("user/toposRequest", model);
	}
	
	/**
	 * accepte la réservation.
	 */
	@RequestMapping(value = "/toposAccepted", method = RequestMethod.GET)
	public ModelAndView requestAcceptedGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos, @RequestParam(name="idBooking", required = true) Long idBooking) {
		if(toposService.checkAcces(idTopos)) {
			bookingService.changeAccepted(idBooking, true);
			toposService.changeRequest(idTopos);
			toposService.changeReserved(idTopos);
			model.addAttribute("bookings", bookingService.getByUserAndAcceptedNull(userService.authUser()));
		}
	    return new ModelAndView("user/toposRequest", model);
	}
	
	/**
	 * refuse la réservation.
	 */
	@RequestMapping(value = "/toposRefused", method = RequestMethod.GET)
	public ModelAndView requestRefusedGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos, @RequestParam(name="idBooking", required = true) Long idBooking) {
		if(toposService.checkAcces(idTopos)) {
			bookingService.changeAccepted(idBooking, false);
			toposService.changeRequest(idTopos);
		}
	    return new ModelAndView("user/toposRequest", model);
	}
	


	/**
	 * affiche la page de modification des données de l'utilisateur connecté.
	 * GET
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateGet(ModelMap model) {
		model.addAttribute("users", userService.getById(userService.authUser().getId()));
	    return new ModelAndView("user/update", model);
	}
	
	/**
	 * permet de modifier les données de l'utilisateur connecté
	 * affiche la page de modification des données de l'utilisateur connecté.
	 * POST
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePost(@ModelAttribute("user") User user, ModelMap model) {
		userService.updateUser(user.getId(), user.getUsername(), user.getEmail());
		model.addAttribute("users", user);
    	return new ModelAndView("user/detail", model);
	}
}
