package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector implements Serializable{
	
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=false)
    private Integer global_length;
	
	@Column(nullable=true, unique=false)
    private Integer way_number;
	
	@Column(nullable=true, unique=false)
    private String type;

	//---------------------------------------------------------------------------------

	@ManyToMany(mappedBy="sectors", cascade=CascadeType.ALL)
	private List<Topos> topos;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	
	@OneToMany(mappedBy = "sector", cascade=CascadeType.ALL)
    private List <Comment> comments;
	
	@ManyToMany
	private List<Way> ways;

	//---------------------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGlobal_length() {
		return global_length;
	}

	public void setGlobal_length(Integer global_length) {
		this.global_length = global_length;
	}

	public Integer getWay_number() {
		return way_number;
	}

	public void setWay_number(Integer way_number) {
		this.way_number = way_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Way> getWays() {
		return ways;
	}

	public void setWays(List<Way> ways) {
		this.ways = ways;
	}

	public List<Topos> getTopos() {
		return topos;
	}

	public void setTopos(List<Topos> topos) {
		this.topos = topos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
