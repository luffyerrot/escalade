package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.entities.Way;

@Repository
public interface WayRepository extends JpaRepository<Way, Long>{

	@Modifying
	@Query("UPDATE Way w SET w.length = :length, w.difficulty = :difficulty, w.description = :desc, w.sector = :sector WHERE w.id = :id")
	void updateWay(@Param("id")Long idWay, @Param("length")Integer length, @Param("difficulty")Integer difficulty, @Param("desc")String description, @Param("sector")Sector sector);

	public List<Way> findByUserId(Long id);
	
	public List<Way> findBySectorId(Long id);
}
