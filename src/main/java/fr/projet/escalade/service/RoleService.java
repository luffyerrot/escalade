package fr.projet.escalade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Role;
import fr.projet.escalade.repositories.RoleRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class RoleService extends CustomUserDetailsService{

	@Autowired
	RoleRepository roleRepository;
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
