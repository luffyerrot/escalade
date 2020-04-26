package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=true)
    private String name;
	
	@Column(nullable=false, unique=false)
    private Integer global_length;
	
	@Column(nullable=true, unique=false)
    private String type;

	//---------------------------------------------------------------------------------

	@ManyToOne()
	@JoinColumn(name = "topos_id")
	private Topos topos;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    private User user;
	
	@OneToMany(mappedBy="sector")
	private List<Way> ways;

	//---------------------------------------------------------------------------------

	@Override
	public String toString() {
		return "Sector [id=" + id + ", name=" + name + ", global_length=" + global_length + ", type=" + type + "]";
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

	public Integer getGlobal_length() {
		return global_length;
	}

	public void setGlobal_length(Integer global_length) {
		this.global_length = global_length;
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

	public Topos getTopos() {
		return topos;
	}

	public void setTopos(Topos topos) {
		this.topos = topos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
