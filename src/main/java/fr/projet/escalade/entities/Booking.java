package fr.projet.escalade.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	//---------------------------------------------------------------------------------

	@ManyToOne()
	@JoinColumn(name = "user_id")
    private User user;
	
	@OneToOne()
    private Topos topos;
	
	//---------------------------------------------------------------------------------
	
	public Long getId() {
		return id;
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
}
