package fr.projet.escalade.service;

import java.util.List;

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
	
	public Sector getById(Long id) {
		return sectorRepository.findById(id).get();
	}
	
	public List<Sector> getByUserId(Long id) {
		return sectorRepository.findByUserId(id);
	}
	
	public List<Sector> getByToposId(Long id) {
		return sectorRepository.findByToposId(id);
	}
	
	public Sector save(Sector topos) {
		return sectorRepository.save(topos);
	}
	
	public void create(Sector sector, User user) {
		sector.setUser(user);
		save(sector);
	}
	
	public void updateSector(Long idSector, String name, Integer global_length, String type, Topos topos) {
		sectorRepository.updateSector(idSector, name, global_length, type, topos);
	}
	
	public Boolean asAcces(Long idSector) {
		if (getByUserId(userService.authUser().getId()).contains(getById(idSector))) {
			return true;
		} else {
			return false;
		}
	}
}
