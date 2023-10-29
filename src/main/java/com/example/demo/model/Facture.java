package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="facture")
public class Facture {

	
	@Id
	@GeneratedValue
	private int idFacture;
	
	@Column(nullable = false)
	private LocalDate dateFacturation; 

	private LocalDate datePaiement; 
	
	@Column(nullable = false)
	private boolean etatFacture; 
	
	@Column(nullable = false)
	private float montantFacture;
	
	@ManyToOne
	private Contrat contrat; 
		

	@OneToMany(mappedBy="facture")
	private List<Recharge> recharges;


	public Facture() {
		super();
	}


	public int getIdFacture() {
		return idFacture;
	}


	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}


	public LocalDate getDateFacturation() {
		return dateFacturation;
	}


	public void setDateFacturation(LocalDate dateFacturation) {
		this.dateFacturation = dateFacturation;
	}


	public LocalDate getDatePaiement() {
		return datePaiement;
	}


	public void setDatePaiement(LocalDate datePaiement) {
		this.datePaiement = datePaiement;
	}


	public boolean isEtatFacture() {
		return etatFacture;
	}


	public void setEtatFacture(boolean etatFacture) {
		this.etatFacture = etatFacture;
	}


	public float getMontantFacture() {
		return montantFacture;
	}


	public void setMontantFacture(float montantFacture) {
		this.montantFacture = montantFacture;
	}


	public Contrat getContrat() {
		return contrat;
	}


	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}


	public List<Recharge> getRecharges() {
		return recharges;
	}


	public void setRecharges(List<Recharge> recharges) {
		this.recharges = recharges;
	}


	@Override
	public String toString() {
		return "Facture [idFacture=" + idFacture + ", dateFacturation=" + dateFacturation + ", datePaiement="
				+ datePaiement + ", etatFacture=" + etatFacture + ", montantFacture=" + montantFacture + ", contrat="
				+ contrat + ", recharges=" + recharges + "]";
	} 
	
	
	
	
	
	
	
	
}
