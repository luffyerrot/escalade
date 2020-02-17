package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Way;

@Repository
public interface WayRepository extends JpaRepository<Way, Long>{

	public List<Way> findByUserId(Long id);
}
