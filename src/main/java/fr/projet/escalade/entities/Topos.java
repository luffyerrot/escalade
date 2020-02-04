package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "topos")
public class Topos implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false, unique=true)
    private String name;
	
	@Column(nullable=false, unique=false)
    private String place;
	
	@Column(nullable=false)
    private Boolean reserved;
	
	@Column(nullable=false, unique=false)
    private String description;
	
	@Column(nullable=true, unique=false)
	private String date;

	//---------------------------------------------------------------------------------
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	
	@ManyToMany
	private List<Sector> sectors;
	
	@OneToOne(mappedBy = "topos", cascade=CascadeType.ALL)
    private Booking booking;

	//---------------------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}