package fr.projet.escalade.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.entities.Way;
import fr.projet.escalade.repositories.WayRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class WayService extends CustomUserDetailsService{

	@Autowired
	private WayRepository wayRepository;

	Logger logger = LoggerFactory.getLogger(ToposService.class);
	
	public Way getById(Long id) {
		this.logger.debug("getById Call = " + id);
		Way way = wayRepository.findById(id).get();
		this.logger.debug("getById Return = " + way);
		return way;
	}
	
	public List<Way> getByUserId(Long id) {
		this.logger.debug("getByUserId Call = " + id);
		List<Way> way = wayRepository.findByUserId(id);
		this.logger.debug("getByUserId Return = " + way);
		return way;
	}
	
	public List<Way> getBySectorId(Long id) {
		this.logger.debug("getBySectorId Call = " + id);
		List<Way> way = wayRepository.findBySectorId(id);
		this.logger.debug("getBySectorId Return = " + way);
		return way;
	}
	
	public Way save(Way way) {
		this.logger.debug("save Call = " + way);
		Way wayreturn = wayRepository.save(way);
		this.logger.debug("save Return = " + wayreturn);
		return wayreturn;
	}
	
	public void create(Way way, User user) {
		this.logger.debug("create Call = " + way + " " + user);
		way.setUser(user);
		save(way);
	}
	
	public void updateWay(Long idWay, Integer length, Integer difficulty, String description, Sector sector) {
		this.logger.debug("updateWay Call = " + idWay + " " + length + " " + difficulty + " " + description + " " + sector);
		wayRepository.updateWay(idWay, length, difficulty, description, sector);
	}
}
