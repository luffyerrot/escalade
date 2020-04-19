package fr.projet.escalade.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.projet.escalade.entities.User;
import fr.projet.escalade.service.BookingService;
import fr.projet.escalade.service.CommentService;
import fr.projet.escalade.service.RoleService;
import fr.projet.escalade.service.SectorService;
import fr.projet.escalade.service.ToposService;
import fr.projet.escalade.service.UserService;
import fr.projet.escalade.service.WayService;

@Controller
public class PagesController{
	
	@Autowired
	UserService userService;
	@Autowired
	ToposService toposService;
	@Autowired
	SectorService sectorService;
	@Autowired
	WayService wayService;
	@Autowired
	RoleService roleService;
	@Autowired
	CommentService commentService;
	@Autowired
	BookingService bookingService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public ModelAndView home(ModelMap model) {
		model.addAttribute("bookings", bookingService.getByToposUserIdAndAccepted(userService.authUser()));
	    return new ModelAndView("home", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(ModelMap model) {
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("user") User user, ModelMap model) {
		userService.create(user);
		model.addAttribute("users", user);
	    return new ModelAndView("create", model);
	}
	
	@RequestMapping(value = "/toposAll", method = RequestMethod.GET)
	public ModelAndView infoToposGet(ModelMap model, @RequestParam(name="username", required = false) String username, @RequestParam(name="toposname", required = false) String toposname) {
		if((username != null && toposname != null) && (!username.isEmpty() || !toposname.isEmpty())) {
			model.addAttribute("topos", toposService.getByUserUsernameOrName(username, toposname));
		} else {
			model.addAttribute("topos", toposService.getAllPublished());
		}
		model.addAttribute("username", username);
		model.addAttribute("toposname", toposname);
	    return new ModelAndView("toposAll", model);
	}
	
	@RequestMapping(value = "/toposReserved", method = RequestMethod.GET)
	public ModelAndView toposReserved(ModelMap model, @RequestParam(name="username", required = false) String username, @RequestParam(name="toposname", required = false) String toposname, 
			@RequestParam(name="toposreserved", required = false) Boolean toposreserved, @RequestParam(name="idTopos", required = false) Long idTopos) {
		toposService.changeRequest(idTopos);
		if (!((toposreserved == null) && (idTopos == null))) {
			bookingService.sendBookingRequest(idTopos);
		}
		if((username != null && toposname != null) && (!username.isEmpty() || !toposname.isEmpty())) {
			model.addAttribute("topos", toposService.getByUserUsernameOrName(username, toposname));
		} else {
			model.addAttribute("topos", toposService.getAllPublished());
		}
	    return new ModelAndView("redirect:/toposAll", model);
	}
	
	@RequestMapping(value = "/toposInfo", method = RequestMethod.GET)
	public ModelAndView detailToposGet(ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos) {
		model.addAttribute("comments", commentService.getByToposId(idTopos));
		model.addAttribute("topos", toposService.getById(idTopos));
		model.addAttribute("sectors", sectorService.getByToposId(idTopos));
	    return new ModelAndView("toposInfo", model);
	}
	
	@RequestMapping(value = "/toposInfo", method = RequestMethod.POST)
	public ModelAndView detailToposPost(@ModelAttribute("comments") Comment comment, ModelMap model, @RequestParam(name="idTopos", required = true) Long idTopos) {
		commentService.create(comment, idTopos);
		model.addAttribute("comments", commentService.getByToposId(idTopos));
		model.addAttribute("topos", toposService.getById(idTopos));
		model.addAttribute("sectors", sectorService.getByToposId(idTopos));
	    return new ModelAndView("toposInfo", model);
	}
	
	@RequestMapping(value = "/sectorInfo", method = RequestMethod.GET)
	public ModelAndView detailSectorGet(ModelMap model, @RequestParam(name="idSector", required = true) Long idSector) {
		model.addAttribute("sectors", sectorService.getById(idSector));
		model.addAttribute("ways", wayService.getBySectorId(idSector));
	    return new ModelAndView("sectorInfo", model);
	}
	
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	public ModelAndView deleteComment(ModelMap model, @RequestParam(name="idComment", required = true) Long idComment, @RequestParam(name="idTopos", required = true) Long idTopos) {
		commentService.deleteById(idComment);
		model.addAttribute("comments", commentService.getByToposId(idTopos));
		model.addAttribute("topos", toposService.getById(idTopos));
	    return new ModelAndView("redirect:/toposInfo", model);
	}
	
	@RequestMapping(value = "/modifComment", method = RequestMethod.GET)
	public ModelAndView modifCommentGet(ModelMap model, @RequestParam(name="idComment", required = true) Long idComment) {
		model.addAttribute("comments", commentService.getById(idComment));
	    return new ModelAndView("modifComment", model);
	}
	
	@RequestMapping(value = "/modifComment", method = RequestMethod.POST)
	public ModelAndView modifCommentPost(@ModelAttribute("commentObj") Comment comment, ModelMap model) {
		commentService.update(comment.getId(), comment.getComment());
		model.addAttribute("comments", commentService.getById(comment.getId()));
		model.addAttribute("topos", toposService.getById(comment.getTopos().getId()));
	    return new ModelAndView("toposInfo", model);
	}
}