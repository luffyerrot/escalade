package fr.projet.escalade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
