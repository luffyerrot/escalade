package fr.projet.escalade.service;

import java.util.List;

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
	
	public Topos getById(Long id) {
		return toposRepository.findById(id).get();
	}
	
	public List<Topos> getByUserId(Long id) {
		return toposRepository.findByUserId(id);
	}
	
	public Topos save(Topos topos) {
		return toposRepository.save(topos);
	}
}
