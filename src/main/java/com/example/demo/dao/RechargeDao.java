package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Contrat;
import com.example.demo.model.Recharge;

@Repository
public interface RechargeDao extends JpaRepository<Recharge,Integer>{
	public Recharge findById(int idRecharge); 
	public List<Recharge> findByContrat(Contrat contrat); 
}
