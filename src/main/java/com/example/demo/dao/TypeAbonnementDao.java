package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TypeAbonnement;



@Repository
public interface TypeAbonnementDao extends JpaRepository<TypeAbonnement,Integer>{
	public TypeAbonnement findById(int idType); 
}
