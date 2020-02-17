package fr.projet.escalade.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=false)
    private String comment;
	
	@Column(nullable=false, unique=false)
    private Timestamp release_date;
	
	//---------------------------------------------------------------------------------
	   
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne
	@JoinColumn(name = "topos_id")
    private Topos topos;

	//---------------------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	  
	public Timestamp getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Timestamp release_date) {
		this.release_date = release_date;
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
