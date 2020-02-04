package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=true)
    @NotEmpty
	private String email;
	
	@Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String password;
	
	@Column(nullable=false)
    @NotEmpty()
	private String username;
	
	//---------------------------------------------------------------------------------
   
	@ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
       inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private List <Role> roles;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List <Topos> topos;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List <Sector> sectors;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List <Comment> comments;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List <Booking> bookings;
    
    //----------------------------------------------------------------------------------
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Topos> getTopos() {
		return topos;
	}

	public void setTopos(List<Topos> topos) {
		this.topos = topos;
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
}