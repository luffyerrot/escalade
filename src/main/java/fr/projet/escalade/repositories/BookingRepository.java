package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Booking;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	@Query("SELECT b FROM Booking b WHERE b.accepted = null AND b.topos.user = :user")
	public List<Booking> findByToposUserAndAcceptedNull(@Param("user") User user);
	
	@Modifying
	@Query("UPDATE Booking b SET b.accepted = :accepted WHERE b.id = :id")
	void changeAccepted(@Param("id")Long idTopos, @Param("accepted")Boolean accepted);
	
	@Query("SELECT b FROM Booking b WHERE b.accepted is not null AND b.user = :user")
	public List<Booking> findByUserAndAcceptedFalseOrTrue(@Param("user") User user);
	
	@Query("SELECT b FROM Booking b WHERE b.accepted = true AND b.topos = :topos")
	public List<Booking> findByToposAndAccepted(@Param("topos") Topos topos);
	
	public List<Booking> findByUserId(Long id);
	
	public List<Booking> findByToposId(Long idTopos);
}
