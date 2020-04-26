package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false, unique=false)
    private Date booking_date;
	
	@Column(nullable=true, unique=false)
    private Boolean accepted;
	
	//---------------------------------------------------------------------------------

	@ManyToOne()
	@JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne()
	@JoinColumn(name = "topos_id")
    private Topos topos;
	
	//---------------------------------------------------------------------------------
	
	
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", booking_date=" + booking_date + ", accepted=" + accepted + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topos getTopos() {
		return topos;
	}

	public void setTopos(Topos topos) {
		this.topos = topos;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
}
