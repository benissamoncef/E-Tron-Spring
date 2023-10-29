package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name ="contrat")
public class Contrat {

	@Id
	@GeneratedValue
	private int idContrat; 
	
	@Column(nullable = false)
	private LocalDate dateSignature; 
	
	@Column(nullable = false)
	private int dureeContrat;
	
	
	@ManyToOne
	private TypeAbonnement typeAbonnement; 
	
	
	@OneToMany(mappedBy = "contrat")
	private List<Facture> factures; 
	
	
	@OneToOne
	private User user;


	public int getIdContrat() {
		return idContrat;
	}


	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}


	public LocalDate getDateSignature() {
		return dateSignature;
	}


	public void setDateSignature(LocalDate dateSignature) {
		this.dateSignature = dateSignature;
	}


	public int getDureeContrat() {
		return dureeContrat;
	}


	public void setDureeContrat(int dureeContrat) {
		this.dureeContrat = dureeContrat;
	}


	public TypeAbonnement getTypeAbonnement() {
		return typeAbonnement;
	}


	public void setTypeAbonnement(TypeAbonnement typeAbonnement) {
		this.typeAbonnement = typeAbonnement;
	}


	public List<Facture> getFactures() {
		return factures;
	}


	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Contrat() {
		super();
	}


	@Override
	public String toString() {
		return "Contrat [idContrat=" + idContrat + ", dateSignature=" + dateSignature + ", dureeContrat=" + dureeContrat
				+ ", typeAbonnement=" + typeAbonnement + ", factures=" + factures + ", user=" + user + "]";
	} 
	
	
	
}
