package fr.projet.escalade.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.SectorRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class SectorService extends CustomUserDetailsService{

	@Autowired
	private UserService userService;
	
	@Autowired
	private SectorRepository sectorRepository;
	
	Logger logger = LoggerFactory.getLogger(SectorService.class);
	
	public Sector getById(Long id) {
		this.logger.info("getById Call = " + id);
		Sector sector = sectorRepository.findById(id).get();
		this.logger.info("getById Return = " + sector);
		return sector;
	}
	
	public List<Sector> getByUserId(Long id) {
		this.logger.info("getByUserId Call = " + id);
		List<Sector> sectors = sectorRepository.findByUserId(id);
		this.logger.info("getByUserId Return = " + sectors);
		return sectors;
	}
	
	public List<Sector> getByToposId(Long id) {
		this.logger.info("getByToposId Call = " + id);
		List<Sector> sectors = sectorRepository.findByToposId(id);
		this.logger.info("getByToposId Return = " + sectors);
		return sectors;
	}
	
	public Sector save(Sector sector) {
		this.logger.info("save Call = " + sector);
		Sector sectorreturn = sectorRepository.save(sector);
		this.logger.info("save Return = " + sectorreturn);
		return sectorreturn;
	}
	
	public void create(Sector sector, User user) {
		this.logger.info("create Call = " + sector + " " + user);
		sector.setUser(user);
		save(sector);
	}
	
	public void updateSector(Long idSector, String name, Integer global_length, String type, Topos topos) {
		this.logger.info("updateSector Call = " + idSector + " " + name + " " + global_length + " " + type + " " + topos);
		sectorRepository.updateSector(idSector, name, global_length, type, topos);
	}
	
	public Boolean checkAcces(Long idSector) {
		this.logger.info("asAcces Call = " + idSector);
		if (getByUserId(userService.authUser().getId()).contains(getById(idSector))) {
			return true;
		} else {
			return false;
		}
	}
}
