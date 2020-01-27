package fr.projet.escalade.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment implements Serializable{
	
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column(nullable=false, unique=false)
    private String comment;
	
	@Column(nullable=false, unique=false)
    private Integer release_date;
}
