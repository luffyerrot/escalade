package fr.projet.escalade.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	@Modifying
	@Query("UPDATE User u SET u.username = :username, u.email = :email WHERE u.id = :id")
	void updateUser(@Param("id")Long idTopos, @Param("username")String username, @Param("email")String description);
}
