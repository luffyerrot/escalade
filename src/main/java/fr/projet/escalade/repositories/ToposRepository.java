package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.escalade.entities.Topos;

public interface ToposRepository extends JpaRepository<Topos, Long>{

	public List<Topos> findByUserId(Long id);
}
