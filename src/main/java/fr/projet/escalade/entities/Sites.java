package fr.projet.escalade.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "sites")
public class Sites implements Serializable{
	
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=false)
    private String region;
	
	@Column(nullable=false, unique=false)
    private Boolean official;
	
	@Column(nullable=false, unique=false)
    private Integer length;
}
