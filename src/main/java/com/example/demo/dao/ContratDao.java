package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contrat;


@Repository
public interface ContratDao extends JpaRepository<Contrat,Integer>{
	public Contrat findById(int id); 
}
