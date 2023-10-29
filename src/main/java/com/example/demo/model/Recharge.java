package com.example.demo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="recharge")
public class Recharge {

	@Id
	@GeneratedValue
	private int idRecharge; 
	
	@Column(nullable = false)
	private float kwh; 
	
	@Column(nullable = false)
	private LocalDate dateDebutRecharge;
	
	@Column(nullable = false)
	private LocalDate dateFinRecharge;
	
	@Column(nullable = false)
	private String typeRecharge;
	
	@Column(nullable = false)
	private boolean isFactured; 
	
	
	
	@ManyToOne
	private Facture facture; 
	
	@ManyToOne
	private Contrat contrat;

	public int getIdRecharge() {
		return idRecharge;
	}

	public void setIdRecharge(int idRecharge) {
		this.idRecharge = idRecharge;
	}

	public float getKwh() {
		return kwh;
	}

	public void setKwh(float kwh) {
		this.kwh = kwh;
	}

	public LocalDate getDateDebutRecharge() {
		return dateDebutRecharge;
	}

	public void setDateDebutRecharge(LocalDate dateDebutRecharge) {
		this.dateDebutRecharge = dateDebutRecharge;
	}

	public LocalDate getDateFinRecharge() {
		return dateFinRecharge;
	}

	public void setDateFinRecharge(LocalDate dateFinRecharge) {
		this.dateFinRecharge = dateFinRecharge;
	}

	public String getTypeRecharge() {
		return typeRecharge;
	}

	public void setTypeRecharge(String typeRecharge) {
		this.typeRecharge = typeRecharge;
	}

	public boolean isFactured() {
		return isFactured;
	}

	public void setFactured(boolean isFactured) {
		this.isFactured = isFactured;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Recharge() {
		super();
	}

	@Override
	public String toString() {
		return "Recharge [idRecharge=" + idRecharge + ", kwh=" + kwh + ", dateDebutRecharge=" + dateDebutRecharge
				+ ", dateFinRecharge=" + dateFinRecharge + ", typeRecharge=" + typeRecharge + ", isFactured="
				+ isFactured + ", facture=" + facture + ", contrat=" + contrat + "]";
	} 
	
	
	
	
}
