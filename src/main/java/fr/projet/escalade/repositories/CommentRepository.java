package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

	public List<Comment> findByToposId(Long id);
	
	@Modifying
	@Query("UPDATE Comment c SET c.comment = :comment WHERE c.id = :id")
	void update(@Param("id")Long idComment, @Param("comment")String comment);
}
