package fr.projet.escalade.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String pseudo;
    private String prenom;
    private String nom;
    private String adresse;
    private String ville;
    private String anniv;
    private String age;
    private String telephone;
    
    public Person(){
    	super();
    }
    
    public Person(String pseudo, String prenom, String nom, String adresse, String ville, String anniv, String age, String telephone) {
    	super();
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
		return "Person [id=" + id + ", pseudo=" + pseudo + ", prenom=" + prenom + ", nom=" + nom + ", adresse="
				+ adresse + ", ville=" + ville + ", anniv=" + anniv + ", age=" + age + ", telephone=" + telephone + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}