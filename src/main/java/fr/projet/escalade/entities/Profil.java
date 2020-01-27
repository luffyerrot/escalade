package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "profils")
public class Profil implements Serializable{
	
    @Id
    @Column(name = "id")
    private Long id;
    
	//---------------------------------------------------------------------------------
    
    @OneToOne
    @MapsId
    private User user;
	
    @Column(nullable=true, unique=false)
    private String firstname;
	
	@Column(nullable=true, unique=false)
    private String lastname;
	
	@Column(nullable=true, unique=false)
    private String country;
	
	@Column(nullable=true, unique=false)
    private String adress;
	
	@Column(nullable=true, unique=false)
    private Integer phone_number;
    
	@Transient
	@Column(nullable=true, unique=false)
    private Integer age;
    
    @Temporal(TemporalType.DATE)
	@Column(nullable=true, unique=false)
    private Date birthDate;
    
    @Enumerated(EnumType.STRING)
	@Column(nullable=true, unique=false)
    private Gender gender;
    
    public enum Gender {
    	MALE,FEMALE
    }
}