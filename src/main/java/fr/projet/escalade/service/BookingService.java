package fr.projet.escalade.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.escalade.entities.Booking;
import fr.projet.escalade.entities.Topos;
import fr.projet.escalade.entities.User;
import fr.projet.escalade.repositories.BookingRepository;
import fr.projet.escalade.security.CustomUserDetailsService;

@Service
public class BookingService extends CustomUserDetailsService{

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ToposService toposService;
	
	Logger logger = LoggerFactory.getLogger(BookingService.class);
	
	public Booking getById(Long id) {
		this.logger.debug("getById Call = " + id);
		Booking booking =  bookingRepository.findById(id).get();
		this.logger.debug("getById Return = " + booking);
		return booking;
	}
	
	public Booking save(Booking booking) {
		this.logger.debug("save Call = " + booking);
		Booking bookingreturn = bookingRepository.save(booking);
		this.logger.debug("save Return = " + bookingreturn);
		return bookingreturn;
	}
	
	public Booking getByToposId(Long idTopos) {
		this.logger.debug("getByToposId Call = " + idTopos);
		Booking booking = bookingRepository.findByToposId(idTopos);
		this.logger.debug("getByToposId Return = " + booking);
		return booking;
	}
	
	public List<Booking> getByUserIdAndAcceptedNull(User user) {
		this.logger.debug("getByUserIdAndAcceptedNull Call = " + user);
		List<Booking> bookings = bookingRepository.findByUserIdAndAcceptedNull(user);
		this.logger.debug("getByUserIdAndAcceptedNull Return = " + bookings);
		return bookings;
	}
	
	public void changeAccepted(Long idBooking, Boolean accepted) {
		this.logger.debug("changeAccepted Call = " + idBooking + " " + accepted);
		bookingRepository.changeAccepted(idBooking, accepted);
	}
	
	public List<Booking> getByToposUserIdAndAccepted(User user) {
		this.logger.debug("getByToposUserIdAndAccepted Call = " + user);
		List<Booking> bookings = bookingRepository.findByToposUserIdAndAcceptedFalseOrTrue(user);
		this.logger.debug("getByToposUserIdAndAccepted Return = " + bookings);
		return bookings;
	}
	
	public void sendBookingRequest(Long idTopos) {
		this.logger.debug("sendBookingRequest Call = " + idTopos);
		Topos topos = toposService.getById(idTopos);
		this.logger.debug("sendBookingRequest Topos = " + topos);
		if (topos.getReserved().equals(false)) {
			Date date = new Date();
			this.logger.debug("create New Date = " + date);
			Booking booking = new Booking();
			this.logger.debug("create Booking = " + booking);
			booking.setTopos(topos);
			booking.setUser(userService.authUser());
			booking.setBooking_date(date);
			booking.setAccepted(null);
			save(booking);
		}
	}
	
	public void deleteById(Long idBooking) {
		this.logger.debug("deleteById Call = " + idBooking);
		bookingRepository.deleteById(idBooking);
	}
}
