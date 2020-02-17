package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	public List<Comment> findByToposId(Long id);
}
