package fr.projet.escalade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.projet.escalade.entities.Booking;
import fr.projet.escalade.entities.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	public Booking findByToposId(Long idTopos);
	
	@Query("SELECT b FROM Booking b WHERE b.user = :user AND b.accepted = null")
	public List<Booking> findByUserIdAndAcceptedNull(@Param("user") User user);
	
	@Modifying
	@Query("UPDATE Booking b SET b.accepted = :accepted WHERE b.id = :id")
	void changeAccepted(@Param("id")Long idTopos, @Param("accepted")Boolean accepted);
	
	@Query("SELECT b FROM Booking b WHERE b.accepted = true OR b.accepted = false AND b.user = :user")
	public List<Booking> findByToposUserIdAndAcceptedFalseOrTrue(@Param("user") User user);
}
