package fr.projet.escalade.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable=false, unique=true)
    @NotEmpty
    @Email(message="{errors.invalid_email}")
	private String email;
	@Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String password;
	@Column(nullable=false)
    @NotEmpty()
	private String pseudo;
    private String prenom;
    private String nom;
    private String adresse;
    private String ville;
    private String anniv;
    private String age;
    private String telephone;
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
       inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List <Role> roles;
    
    public User(){
    	super();
    }
    
    public User(String email, String pseudo,String password, String prenom, String nom, String adresse, String ville, String anniv, String age, String telephone) {
    	super();
    	this.email = email;
    	this.password = password;
    	this.pseudo = pseudo;
    	this.prenom = prenom;
    	this.nom = nom;
    	this.adresse = adresse;
    	this.ville = ville;
    	this.anniv = anniv;
    	this.age = age;
    	this.telephone = telephone;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", pseudo=" + pseudo + ", prenom="
				+ prenom + ", nom=" + nom + ", adresse=" + adresse + ", ville=" + ville + ", anniv=" + anniv + ", age="
				+ age + ", telephone=" + telephone + ", roles=" + roles + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getAnniv() {
		return anniv;
	}
	
	public void setAnniv(String anniv) {
		this.anniv = anniv;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}