package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{

	public List<Sector> findByUserId(Long id);
}
