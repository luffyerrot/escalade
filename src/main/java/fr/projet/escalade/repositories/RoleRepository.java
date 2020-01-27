package fr.projet.escalade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
