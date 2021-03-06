package fr.projet.escalade.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.ToposRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class ToposService extends CustomUserDetailsService{

	@Autowired
	private ToposRepository toposRepository;
	
	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(ToposService.class);
	
	public Topos getById(Long id) {
		this.logger.info("getById Call = " + id);
		Topos topos =  toposRepository.findById(id).get();
		this.logger.info("getById Return = " + topos);
		return topos;
	}
	
	public List<Topos> getByUserId(Long id) {
		this.logger.info("getByUserId Call = " + id);
		List<Topos> topos = toposRepository.findByUserId(id);
		this.logger.info("getByUserId Return = " + topos);
		return topos;
	}
	
	
	public Topos save(Topos topos) {
		this.logger.info("save Call = " + topos);
		Topos toposreturn = toposRepository.save(topos);
		this.logger.info("save Return = " + toposreturn);
		return toposRepository.save(toposreturn);
	}
	
	public List<Topos> getAll() {
		List<Topos> topos = toposRepository.findAll();
		this.logger.info("getAll Return = " + topos);
		return topos;
	}
	
	public List<Topos> getAllPublished() {
		List<Topos> topos = toposRepository.findAllByPublished(true);
		this.logger.info("getAllPublished Return = " + topos);
		return topos;
	}
	
	public List<Topos> getByUserUsernameOrName(String username, String toposname) {
		this.logger.info("getByUserUsernameOrName Call = " + username + " " + toposname);
		List<Topos> topos = toposRepository.findByUserUsernameOrNameAndPublished(username, toposname, true);
		this.logger.info("getByUserUsernameOrName Return = " + topos);
		return topos;
	}
	
	public Topos getByToposIdAndReserved(Long id) {
		this.logger.info("getByToposIdAndReserved Call = " + id);
		Topos topos = toposRepository.findByToposIdAndReserved(id);
		this.logger.info("getByToposIdAndReserved Return = " + topos);
		return topos;
	}
	
	public void changeOfficial(Long idTopos) {
		this.logger.info("changeOfficial Call = " + idTopos);
		if(this.getById(idTopos).getOfficial().equals(true)) {
			toposRepository.changeOfficial(idTopos, false);
		} else {
			toposRepository.changeOfficial(idTopos, true);
		}
	}
	
	public void changeRequest(Long idTopos) {
		this.logger.info("changeRequest Call = " + idTopos);
		if(this.getById(idTopos).getRequest().equals(true)) {
			toposRepository.changeRequest(idTopos, false);
		} else {
			toposRepository.changeRequest(idTopos, true);
		}
	}
	
	public void changePublished(Long idTopos) {
		this.logger.info("changePublished Call = " + idTopos);
		if(this.getById(idTopos).getPublished().equals(true)) {
			toposRepository.changePublished(idTopos, false);
		} else {
			toposRepository.changePublished(idTopos, true);
		}
	}
	
	public void create(Topos topos, User user) {
		this.logger.info("create Call = " + topos + " " + user);
		Date date = new Date();
		this.logger.info("create New Date = " + date);
		topos.setDate(date);
		topos.setUser(user);
		save(topos);
	}
	
	public void update(Long idTopos, String name, String description, String place) {
		this.logger.info("update Call = " + idTopos + " " + name + " " + description + " " + place);
		toposRepository.updateTopos(idTopos, name, description, place);
	}
	
	public void changeReserved(Long idTopos) {
		this.logger.info("changeReserved Call = " + idTopos);
		if(this.getById(idTopos).getReserved().equals(true)) {
			toposRepository.changeReserved(idTopos, false);
		} else {
			toposRepository.changeReserved(idTopos, true);
		}
	}
	
	public Boolean checkAcces(Long idTopos) {
		if (getByUserId(userService.authUser().getId()).contains(getById(idTopos))) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean toposNull() {
		if (getByUserId(userService.authUser().getId()) != null) {
			return true;
		} else {
			return false;
		}
	}
}
