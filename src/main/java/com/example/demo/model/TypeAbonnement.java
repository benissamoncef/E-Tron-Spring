package com.example.demo.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name ="typeabonnement")
public class TypeAbonnement {
	
	@Id
	@GeneratedValue
	private int idType;
	
	@Column(nullable = false)
	private float fraisBase; 
	
	@Column(nullable = false)
	private float ac; 
	
	@Column(nullable = false)
	private float dcHpc; 
	
	@Column(nullable = false)
	private float partenaireHP;
	
	@Column(nullable = false)
	private float fraisBlocageDc;
	
	@Column(nullable = false)
	private float fraisBlocageAc; 

	

	@OneToMany(mappedBy = "typeAbonnement")
	private List<Contrat> contrats;



	public int getIdType() {
		return idType;
	}



	public void setIdType(int idType) {
		this.idType = idType;
	}



	public float getFraisBase() {
		return fraisBase;
	}



	public void setFraisBase(float fraisBase) {
		this.fraisBase = fraisBase;
	}



	public float getAc() {
		return ac;
	}



	public void setAc(float ac) {
		this.ac = ac;
	}



	public float getDcHpc() {
		return dcHpc;
	}



	public void setDcHpc(float dcHpc) {
		this.dcHpc = dcHpc;
	}



	public float getPartenaireHP() {
		return partenaireHP;
	}



	public void setPartenaireHP(float partenaireHP) {
		this.partenaireHP = partenaireHP;
	}



	public float getFraisBlocageDc() {
		return fraisBlocageDc;
	}



	public void setFraisBlocageDc(float fraisBlocageDc) {
		this.fraisBlocageDc = fraisBlocageDc;
	}



	public float getFraisBlocageAc() {
		return fraisBlocageAc;
	}



	public void setFraisBlocageAc(float fraisBlocageAc) {
		this.fraisBlocageAc = fraisBlocageAc;
	}



	public List<Contrat> getContrats() {
		return contrats;
	}



	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}



	public TypeAbonnement() {
		super();
	}



	@Override
	public String toString() {
		return "TypeAbonnement [idType=" + idType + ", fraisBase=" + fraisBase + ", ac=" + ac + ", dcHpc=" + dcHpc
				+ ", partenaireHP=" + partenaireHP + ", fraisBlocageDc=" + fraisBlocageDc + ", fraisBlocageAc="
				+ fraisBlocageAc + ", contrats=" + contrats + "]";
	}  
	
	
	
	
}
