package fr.projet.escalade.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Comment;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.repositories.CommentRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class CommentService extends CustomUserDetailsService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	ToposService toposService;

	@Autowired
	UserService userService;
	
	Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	public Comment getById(Long id) {
		this.logger.info("getById Call = " + id);
		Comment comment = commentRepository.findById(id).get();
		this.logger.info("getById Return = " + comment);
		return comment;
	}
	
	public List<Comment> getByToposId(Long id) {
		this.logger.info("getByToposId Call = " + id);
		List<Comment> comment = commentRepository.findByToposId(id);
		this.logger.info("getByToposId Return = " + comment);
		return comment;
	}
	
	public Comment save(Comment comment) {
		this.logger.info("save Call = " + comment);
		Comment commentreturn = commentRepository.save(comment);
		this.logger.info("save Return = " + commentreturn);
		return commentreturn;
	}
	
	public void deleteById(Long id) {
		this.logger.info("deleteById Call = " + id);
		commentRepository.deleteById(id);
	}
	
	public void create(Comment comment, Long idTopos) {
		this.logger.info("create Call = " + comment + " " + idTopos);
		Date date = new Date();
		this.logger.info("create New Date = " + date);
		Topos topos = toposService.getById(idTopos);
		this.logger.info("create Topos = " + topos);
		comment.setTopos(topos);
		comment.setUser(userService.authUser());
		comment.setRelease_date(date);
		save(comment);
	}
	
	public void update(Long idComment, String comment) {
		this.logger.info("update Call = " + idComment + " " + comment);
		commentRepository.update(idComment, comment);
	}
}
