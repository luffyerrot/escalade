package fr.projet.escalade.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Role;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.UserRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class UserService extends CustomUserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder passwordEncoder;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	public User getById(Long id) {
		this.logger.debug("getById Call = " + id);
		if(id != 0) {
			User user = userRepository.findById(id).get();
			this.logger.debug("getById Return = " + user);
			return user;
		}
		return null;
	}

	public User save(User user) {
		this.logger.debug("save Call = " + user);
		User userreturn = userRepository.save(user);
		this.logger.debug("save Return = " + userreturn);
		return userreturn;
	}

	public List<User> findAll() {
		List<User> user = userRepository.findAll();
		this.logger.debug("findAll Return = " + user);
		return user;
	}

	public Long getIdByName(String name) {
		this.logger.debug("getIdByName Call = " + name);
		try {
			Long namereturn = userRepository.findByEmail(name).get().getId();
			this.logger.debug("getIdByName Return = " + namereturn);
			return namereturn;
		} catch (NoSuchElementException e) {
			Long noId = Integer.toUnsignedLong(0);
			this.logger.debug("getIdByName Try Catch Return = " + noId);
			return noId;
		}
	}

	public User authUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Long id = this.getIdByName(name);
		User user = this.getById(id);
		this.logger.debug("authUser Return = " + user);
		return user;
	}

	public void updateUser(Long idUser, String username, String email) {
		this.logger.debug("updateUser Call = " + idUser + " " + username + " " + email);
		userRepository.updateUser(idUser, username, email);
	}

	public void create(User user) {
		this.logger.debug("create Call = " + user);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = roleService.findByName("ROLE_USER");
		this.logger.debug("create Role = " + role);
		user.setRoles(Arrays.asList(role));
		save(user);
	}
}
