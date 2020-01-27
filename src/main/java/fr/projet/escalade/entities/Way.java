package fr.projet.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ways")
public class Way implements Serializable{
	
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=false)
    private Integer length;
	
	@Column(nullable=false, unique=false)
    private Integer difficulty;
	
	@Column(nullable=true, unique=false)
    private String description;
}
