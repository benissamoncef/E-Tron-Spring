package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Facture;

@Repository
public interface FactureDao extends JpaRepository<Facture,Integer> {
		public Facture findById(int idFacture); 

}
