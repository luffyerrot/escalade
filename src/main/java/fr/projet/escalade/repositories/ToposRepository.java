package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Topos;

@Repository
public interface ToposRepository extends JpaRepository<Topos, Long>{

	public List<Topos> findByUserId(Long id);
	
	public List<Topos> findAllByOfficial(Boolean official);
}
