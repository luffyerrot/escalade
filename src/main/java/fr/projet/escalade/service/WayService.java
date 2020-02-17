package fr.projet.escalade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Way;
import fr.projet.escalade.repositories.WayRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class WayService extends CustomUserDetailsService{

	@Autowired
	private WayRepository wayRepository;
	
	public Way getById(Long id) {
		return wayRepository.findById(id).get();
	}
	
	public List<Way> getByUserId(Long id) {
		return wayRepository.findByUserId(id);
	}
	
	public Way save(Way way) {
		return wayRepository.save(way);
	}
}
