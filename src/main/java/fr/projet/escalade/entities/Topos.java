package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "topos")
public class Topos implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable=false, unique=false)
    private String name;
	
	@Column(nullable=false, unique=false)
    private String place;
	
	@Column(nullable=false)
    private Boolean reserved;
	
	@Column(nullable=false)
    private Boolean published;
	
	@Column(nullable=false, unique=false)
    private String description;
	
	@Column(nullable=true, unique=false)
	private Date date;
	
	@Column(nullable=false, unique=false)
	private Boolean official;
	
	@Column(nullable=false, unique=false)
	private Boolean request;

	//---------------------------------------------------------------------------------
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private User user;
	
	@OneToMany(mappedBy="topos")
	private List<Sector> sectors;
	
	@OneToMany(mappedBy = "topos")
    private List <Booking> bookings;
	
	@OneToMany(mappedBy = "topos") 
    private List <Comment> comments;

	//---------------------------------------------------------------------------------

	@Override
	public String toString() {
		return "Topos [id=" + id + ", name=" + name + ", place=" + place + ", reserved=" + reserved + ", published="
				+ published + ", description=" + description + ", date=" + date + ", official=" + official
				+ ", request=" + request + "]";
	}
	
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

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Boolean getOfficial() {
		return official;
	}

	public void setOfficial(Boolean official) {
		this.official = official;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getRequest() {
		return request;
	}

	public void setRequest(Boolean request) {
		this.request = request;
	}
}
