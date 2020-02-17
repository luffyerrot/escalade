package fr.projet.escalade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Comment;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.CommentRepository;
import fr.projet.escalade.repositories.ToposRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class CommentService extends CustomUserDetailsService{

	@Autowired
	private CommentRepository commentRepository;
	
	public Comment getById(Long id) {
		return commentRepository.findById(id).get();
	}
	
	public List<Comment> getByToposId(Long id) {
		return commentRepository.findByToposId(id);
	}
	
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public void deleteById(Long id) {
		commentRepository.deleteById(id);
		return;
	}
}
