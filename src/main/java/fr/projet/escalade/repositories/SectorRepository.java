package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Sector;
import fr.projet.escalade.entities.Topos;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{

	@Modifying
	@Query("UPDATE Sector s SET s.name = :name, s.global_length = :global, s.type = :type, s.topos = :topos WHERE s.id = :id")
	void updateSector(@Param("id")Long idSector, @Param("name")String name, @Param("global")Integer global_length, @Param("type")String type, @Param("topos")Topos topos);
	
	public List<Sector> findByUserId(Long id);
	
	public List<Sector> findByToposId(Long id);
}
