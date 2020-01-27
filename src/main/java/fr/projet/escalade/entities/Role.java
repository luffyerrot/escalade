package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "roles")
public class Role implements Serializable{

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
	@Column(nullable = false, unique = true)
    @NotEmpty
    private String name;
    
	//---------------------------------------------------------------------------------
	
	@ManyToMany(mappedBy = "roles")
    private List <User> users;

	//---------------------------------------------------------------------------------
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
