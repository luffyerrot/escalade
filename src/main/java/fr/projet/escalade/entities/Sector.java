package fr.projet.escalade.entities;

import java.io.Serializable;

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
}
