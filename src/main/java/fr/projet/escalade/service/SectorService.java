package fr.projet.escalade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.repositories.SectorRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class SectorService extends CustomUserDetailsService{

	@Autowired
	private SectorRepository sectorRepository;
	
	public Sector getById(Long id) {
		return sectorRepository.findById(id).get();
	}
	
	public List<Sector> getByUserId(Long id) {
		return sectorRepository.findByUserId(id);
	}
	
	public Sector save(Sector topos) {
		return sectorRepository.save(topos);
	}
}
