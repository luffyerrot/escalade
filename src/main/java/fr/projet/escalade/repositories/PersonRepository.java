package fr.projet.escalade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
