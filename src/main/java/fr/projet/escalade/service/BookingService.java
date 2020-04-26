package fr.projet.escalade.service;

import java.util.ArrayList;
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
		this.logger.info("getById Call = " + id);
		Booking booking =  bookingRepository.findById(id).get();
		this.logger.info("getById Return = " + booking);
		return booking;
	}
	
	public List<Booking> getByUserId(Long id) {
		this.logger.info("getByUserId Call = " + id);
		List<Booking> bookings = bookingRepository.findByUserId(id);
		this.logger.info("getByUserId Return = " + bookings);
		return bookings;
	}
	
	public Booking save(Booking booking) {
		this.logger.info("save Call = " + booking);
		Booking bookingreturn = bookingRepository.save(booking);
		this.logger.info("save Return = " + bookingreturn);
		return bookingreturn;
	}
	
	public List<Booking> getByToposId(Long idTopos) {
		this.logger.info("getByToposId Call = " + idTopos);
		List<Booking> bookings = bookingRepository.findByToposId(idTopos);
		this.logger.info("getByToposId Return = " + bookings);
		return bookings;
	}
	
	public List<Booking> getByUserAndAcceptedNull(User user) {
		this.logger.info("getByUserAndAcceptedNull Call = " + user);
		List<Booking> bookings = bookingRepository.findByToposUserAndAcceptedNull(user);
		this.logger.info("getByUserAndAcceptedNull Return = " + bookings);
		return bookings;
	}
	
	public void changeAccepted(Long idBooking, Boolean accepted) {
		this.logger.info("changeAccepted Call = " + idBooking + " " + accepted);
		bookingRepository.changeAccepted(idBooking, accepted);
	}
	
	public List<Booking> getByUserAndAccepted(User user) {
		this.logger.info("getByToposUserIdAndAccepted Call = " + user);
		List<Booking> bookings = bookingRepository.findByUserAndAcceptedFalseOrTrue(user);
		this.logger.info("getByToposUserIdAndAccepted Return = " + bookings);
		return bookings;
	}
	
	public List<Booking> getByToposAndAccepted(List<Topos> topos) {
		this.logger.info("getByToposAndAccepted Call = " + topos);
		List<Booking> bookings = new ArrayList<>();
		for (int i = 0; i < topos.size(); i++) {
			Topos toposUnique = topos.get(i);
			this.logger.info("getByToposAndAccepted For = " + toposUnique);
			for (int j = 0; j < bookingRepository.findByToposAndAccepted(toposUnique).size(); j++) {
				bookings.add(bookingRepository.findByToposAndAccepted(toposUnique).get(j));
			}
		}
		this.logger.info("getByToposAndAccepted Return = " + bookings);
		return bookings;
	}
	
	public void sendBookingRequest(Long idTopos) {
		this.logger.info("sendBookingRequest Call = " + idTopos);
		Topos topos = toposService.getById(idTopos);
		this.logger.info("sendBookingRequest Topos = " + topos);
		if (topos.getReserved().equals(false)) {
			Date date = new Date();
			this.logger.info("create New Date = " + date);
			Booking booking = new Booking();
			this.logger.info("create Booking = " + booking);
			booking.setTopos(topos);
			booking.setUser(userService.authUser());
			booking.setBooking_date(date);
			booking.setAccepted(null);
			save(booking);
		}
	}
	
	public void deleteById(Long idBooking) {
		this.logger.info("deleteById Call = " + idBooking);
		bookingRepository.deleteById(idBooking);
	}
	
	public Boolean checkAcces(Long idBooking) {
		if (getByUserId(userService.authUser().getId()).contains(getById(idBooking))) {
			return true;
		} else {
			return false;
		}
	}
}
