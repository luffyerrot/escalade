package fr.projet.escalade.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.UserRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class UserService extends CustomUserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Long getIdByName(String name) {
		try {
			return userRepository.findByEmail(name).get().getId();
		} catch (NoSuchElementException e) {
			Long noId = Integer.toUnsignedLong(0);
			return noId;
		}
	}
}
