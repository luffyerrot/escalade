package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Topos;

@Repository
public interface ToposRepository extends JpaRepository<Topos, Long>{

	public List<Topos> findByUserId(Long id);
	
	
	public List<Topos> findAllByPublished(Boolean published);
	
	public List<Topos> findByUserUsernameOrNameAndPublished(String username, String toposname, Boolean published);
	
	@Modifying
	@Query("UPDATE Topos t SET t.official = :official WHERE t.id = :id")
	void changeOfficial(@Param("id")Long idTopos, @Param("official")Boolean official);
	
	@Modifying
	@Query("UPDATE Topos t SET t.request = :request WHERE t.id = :id")
	void changeRequest(@Param("id")Long idTopos, @Param("request")Boolean request);
	
	
	@Modifying
	@Query("UPDATE Topos t SET t.published = :published WHERE t.id = :id")
	void changePublished(@Param("id")Long idTopos, @Param("published")Boolean published);
	
	@Modifying
	@Query("UPDATE Topos t SET t.reserved = :reserved WHERE t.id = :id")
	void changeReserved(@Param("id")Long idTopos, @Param("reserved")Boolean reserved);
	
	@Modifying
	@Query("UPDATE Topos t SET t.name = :name, t.description = :description, t.place = :place WHERE t.id = :id")
	void updateTopos(@Param("id")Long idTopos, @Param("name")String name, @Param("description")String description, @Param("place")String place);
	
	@Query("SELECT t FROM Topos t WHERE t.reserved = true AND t.id = :id")
	public Topos findByToposIdAndReserved(@Param("id")Long id);
}
