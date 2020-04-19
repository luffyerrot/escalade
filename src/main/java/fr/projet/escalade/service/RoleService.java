package fr.projet.escalade.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Role;
import fr.projet.escalade.repositories.RoleRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class RoleService extends CustomUserDetailsService{

	@Autowired
	RoleRepository roleRepository;

	Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	public Role findByName(String name) {
		this.logger.debug("findByName Call = " + name);
		Role role = roleRepository.findByName(name);
		this.logger.debug("findByName Return = " + role);
		return role;
	}
}
